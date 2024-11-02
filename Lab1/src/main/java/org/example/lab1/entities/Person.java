package org.example.lab1.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.lab1.DB.Color;
import org.example.lab1.DB.Country;

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
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Color eyeColor; //Поле может быть null
    private Color hairColor; //Поле не может быть null
    @OneToOne
    private Location location; //Поле не может быть null
    private double weight; //Значение поля должно быть больше 0
    private Country nationality; //Поле не может быть null
}
