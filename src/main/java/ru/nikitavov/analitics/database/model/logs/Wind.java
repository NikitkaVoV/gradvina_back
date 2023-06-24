package ru.nikitavov.analitics.database.model.logs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.nikitavov.analitics.database.model.Region;
import ru.nikitavov.analitics.model.Direction;

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
@Table(name = "winds")
public class Wind {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "wind_id_generator")
    @SequenceGenerator(name = "wind_id_generator", allocationSize = 1)
    @Column(name = "id", nullable = false)
    Integer id;

    @JsonIgnore
    @ManyToOne
    Region region;

    @Column(name = "date_and_time", nullable = false)
    LocalDateTime dateAndTime;

    @Column(name = "power", nullable = false)
    BigDecimal power;

    @Column(name = "direction", nullable = false)
    @Enumerated(EnumType.STRING)
    Direction direction;

}
