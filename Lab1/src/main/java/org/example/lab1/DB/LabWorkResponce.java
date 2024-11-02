package org.example.lab1.DB;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.lab1.entities.Coordinates;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LabWorkResponce {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private int coordinates_id;
    private float coordinates_x;//Поле не может быть null
    private Integer coordinates_y;
    private String description; //Строка не может быть пустой, Поле может быть null
    private Difficulty difficulty; //Поле не может быть null
    private int discipline_id;
    private String discipline_name; //Поле может быть null
    private Long discipline_lectureHours;
    private long minimalPoint; //Значение поля должно быть больше 0
    private Double averagePoint; //Поле может быть null, Значение поля должно быть больше 0
    private String person_name; //Поле может быть null
    private Color person_eyeColor;
    private Color person_hairColor;
    private Long person_location_x;
    private Long person_location_y;
    private int person_location_z;
    private double person_weight;
    private Country person_nationality;
    private long idRelatedLab;
    private boolean permission;
}
