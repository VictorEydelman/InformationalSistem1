package org.example.lab1.Controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.example.lab1.BuilderHistory;
import org.example.lab1.ResponceFormate.FilterGroupByIdResponce;
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
@RequestMapping("/api/filter")
public class FilterController {
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

    @DeleteMapping(value = "/deleteByMinimalPoint/{minimalPoint}", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<String> deleteByMinimalPoint(@PathVariable long minimalPoint, HttpServletRequest request) {
        BuilderHistory builderHistory =new BuilderHistory();
        LabWork labWork =labWorkService.findByMinimalPoint(minimalPoint);
        User user = userService.getByUsername(jwtService.extractUsername(jwtService.resolveToken(request)));
        builderHistory.delete(labWork,user.getUsername(),historyLabWorkService,historyCoordinateSevice,historyDisciplineService,
                historyLocationService,historyPersonService);
        labWorkService.delete(labWork.getId(), user.getUsername(), coordinateSevice, disciplineService, personService, locationService);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping(value = "/groupById", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<FilterGroupByIdResponce>> groupById(HttpServletRequest request) {
        return new ResponseEntity<>(labWorkService.groupById(),HttpStatus.CREATED);
    }
    @GetMapping(value = "/getByDescription/{description}", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<LabWork>> getByDescription(@PathVariable String description,HttpServletRequest request) {
        return new ResponseEntity<>(labWorkService.getByDescription(description),HttpStatus.CREATED);
    }
    @PostMapping(value = "/upTheDifficulty/{id}/{step}", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<String> upTheDifficulty(@PathVariable Long id,@PathVariable int step,HttpServletRequest request) {
        LabWork labWork = labWorkService.findById(id);
        while (step>0){
            labWork.setDifficulty(labWork.getDifficulty().getNextDifficulty());
            step--;
        }
        User user = userService.getByUsername(jwtService.extractUsername(jwtService.resolveToken(request)));
        BuilderHistory builderHistory =new BuilderHistory();
        builderHistory.update(labWork,user.getUsername(),historyLabWorkService,historyCoordinateSevice,historyDisciplineService,historyLocationService,historyPersonService);
        labWorkService.update(labWork,user.getUsername());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping(value = "/deleteFromDiscipline/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<String> deleteDiscipline(@PathVariable long id, HttpServletRequest request) {
        User user = userService.getByUsername(jwtService.extractUsername(jwtService.resolveToken(request)));
        LabWork labWork = labWorkService.findById(id);
        if(Objects.equals(user.getUsername(), labWorkService.findById(id).getUsername()) || (user.getRole()== Role.ROLE_ADMIN && labWork.isPermission())) {
            labWork.setDiscipline(null);
            labWorkService.update(labWork,user.getUsername());
            BuilderHistory builderHistory =new BuilderHistory();
            builderHistory.update(labWork,user.getUsername(),historyLabWorkService,historyCoordinateSevice,historyDisciplineService,historyLocationService,historyPersonService);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Нет доступа к изменению",HttpStatus.BAD_REQUEST);
        }
    }
}