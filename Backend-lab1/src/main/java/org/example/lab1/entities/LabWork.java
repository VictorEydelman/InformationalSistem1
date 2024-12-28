package org.IS.lab1.entities;

import jakarta.persistence.*;
import lombok.*;
import org.IS.lab1.entities.enums.Difficulty;
import org.hibernate.annotations.Check;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "LabWorks")
public class LabWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @Column
    @Check(constraints = "name is not null")
    private String name; //Поле не может быть null, Строка не может быть пустой
    @ManyToOne
    @Check(constraints = "coordinates is not null")
    private Coordinates coordinates;
    @Column
    @Check(constraints = "creationDate is not null")
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @Column
    @Check(constraints = "description is not null")
    private String description; //Строка не может быть пустой, Поле может быть null
    @Column
    @Check(constraints = "difficulty is not null")
    private Difficulty difficulty; //Поле не может быть null
    @ManyToOne
    private Discipline discipline;
    @Column
    @Check(constraints = "minimalPoint > 0")
    private long minimalPoint; //Значение поля должно быть больше 0
    @Column
    @Check(constraints = "averagePoint > 0")
    private Double averagePoint; //Поле может быть null, Значение поля должно быть больше 0
    @ManyToOne
    private Person person;
    @Column
    private String Username;
    private boolean permission;
}