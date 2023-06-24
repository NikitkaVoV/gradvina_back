package ru.nikitavov.analitics.database.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.nikitavov.analitics.database.model.logs.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "regions")
public class Region {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "region_id_generator")
    @SequenceGenerator(name = "region_id_generator", allocationSize = 1)
    @Column(name = "id", nullable = false)
    Integer id;

    @JsonIgnore
    @ManyToOne
    Field field;

    @JoinColumn(name = "photo_id")
    @ManyToOne
    Document photo;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "variety_id", nullable = false)
    Variety variety;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "soil_id", nullable = false)
    Soil soil;

    @Column(name = "note")
    String note;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "region_point",
            joinColumns = {@JoinColumn(name = "region_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "point_id", referencedColumnName = "id")}
    )
    List<Point> points;

    @OneToMany(mappedBy = "region")
    private List<Disease> regions;

    @OneToMany(mappedBy = "region")
    private List<Indicator> indicators;

    @OneToMany(mappedBy = "region")
    private List<ManualWork> manualWorks;

    @OneToMany(mappedBy = "region")
    private List<Weather> weathers;

    @OneToMany(mappedBy = "region")
    private List<Wind> winds;
}
