package org.example.lab1.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.File;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "HistoryFile")
public class HistoryFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String status;
    int lenght;
    @Lob
    String jsonString;
    String username;
    byte[] file;
    String filename;
}
