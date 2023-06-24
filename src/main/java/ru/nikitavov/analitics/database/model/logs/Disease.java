package ru.nikitavov.analitics.database.model.logs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.nikitavov.analitics.database.model.Region;

import javax.persistence.*;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "diseases")
public class Disease {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "disease_id_generator")
    @SequenceGenerator(name = "disease_id_generator", allocationSize = 1)
    @Column(name = "id", nullable = false)
    Integer id;

    @JsonIgnore
    @ManyToOne
    Region region;

    @Column(name = "description", nullable = false)
    String description;
}
