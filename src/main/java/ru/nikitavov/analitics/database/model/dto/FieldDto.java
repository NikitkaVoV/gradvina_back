package ru.nikitavov.analitics.database.model.dto;

import ru.nikitavov.analitics.database.model.Field;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Field}
 */
public record FieldDto(Integer id, List<PointDto> points, List<RegionDto> regions) implements Serializable {
}