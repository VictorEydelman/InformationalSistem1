package org.example.lab1.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "HistoryDiscipline")
public class HistoryDiscipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Long lectureHours; //Поле не может быть null
}
