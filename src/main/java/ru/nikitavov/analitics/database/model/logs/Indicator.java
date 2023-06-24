package ru.nikitavov.analitics.database.model.logs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.nikitavov.analitics.database.model.Region;
import ru.nikitavov.analitics.model.IndicatorType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "indicators")
public class Indicator {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "indicator_id_generator")
    @SequenceGenerator(name = "indicator_id_generator", allocationSize = 1)
    @Column(name = "id", nullable = false)
    Integer id;

    @JsonIgnore
    @ManyToOne
    Region region;

    @Column(name = "indicator_type", nullable = false)
    @Enumerated(EnumType.STRING)
    IndicatorType indicatorType;

    @Column(name = "date_and_time", nullable = false)
    LocalDateTime dateAndTime;

    @Column(name = "value", nullable = false)
    BigDecimal value;

}
