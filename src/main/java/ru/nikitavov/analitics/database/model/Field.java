package ru.nikitavov.analitics.database.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fields")
public class Field {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "field_id_generator")
    @SequenceGenerator(name = "field_id_generator", allocationSize = 1)
    @Column(name = "id", nullable = false)
    Integer id;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "field_point",
            joinColumns = {@JoinColumn(name = "field_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "point_id", referencedColumnName = "id")}
    )
    List<Point> points;

    @OneToMany(mappedBy = "field")
    private List<Region> regions;

}
