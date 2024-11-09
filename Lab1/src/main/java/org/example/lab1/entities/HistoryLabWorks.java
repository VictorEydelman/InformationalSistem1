package org.example.lab1.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.lab1.entities.enums.Difficulty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "HistoryLabWorks")
public class HistoryLabWorks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private long labwork_id;
    private String usernamechanger;
    private String action;
    @Column
    private String name; //Поле не может быть null, Строка не может быть пустой
    @ManyToOne
    private HistoryCoordinates coordinates;
    @Column
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @Column
    private String description; //Строка не может быть пустой, Поле может быть null
    @Column
    private Difficulty difficulty; //Поле не может быть null
    @ManyToOne
    private HistoryDiscipline discipline;
    @Column
    private long minimalPoint; //Значение поля должно быть больше 0
    @Column
    private Double averagePoint; //Поле может быть null, Значение поля должно быть больше 0
    @ManyToOne
    private HistoryPerson person;
    @Column
    private String Username;
    private boolean permission;
}
