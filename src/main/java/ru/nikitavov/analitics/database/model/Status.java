package ru.nikitavov.analitics.database.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "statuses")
public class Status implements IEntity<Integer> {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "status_id_generator")
    @SequenceGenerator(name = "status_id_generator", allocationSize = 1)
    @Column(name = "id", nullable = false)
    Integer id;

    @NotBlank
    @Column(name = "name", nullable = false)
    String name;
}

/*
* Создан
* Отменён
* Принят
* Выдан
* */
