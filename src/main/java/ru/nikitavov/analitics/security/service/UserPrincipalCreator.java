package ru.nikitavov.analitics.security.service;

import ru.nikitavov.analitics.database.model.Role;
import ru.nikitavov.analitics.database.model.User;
import ru.nikitavov.analitics.database.repository.UserRepository;
import ru.nikitavov.analitics.security.data.UserPrincipal;
import ru.nikitavov.analitics.security.util.RoleUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserPrincipalCreator {
    private final UserRepository userRepository;

    public UserPrincipalCreator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserPrincipal create(User user) {
        user = userRepository.findById(user.getId()).get();
        Set<Role> roles = user.getRoles();
        List<GrantedAuthority> authorities = RoleUtil.rolesToGrantedAuthority(roles);
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new UserPrincipal(
                user.getId(),
                user.getLogin(),
                user.getPassword(),
                authorities
        );
    }
}
