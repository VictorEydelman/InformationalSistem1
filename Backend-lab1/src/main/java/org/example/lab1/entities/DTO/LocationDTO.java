package org.example.lab1.entities.DTO;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
public class LocationDTO {
    int id;
    @NotNull
    private Long x; //Поле не может быть null
    @NotNull
    private Long y; //Поле не может быть null
    private int z;
}
