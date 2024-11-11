package org.example.lab1.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.lab1.entities.enums.Color;
import org.example.lab1.entities.enums.Country;

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
    @NotBlank
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Color eyeColor; //Поле может быть null
    @NotNull
    private Color hairColor; //Поле не может быть null
    @ManyToOne
    @NotNull
    private Location location; //Поле не может быть null
    @Min(0)
    private double weight; //Значение поля должно быть больше 0
    @NotNull
    private Country nationality; //Поле не может быть null
}
