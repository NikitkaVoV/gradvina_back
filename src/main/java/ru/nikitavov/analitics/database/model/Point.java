package ru.nikitavov.analitics.database.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;


@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "point")
public class Point implements IEntity<Integer> {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "point_id_generator")
    @SequenceGenerator(name = "point_id_generator", allocationSize = 1)
    @Column(name = "id", nullable = false)
    Integer id;


    @Column(name = "lon", nullable = false, columnDefinition = "numeric(19,16)")
    BigDecimal lon;

    @Column(name = "lat", nullable = false, columnDefinition = "numeric(19,16)")
    BigDecimal lat;
}
