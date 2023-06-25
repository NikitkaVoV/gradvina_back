package ru.nikitavov.analitics.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nikitavov.analitics.database.model.User;
import ru.nikitavov.analitics.database.repository.UserRepository;
import ru.nikitavov.analitics.message.AuthResponse;
import ru.nikitavov.analitics.message.LoginRequest;
import ru.nikitavov.analitics.message.SignUpRequest;
import ru.nikitavov.analitics.security.data.UserPrincipal;
import ru.nikitavov.analitics.security.service.TokenProvider;
import ru.nikitavov.analitics.security.service.UserPrincipalCreator;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final UserPrincipalCreator principalCreator;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Optional<User> optionalUser = userRepository.findByLogin(loginRequest.login());
        if (optionalUser.isEmpty()) return ResponseEntity.notFound().build();

        if (!passwordEncoder.matches(loginRequest.password(), optionalUser.get().getPassword())) {
            return ResponseEntity.badRequest().build();
        }

        UserPrincipal principal = principalCreator.create(optionalUser.get());
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principal,
                null,
                principal.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByLogin(signUpRequest.login())) {
            throw new RuntimeException("Email address already in use.");
        }

        User user = new User();
        user.setFullName(signUpRequest.name());
        user.setLogin(signUpRequest.login());
        user.setPhone(signUpRequest.phone());
        user.setPassword(signUpRequest.password());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User result = userRepository.save(user);

        UserPrincipal principal = principalCreator.create(result);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principal,
                null,
                principal.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(new AuthResponse(token));
    }

}
