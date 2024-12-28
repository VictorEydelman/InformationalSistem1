package org.IS.lab1.Controller;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.IS.lab1.Service.*;
import org.IS.lab1.Service.Builder.BuilderHistory;
import org.IS.lab1.Service.Builder.BuilderLabworks;
import org.IS.lab1.entities.*;
import org.IS.lab1.entities.DTO.FileDTO;
import org.IS.lab1.entities.enums.Role;
import org.example.lab1.entities.*;
import org.IS.lab1.entities.DTO.LabWorkDTO;
import org.example.lab1.Service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    private final HistoryLabWorkService historyLabWorkService;
    private final HistoryDisciplineService historyDisciplineService;
    private final HistoryLocationService historyLocationService;
    private final HistoryPersonService historyPersonService;
    private final HistoryCoordinateSevice historyCoordinateSevice;
    private final HistoryFileService historyFileService;
    private final FileService fileService;
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<String> add(@Valid @RequestBody LabWorkDTO labWorkDTO, HttpServletRequest request) {
        try {
            User user = userService.getByUsername(jwtService.extractUsername(jwtService.resolveToken(request)));
            BuilderLabworks builderLabworks = new BuilderLabworks();
            LabWork labWork = builderLabworks.add(labWorkDTO, user, coordinateSevice, disciplineService, personService, locationService);
            labWorkService.add(labWork);
            BuilderHistory builderHistory = new BuilderHistory();
            builderHistory.add(labWork, "INSERT", user.getUsername(), historyLabWorkService, historyCoordinateSevice, historyDisciplineService, historyLocationService, historyPersonService);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Ошибка добавления",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<LabWork>> get(HttpServletRequest request) {
        var n = labWorkService.getAll();
        return ResponseEntity.ok(n);
    }

    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<String> update(@RequestBody LabWorkDTO labWorkDTO, HttpServletRequest request) {
        User user = userService.getByUsername(jwtService.extractUsername(jwtService.resolveToken(request)));
        if(Objects.equals(user.getUsername(), labWorkService.findById(labWorkDTO.getId()).getUsername()) || (user.getRole()== Role.ROLE_ADMIN && labWorkDTO.isPermission())) {
            BuilderLabworks builderLabworks = new BuilderLabworks();
            System.out.println(labWorkDTO);
            LabWork labWork = builderLabworks.update(labWorkDTO, user,labWorkService, coordinateSevice, disciplineService, personService, locationService);
            System.out.println(labWork);
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
        if(Objects.equals(user.getUsername(), labWork.getUsername()) || (user.getRole()== Role.ROLE_ADMIN && labWork.isPermission())) {
            labWorkService.delete(id, user.getUsername(), coordinateSevice, disciplineService, personService, locationService);
            BuilderHistory builderHistory = new BuilderHistory();
            builderHistory.add(labWork,"INSERT",user.getUsername(),historyLabWorkService,historyCoordinateSevice,historyDisciplineService,historyLocationService,historyPersonService);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Нет доступа к изменению",HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(value = "/addfile", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<String> addfile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        StringBuilder jsonStringBuilder = new StringBuilder();
        System.out.println(file);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                jsonStringBuilder.append(line);
            }

            // Обработка JSON-строки
            String jsonString = jsonStringBuilder.toString();

            System.out.println(jsonString);
            // Вывод данных
            User user = userService.getByUsername(jwtService.extractUsername(jwtService.resolveToken(request)));
            Gson gson = new Gson();
            FileDTO[] items = gson.fromJson(jsonString, FileDTO[].class);
            LabWork[] labWorks = new LabWork[items.length];
            Coordinates[] coordinates = new Coordinates[items.length];
            Person[] person = new Person[items.length];
            Location[] locations = new Location[items.length];
            Discipline[] disciplines = new Discipline[items.length];
            System.out.println(129213891);
            System.out.println(items.length);
            String status="";
            // Парсинг JSON-строки
            boolean t=true;
            try {
                for (int i=0;i<items.length;i++) {

                    var item=items[i];
                    if(item.coordinates.getY()==null ||(item.discipline.getName() == null) || (item.discipline.getLectureHours() == null)
                    ||(item.Person.getLocation().getX()==null) || (item.Person.getLocation().getY()==null) || (item.getPerson().getLocation()==null)
                    || item.getPerson().getHairColor()==null || item.getPerson().getEyeColor()==null || item.getPerson().getNationality()==null){
                        status="не успешно";
                        break;
                    }
                    System.out.println(i);
                    coordinates[i] = Coordinates.builder().x(item.coordinates.getX()).y(item.coordinates.getY()).build();
                    System.out.println(coordinates[i]);
                    if(item.discipline!=null) {

                        disciplines[i] = Discipline.builder().name(item.discipline.getName())
                                .lectureHours(item.discipline.getLectureHours()).build();
                    } else {
                        disciplines[i]=null;
                    }
                    System.out.println(disciplines[i]);

                    locations[i] = Location.builder().x(item.Person.getLocation().getX())
                            .y(item.Person.getLocation().getY()).z(item.Person.getLocation().getZ()).build();
                    System.out.println(locations[i]);
                    if(item.Person!=null) {
                        person[i] = Person.builder().name(item.Person.getName()).hairColor(item.Person.getHairColor())
                                .eyeColor(item.Person.getEyeColor()).weight(item.Person.getWeight())
                                .location(locations[i]).nationality(item.Person.getNationality()).build();
                    } else {
                        person[i]=null;
                    }
                    System.out.println(person[i]);
                    labWorks[i] = LabWork.builder().name(item.name).coordinates(coordinates[i])
                            .description(item.description).difficulty(item.difficulty)
                            .discipline(disciplines[i]).minimalPoint(item.minimalPoint)
                            .averagePoint(item.averagePoint).person(person[i]).permission(item.permission)
                            .Username(user.getUsername()).creationDate(new Date())
                            .build();
                    System.out.println(1);
                }

            } catch (NullPointerException e){
                status="не успешно";

            } catch (Exception e){
                status="не успешно";
            }
            System.out.println(status);
            if(!status.equals("не успешно")){
                status = labWorkService.saveAll(coordinates, locations, person, disciplines, labWorks,
                        coordinateSevice, disciplineService, locationService, personService,file,user,fileService);
            }
            HistoryFile historyFile = HistoryFile.builder().lenght(items.length)
                    .status(status)
                    .jsonString(jsonString).username(user.getUsername()).build();
            historyFileService.add(historyFile);
            if(status.equals("успешно")){
                return ResponseEntity.ok("File Content: " + jsonString);
            } else {
                return ResponseEntity.badRequest().body("Error");
            }
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error reading file: " + e.getMessage());
        }
    }
    @GetMapping(value = "/getFiles", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<HistoryFile>> getFiles(HttpServletRequest request) {
        User user = userService.getByUsername(jwtService.extractUsername(jwtService.resolveToken(request)));
        List<HistoryFile> n;
        if (user.getRole()==Role.ROLE_ADMIN) {
            n = historyFileService.getAll();
        } else {
            n = historyFileService.getbyuser(user);
        }
        return ResponseEntity.ok(n);
    }
}