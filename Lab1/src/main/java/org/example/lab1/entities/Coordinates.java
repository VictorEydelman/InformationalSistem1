package org.example.lab1.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "Coordinates")
public class Coordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Min(-137)
    float x; //Значение поля должно быть больше -137
    @Max(65)
    Integer y; //Максимальное значение поля: 65, Поле не может быть null
}