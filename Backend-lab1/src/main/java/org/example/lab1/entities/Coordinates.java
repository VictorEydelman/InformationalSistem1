package org.IS.lab1.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.annotations.Check;

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
    @Check(constraints = "x > -137")
    float x; //Значение поля должно быть больше -137
    @Check(constraints = "y < 66")
    Integer y; //Максимальное значение поля: 65, Поле не может быть null
}