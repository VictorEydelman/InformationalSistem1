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
@Table(name = "HistoryLocation")
public class HistoryLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private Long x; //Поле не может быть null
    private Long y; //Поле не может быть null
    private int z;
}
