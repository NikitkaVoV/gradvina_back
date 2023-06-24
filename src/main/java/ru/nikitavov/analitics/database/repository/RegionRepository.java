package ru.nikitavov.analitics.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nikitavov.analitics.database.model.Region;

public interface RegionRepository extends JpaRepository<Region, Integer> {
}