package org.IS.lab1.entities;

import jakarta.persistence.*;
import lombok.*;
import org.IS.lab1.entities.enums.Color;
import org.IS.lab1.entities.enums.Country;
import org.hibernate.annotations.Check;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "Person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Check(constraints = "name is not null")
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Color eyeColor; //Поле может быть null
    @Check(constraints = "hairColor is not null")
    private Color hairColor; //Поле не может быть null
    @ManyToOne
    @Check(constraints = "location is not null")
    private Location location; //Поле не может быть null
    @Check(constraints = "weight > 0")
    private double weight; //Значение поля должно быть больше 0
    @Check(constraints = "nationality is not null")
    private Country nationality; //Поле не может быть null
}
