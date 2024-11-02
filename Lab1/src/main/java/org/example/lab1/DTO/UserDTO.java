package org.example.lab1.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.lab1.entities.Role;

@Data
public class UserDTO {
    private String username;
    @Size(min=5)
    private String password;
    private Role role;
}
