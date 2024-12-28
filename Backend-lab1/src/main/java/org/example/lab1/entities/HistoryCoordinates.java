package org.IS.lab1.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "HistoryCoordinates")
public class HistoryCoordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    float x; //Значение поля должно быть больше -137
    Integer y; //Максимальное значение поля: 65, Поле не может быть null
}