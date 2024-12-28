package org.example.lab1.entities.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.lab1.entities.enums.Difficulty;

import java.util.Date;

@Data
public class LabWorkDTO {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @NotBlank
    private String name; //Поле не может быть null, Строка не может быть пустой
    @NotNull
    private CoordinatesDTO coordinates;
    @NotBlank
    private String description; //Строка не может быть пустой, Поле может быть null
    @NotNull
    private Difficulty difficulty; //Поле не может быть null
    private DisciplineDTO discipline;
    @Min(1)
    private long minimalPoint; //Значение поля должно быть больше 0
    @NotNull
    @Min(1)
    private Double averagePoint; //Поле может быть null, Значение поля должно быть больше 0
    private PersonDTO person;
    private boolean permission;
    private Date creation_date;
}