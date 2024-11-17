package org.example.lab1.Service;

import lombok.AllArgsConstructor;
import org.example.lab1.entities.DTO.UserDTO;
import org.example.lab1.entities.Hash.HashUtil;
import org.example.lab1.entities.enums.Role;
import org.example.lab1.entities.User;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AuthntificationService {
    private final UserService userService;
    private final JWTService jwtService;

    public List<String> signUp(UserDTO userDTO) throws NoSuchAlgorithmException {
        String hashedPass=HashUtil.digectPassword(userDTO.getPassword());
        Role role=Role.ROLE_USER;
        if(userService.sizeUsers()==0){
            role = Role.ROLE_ADMIN;
        }
        var user = User.builder().username(userDTO.getUsername())
                .password(hashedPass)
                .role(role)
                .build();
        userService.save(user);
        List<String> result = new ArrayList<>();
        result.add(user.getUsername());
        result.add(jwtService.generateToken(user));
        result.add(user.getRole().name());
        return result;
    }

    public List<String> signIn(User user){
        List<String> result = new ArrayList<>();
        result.add(user.getUsername());
        result.add(jwtService.generateToken(user));
        result.add(String.valueOf(user.getRole()));
        return result;
    }
}
