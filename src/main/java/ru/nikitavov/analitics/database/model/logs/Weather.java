package ru.nikitavov.analitics.database.model.logs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.nikitavov.analitics.database.model.Region;
import ru.nikitavov.analitics.model.WeatherType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "weathers")
public class Weather {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "weather_id_generator")
    @SequenceGenerator(name = "weather_id_generator", allocationSize = 1)
    @Column(name = "id", nullable = false)
    Integer id;

    @JsonIgnore
    @ManyToOne
    Region region;

    @Column(name = "weather_type", nullable = false)
    @Enumerated(EnumType.STRING)
    WeatherType weatherType;

    @Column(name = "date_and_time", nullable = false)
    LocalDateTime dateAndTime;
}
