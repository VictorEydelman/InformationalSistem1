package org.example.lab1.entities.DTO;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data

public class CoordinatesDTO {
    int id;
    @Min(-137)
    float x; //Значение поля должно быть больше -137
    @NotNull
    @Max(65)
    Integer y; //Максимальное значение поля: 65, Поле не может быть null
}