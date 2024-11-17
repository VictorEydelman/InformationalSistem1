package org.example.lab1.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Check;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "Discipline")
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Check(constraints = "name is not null")
    private String name; //Поле не может быть null, Строка не может быть пустой
    @Check(constraints = "lectureHours is not null")
    private Long lectureHours; //Поле не может быть null
}
