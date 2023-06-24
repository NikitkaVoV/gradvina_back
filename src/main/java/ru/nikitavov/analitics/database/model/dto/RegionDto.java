package ru.nikitavov.analitics.database.model.dto;

import ru.nikitavov.analitics.database.model.Region;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Region}
 */
public record RegionDto(Integer id, List<PointDto> points, String color) implements Serializable {
}