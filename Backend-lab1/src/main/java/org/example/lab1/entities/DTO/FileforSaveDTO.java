package org.example.lab1.entities.DTO;

import io.minio.Result;
import io.minio.messages.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
@Data
@AllArgsConstructor
public class FileforSaveDTO {
    private String name;
    private long size;
}
