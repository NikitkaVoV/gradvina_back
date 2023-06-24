package ru.nikitavov.analitics.database.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements IEntity<Integer>
{
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_id_generator")
    @SequenceGenerator(name = "user_id_generator", allocationSize = 1)
    @Column(name = "id", nullable = false)
    Integer id;

    @NotBlank
    @Column(name = "full_name", nullable = false)
    String full_name;


    @NotBlank
    @Column(name = "login", nullable = false)
    String login;

    @NotBlank
    @Column(name = "password", nullable = false)
    String password;

    @NotBlank
    @Column(name = "phone", nullable = false)
    String phone;

    @Setter(AccessLevel.NONE)
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "assigned_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    final Set<Role> roles = new LinkedHashSet<>();

}
