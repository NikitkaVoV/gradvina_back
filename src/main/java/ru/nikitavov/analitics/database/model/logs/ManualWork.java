package ru.nikitavov.analitics.database.model.logs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.nikitavov.analitics.database.model.Document;
import ru.nikitavov.analitics.database.model.Region;
import ru.nikitavov.analitics.database.model.User;
import ru.nikitavov.analitics.model.ManualWorkStage;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "manual_works")
public class ManualWork {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "manual_work_id_generator")
    @SequenceGenerator(name = "manual_work_id_generator", allocationSize = 1)
    @Column(name = "id", nullable = false)
    Integer id;

    @JsonIgnore
    @ManyToOne
    Region region;

    @Column(name = "manual_work_stage", nullable = false)
    @Enumerated(EnumType.STRING)
    ManualWorkStage manualWorkStage;

    @Column(name = "date_and_time", nullable = false)
    LocalDateTime dateAndTime;

    @JsonIgnore
    @ManyToOne
    User user;

    @NotBlank
    @Column(name = "note", nullable = true)
    String note;

    @JsonIgnore
    @ManyToOne
    Document document;
}
