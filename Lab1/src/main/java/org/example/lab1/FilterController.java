package org.example.lab1;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.lab1.DTO.FilterByIdDTO;
import org.example.lab1.DTO.LabWorkDTO;
import org.example.lab1.entities.LabWork;
import org.example.lab1.entities.User;
import org.example.lab1.users.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping(value = "/deleteByMinimalPoint/{minimalPoint}", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<String> deleteByMinimalPoint(@PathVariable long minimalPoint, HttpServletRequest request) {
        labWorkService.delete(labWorkService.findByMinimalPoint(minimalPoint).getId(), coordinateSevice, disciplineService, personService, locationService);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping(value = "/groupById", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<LabWork>> groupById(@Valid @RequestBody FilterByIdDTO filterByIdDTO, HttpServletRequest request) {
        return new ResponseEntity<>(labWorkService.groupById(filterByIdDTO),HttpStatus.CREATED);
    }
}
