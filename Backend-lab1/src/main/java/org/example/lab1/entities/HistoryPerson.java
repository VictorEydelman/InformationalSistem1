package org.IS.lab1.entities;

import jakarta.persistence.*;
import lombok.*;
import org.IS.lab1.entities.enums.Color;
import org.IS.lab1.entities.enums.Country;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "HistoryPerson")
public class HistoryPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Color eyeColor; //Поле может быть null
    private Color hairColor; //Поле не может быть null
    @ManyToOne(cascade=CascadeType.ALL)
    private HistoryLocation location; //Поле не может быть null
    private double weight; //Значение поля должно быть больше 0
    private Country nationality; //Поле не может быть null
}
