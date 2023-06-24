package ru.nikitavov.analitics.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nikitavov.analitics.database.model.Field;

public interface FieldRepository extends JpaRepository<Field, Integer> {
}