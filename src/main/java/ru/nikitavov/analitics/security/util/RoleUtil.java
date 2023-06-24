package ru.nikitavov.analitics.security.util;

import ru.nikitavov.analitics.database.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RoleUtil {

    public static List<GrantedAuthority> rolesToGrantedAuthority(Set<Role> roles) {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>(roles.size());

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName().toUpperCase().replaceAll(" ", "_")));
        }

        return authorities;
    }

}