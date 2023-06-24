package ru.nikitavov.analitics.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nikitavov.analitics.database.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}