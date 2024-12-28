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
@Table(name = "Locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Check(constraints = "x is not null")
    private Long x; //Поле не может быть null
    @Check(constraints = "y is not null")
    private Long y; //Поле не может быть null
    private int z;
}
