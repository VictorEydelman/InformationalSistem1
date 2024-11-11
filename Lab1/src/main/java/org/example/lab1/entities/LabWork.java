package org.example.lab1.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.lab1.entities.enums.Difficulty;
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
    @NotBlank
    @Column
    private String name; //Поле не может быть null, Строка не может быть пустой
    @NotNull
    @ManyToOne
    private Coordinates coordinates;
    @NotNull
    @Column
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @NotEmpty
    @Column
    private String description; //Строка не может быть пустой, Поле может быть null
    @NotNull
    @Column
    private Difficulty difficulty; //Поле не может быть null
    @ManyToOne
    private Discipline discipline;
    @Min(1)
    @Column
    private long minimalPoint; //Значение поля должно быть больше 0
    @Min(1)
    @Column
    private Double averagePoint; //Поле может быть null, Значение поля должно быть больше 0
    @ManyToOne
    private Person person;
    @Column
    private String Username;
    private boolean permission;
}