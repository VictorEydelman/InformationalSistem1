package org.example.lab1;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.lab1.DB.LabWorkResponce;
import org.example.lab1.DTO.LabWorkDTO;
import org.example.lab1.DTO.UserDTO;
import org.example.lab1.entities.LabWork;
import org.example.lab1.entities.Role;
import org.example.lab1.entities.User;
import org.example.lab1.users.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.Authenticator;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/LabWork")
public class LabWorkController {
    private final LabWorkService labWorkService;
    private final CoordinateSevice coordinateSevice;
    private final DisciplineService disciplineService;
    private final PersonService personService;
    private final LocationService locationService;
    private final UserService userService;
    private final JWTService jwtService;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<String> add(@Valid @RequestBody LabWorkDTO labWorkDTO, HttpServletRequest request) {
        User user = userService.getByUsername(jwtService.extractUsername(jwtService.resolveToken(request)));
        BuilderLabworks builderLabworks = new BuilderLabworks();
        LabWork labWork = builderLabworks.add(labWorkDTO, user, coordinateSevice,disciplineService,personService,locationService);
        labWorkService.add(labWork);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<LabWorkResponce>> get(HttpServletRequest request) {
        var n = labWorkService.getAll();
        System.out.println(123456789);
        System.out.println(n);
        return ResponseEntity.ok(n);
    }

    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<String> update(@Valid @RequestBody LabWorkDTO labWorkDTO, HttpServletRequest request) {
        User user = userService.getByUsername(jwtService.extractUsername(jwtService.resolveToken(request)));
        System.out.println(123);
        if(Objects.equals(user.getUsername(), labWorkService.findUsername(labWorkDTO.getId())) || (user.getRole()== Role.ROLE_ADMIN && labWorkDTO.isPermission())) {
            BuilderLabworks builderLabworks = new BuilderLabworks();
            LabWork labWork = builderLabworks.update(labWorkDTO, user, coordinateSevice, disciplineService, personService, locationService);
            labWorkService.update(labWork);
            System.out.println(12345);
            //String username = authentication.getName();

            //System.out.println(labWork);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Нет доступа к изменению",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<String> delete(@PathVariable Long id, HttpServletRequest request) {
        User user = userService.getByUsername(jwtService.extractUsername(jwtService.resolveToken(request)));
        LabWork labWork = labWorkService.findUsername(id);
        if(Objects.equals(user.getUsername(), labWork.getUsername()) || (user.getRole()== Role.ROLE_ADMIN && labWork.isPermission())) {
            labWorkService.delete(id, coordinateSevice, disciplineService, personService, locationService);
            System.out.println(12345);
            //String username = authentication.getName();

            //System.out.println(labWork);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            System.out.println(987654);
            return new ResponseEntity<>("Нет доступа к изменению",HttpStatus.BAD_REQUEST);
        }
    }
}