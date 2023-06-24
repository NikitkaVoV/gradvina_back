package ru.nikitavov.analitics.database.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link ru.nikitavov.analitics.database.model.Point}
 */
public record PointDto(Integer id, BigDecimal lon, BigDecimal lat) implements Serializable {
}