package org.example.lab1.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.lab1.BuilderHistory;
import org.example.lab1.BuilderLabworks;
import org.example.lab1.entities.DTO.LabWorkDTO;
import org.example.lab1.entities.LabWork;
import org.example.lab1.entities.enums.Role;
import org.example.lab1.entities.User;
import org.example.lab1.Service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private final HistoryLabWorkService historyLabWorkService;
    private final HistoryDisciplineService historyDisciplineService;
    private final HistoryLocationService historyLocationService;
    private final HistoryPersonService historyPersonService;
    private final HistoryCoordinateSevice historyCoordinateSevice;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<String> add(@Valid @RequestBody LabWorkDTO labWorkDTO, HttpServletRequest request) {
        User user = userService.getByUsername(jwtService.extractUsername(jwtService.resolveToken(request)));
        BuilderLabworks builderLabworks = new BuilderLabworks();
        LabWork labWork = builderLabworks.add(labWorkDTO, user, coordinateSevice,disciplineService,personService,locationService);
        System.out.println(labWork);
        labWorkService.add(labWork);
        BuilderHistory builderHistory = new BuilderHistory();
        builderHistory.add(labWork,"INSERT",user.getUsername(),historyLabWorkService,historyCoordinateSevice,historyDisciplineService,historyLocationService,historyPersonService);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<LabWork>> get(HttpServletRequest request) {
        var n = labWorkService.getAll();
        System.out.println(n);
        return ResponseEntity.ok(n);
    }

    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<String> update(@RequestBody LabWorkDTO labWorkDTO, HttpServletRequest request) {
        User user = userService.getByUsername(jwtService.extractUsername(jwtService.resolveToken(request)));
        System.out.println(labWorkDTO);
        System.out.println(user);
        if(Objects.equals(user.getUsername(), labWorkService.findById(labWorkDTO.getId()).getUsername()) || (user.getRole()== Role.ROLE_ADMIN && labWorkDTO.isPermission())) {
            BuilderLabworks builderLabworks = new BuilderLabworks();
            LabWork labWork = builderLabworks.update(labWorkDTO, user,labWorkService, coordinateSevice, disciplineService, personService, locationService);
            labWorkService.update(labWork,user.getUsername());
            BuilderHistory builderHistory = new BuilderHistory();
            builderHistory.add(labWork,"UPDATE",user.getUsername(),historyLabWorkService,historyCoordinateSevice,historyDisciplineService,historyLocationService,historyPersonService);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Нет доступа к изменению",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<String> delete(@PathVariable Long id, HttpServletRequest request) {
        User user = userService.getByUsername(jwtService.extractUsername(jwtService.resolveToken(request)));
        LabWork labWork = labWorkService.findById(id);
        System.out.println(labWork);
        System.out.println(123456);
        if(Objects.equals(user.getUsername(), labWork.getUsername()) || (user.getRole()== Role.ROLE_ADMIN && labWork.isPermission())) {
            labWorkService.delete(id, user.getUsername(), coordinateSevice, disciplineService, personService, locationService);
            System.out.println(12345);
            //String username = authentication.getName();
            BuilderHistory builderHistory = new BuilderHistory();
            builderHistory.add(labWork,"INSERT",user.getUsername(),historyLabWorkService,historyCoordinateSevice,historyDisciplineService,historyLocationService,historyPersonService);
            //System.out.println(labWork);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            System.out.println(987654);
            return new ResponseEntity<>("Нет доступа к изменению",HttpStatus.BAD_REQUEST);
        }
    }
}