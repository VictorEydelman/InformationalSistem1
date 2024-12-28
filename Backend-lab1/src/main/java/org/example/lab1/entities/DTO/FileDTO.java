package org.example.lab1.entities.DTO;

import lombok.Data;
import org.example.lab1.entities.enums.Difficulty;

@Data
public class FileDTO {
    public String name;
    public CoordinatesDTO coordinates;
    public String description; //Строка не может быть пустой, Поле может быть null
    public Difficulty difficulty; //Поле не может быть null
    public DisciplineDTO discipline;
    public long minimalPoint; //Значение поля должно быть больше 0
    public Double averagePoint; //Поле может быть null, Значение поля должно быть больше 0
    public PersonDTO Person;
    public boolean permission;
}
