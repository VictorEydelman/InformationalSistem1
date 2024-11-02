package org.example.lab1.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.lab1.DB.*;

@Data
public class LabWorkDTO {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @NotBlank
    private String name; //Поле не может быть null, Строка не может быть пустой
    private int coordinates_id;
    @Min(-137)
    private float coordinates_x;//Поле не может быть null
    @NotNull
    @Max(65)
    private Integer coordinates_y;
    @NotBlank
    private String description; //Строка не может быть пустой, Поле может быть null
    @NotNull
    private Difficulty difficulty; //Поле не может быть null
    private int discipline_id;
    @NotBlank
    private String discipline_name; //Поле может быть null
    @NotNull
    private Long discipline_lectureHours;
    @Min(1)
    private long minimalPoint; //Значение поля должно быть больше 0
    @NotNull
    @Min(1)
    private Double averagePoint; //Поле может быть null, Значение поля должно быть больше 0
    private int person_id;
    @NotBlank
    private String person_name; //Поле может быть null
    @NotNull
    private Color person_eyeColor;
    @NotNull
    private Color person_hairColor;
    private int person_location_id;
    @NotNull
    private Long person_location_x;
    @NotNull
    private Long person_location_y;
    private int person_location_z;
    @Min(1)
    private double person_weight;
    @NotNull
    private Country person_nationality;
    @Min(1)
    private long idRelatedLab;
    private boolean permission;
}