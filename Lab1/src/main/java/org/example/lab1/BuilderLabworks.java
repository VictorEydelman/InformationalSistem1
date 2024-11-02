package org.example.lab1;

import org.example.lab1.DTO.LabWorkDTO;
import org.example.lab1.entities.*;
import org.example.lab1.users.CoordinateSevice;
import org.example.lab1.users.DisciplineService;
import org.example.lab1.users.LocationService;
import org.example.lab1.users.PersonService;

import java.util.Date;

public class BuilderLabworks {

    public LabWork add(LabWorkDTO labWorkDTO, User user, CoordinateSevice coordinateSevice, DisciplineService disciplineService, PersonService personService, LocationService locationService) {
        Location location = Location.builder().x(labWorkDTO.getPerson_location_x()).y(labWorkDTO.getPerson_location_y()).z(labWorkDTO.getPerson_location_z()).build();
        locationService.add(location);
        Coordinates coordinates = Coordinates.builder().x(labWorkDTO.getCoordinates_x()).y(labWorkDTO.getCoordinates_y()).build();
        coordinateSevice.add(coordinates);
        Discipline discipline = Discipline.builder().name(labWorkDTO.getDiscipline_name()).lectureHours(labWorkDTO.getDiscipline_lectureHours()).build();
        disciplineService.add(discipline);
        System.out.println(location);
        Person person = Person.builder().eyeColor(labWorkDTO.getPerson_eyeColor())
                .hairColor(labWorkDTO.getPerson_hairColor()).weight(labWorkDTO.getPerson_weight())
                .nationality(labWorkDTO.getPerson_nationality()).name(labWorkDTO.getPerson_name())
                .location(location).build();
        System.out.println(person);
        personService.add(person);
        return LabWork.builder().name(labWorkDTO.getName())
                .coordinates(coordinates)
                .creationDate(new Date())
                .description(labWorkDTO.getDescription())
                .difficulty(labWorkDTO.getDifficulty())
                .discipline(discipline)
                .minimalPoint(labWorkDTO.getMinimalPoint())
                .averagePoint(labWorkDTO.getAveragePoint())
                .person(person)
                .Username(user.getUsername())
                .idRelatedLab(labWorkDTO.getIdRelatedLab())
                .permission(labWorkDTO.isPermission())
                .build();
    }
    public LabWork update(LabWorkDTO labWorkDTO, User user, CoordinateSevice coordinateSevice, DisciplineService disciplineService, PersonService personService, LocationService locationService) {
        System.out.println(labWorkDTO.getCoordinates_x()+" "+ labWorkDTO.getCoordinates_y());
        Coordinates coordinates = Coordinates.builder().x(labWorkDTO.getCoordinates_x()).y(labWorkDTO.getCoordinates_y()).id(labWorkDTO.getCoordinates_id()).build();
        coordinateSevice.update(coordinates);
        Discipline discipline = Discipline.builder().name(labWorkDTO.getDiscipline_name()).lectureHours(labWorkDTO.getDiscipline_lectureHours()).id(labWorkDTO.getDiscipline_id()).build();
        disciplineService.update(discipline);
        Location location = Location.builder().x(labWorkDTO.getPerson_location_x()).y(labWorkDTO.getPerson_location_y()).id(labWorkDTO.getPerson_location_id()).build();
        locationService.update(location);
        Person person = Person.builder().eyeColor(labWorkDTO.getPerson_eyeColor())
                .hairColor(labWorkDTO.getPerson_hairColor()).weight(labWorkDTO.getPerson_weight())
                .nationality(labWorkDTO.getPerson_nationality()).location(location).id(labWorkDTO.getPerson_id()).build();
        personService.update(person);
        return LabWork.builder().name(labWorkDTO.getName()).id(labWorkDTO.getId())
                .coordinates(coordinates)
                .creationDate(new Date())
                .description(labWorkDTO.getDescription())
                .difficulty(labWorkDTO.getDifficulty())
                .discipline(discipline)
                .minimalPoint(labWorkDTO.getMinimalPoint())
                .averagePoint(labWorkDTO.getAveragePoint())
                .person(person)
                .Username(user.getUsername())
                .idRelatedLab(labWorkDTO.getIdRelatedLab())
                .permission(labWorkDTO.isPermission())
                .build();
    }
}