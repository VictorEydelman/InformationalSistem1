package org.example.lab1.users;

import lombok.AllArgsConstructor;
import org.example.lab1.DTO.UserDTO;
import org.example.lab1.HashUtil;
import org.example.lab1.entities.Role;
import org.example.lab1.entities.User;
import org.springframework.security.crypto.password.Md4PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
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
