package ru.nikitavov.analitics.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nikitavov.analitics.database.model.logs.ManualWork;

public interface ManualWorkRepository extends JpaRepository<ManualWork, Integer> {
}