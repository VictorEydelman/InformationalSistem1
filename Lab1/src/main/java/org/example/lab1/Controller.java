package org.example.lab1;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.lab1.DTO.UserDTO;
import org.example.lab1.entities.User;
import org.example.lab1.users.AuthntificationService;
import org.example.lab1.users.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class Controller {
    private final AuthntificationService authntificationService;
    private final UserService userService;

    @PutMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<String>> reg(@Valid @RequestBody UserDTO userDTO) throws NoSuchAlgorithmException {
        User user = userService.getByUsername(userDTO.getUsername());
        if (user == null) {
            List<String> n=authntificationService.signUp(userDTO);
            System.out.println(n);
            return ResponseEntity.ok(n);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/avtorization",produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<String>> avt(@Valid @RequestBody UserDTO userDTO) throws NoSuchAlgorithmException {
        String hashedPass=HashUtil.digectPassword(userDTO.getPassword());
        User user = userService.getByUsernameAndPassword(userDTO.getUsername(),hashedPass);
        System.out.println(user);
        System.out.println(1);
        if(user != null){
            List<String> n = authntificationService.signIn(user);
            System.out.println(n);
            return ResponseEntity.ok(n);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}