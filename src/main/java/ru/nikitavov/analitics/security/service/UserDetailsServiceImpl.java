package ru.nikitavov.analitics.security.service;


import ru.nikitavov.analitics.database.model.User;
import ru.nikitavov.analitics.database.repository.UserRepository;
import ru.nikitavov.analitics.security.util.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Service
@RequiredArgsConstructor
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserPrincipalCreator principalCreator;
    
    @Override
    public UserDetails loadUserByUsername(String id)
            throws UsernameNotFoundException {
        System.out.println("id = " + id);
        User user = userRepository.findById(Integer.parseInt(id))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id : " + id));

        return principalCreator.create(user);
    }

    public UserDetails loadUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return principalCreator.create(user);
    }
}