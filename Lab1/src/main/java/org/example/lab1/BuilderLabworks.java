package org.example.lab1;

import org.example.lab1.entities.DTO.LabWorkDTO;
import org.example.lab1.entities.*;
import org.example.lab1.Service.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BuilderLabworks {

    public LabWork add(LabWorkDTO labWorkDTO, User user, CoordinateSevice coordinateSevice, DisciplineService disciplineService, PersonService personService, LocationService locationService) {
        Location location = locationService.findByXAndYAndZ(labWorkDTO.getPerson_location_x(), labWorkDTO.getPerson_location_y(), labWorkDTO.getPerson_location_z());
        if(location==null) {
            location = Location.builder().x(labWorkDTO.getPerson_location_x()).y(labWorkDTO.getPerson_location_y()).z(labWorkDTO.getPerson_location_z()).build();
            locationService.add(location);
        }
        Coordinates coordinates = coordinateSevice.findByXAndY(labWorkDTO.getCoordinates_x(), labWorkDTO.getCoordinates_y());
        if(coordinates==null) {
            coordinates = Coordinates.builder().x(labWorkDTO.getCoordinates_x()).y(labWorkDTO.getCoordinates_y()).build();
            coordinateSevice.add(coordinates);
        }
        Discipline discipline = disciplineService.findByNameAndLectureHours(labWorkDTO.getDiscipline_name(), labWorkDTO.getDiscipline_lectureHours());
        if(discipline==null) {
            discipline = Discipline.builder().name(labWorkDTO.getDiscipline_name())
                    .lectureHours(labWorkDTO.getDiscipline_lectureHours()).build();
            disciplineService.add(discipline);
        }
        Person person = personService.find(labWorkDTO.getPerson_name(), labWorkDTO.getPerson_eyeColor(),
                labWorkDTO.getPerson_hairColor(),location,labWorkDTO.getPerson_weight(),labWorkDTO.getPerson_nationality());
        if(person==null) {
            person = Person.builder().eyeColor(labWorkDTO.getPerson_eyeColor())
                    .hairColor(labWorkDTO.getPerson_hairColor()).weight(labWorkDTO.getPerson_weight())
                    .nationality(labWorkDTO.getPerson_nationality()).name(labWorkDTO.getPerson_name())
                    .location(location).build();
            personService.add(person);
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
                .permission(labWorkDTO.isPermission())
                .build();
    }
    public LabWork update(LabWorkDTO labWorkDTO, User user, LabWorkService labWorkService, CoordinateSevice coordinateSevice, DisciplineService disciplineService, PersonService personService, LocationService locationService) {
        Coordinates coordinates = Coordinates.builder().x(labWorkDTO.getCoordinates_x()).y(labWorkDTO.getCoordinates_y()).id(labWorkDTO.getCoordinates_id()).build();
        if(coordinates!=coordinateSevice.findById(labWorkDTO.getCoordinates_id())){
            if(labWorkService.countByCoordinates(labWorkDTO.getCoordinates_id())>1){
                Coordinates coordinates1 = Coordinates.builder().x(labWorkDTO.getCoordinates_x()).y(labWorkDTO.getCoordinates_y()).build();
                coordinateSevice.add(coordinates1);
            } else {
                coordinateSevice.update(coordinates);
            }
        }
        Discipline discipline = Discipline.builder().name(labWorkDTO.getDiscipline_name()).lectureHours(labWorkDTO.getDiscipline_lectureHours()).id(labWorkDTO.getDiscipline_id()).build();
        if(labWorkDTO.getDiscipline_id()==0){
            disciplineService.add(discipline);
        }else if(discipline!=disciplineService.findById(labWorkDTO.getDiscipline_id())){
            if(labWorkService.countByDiscipline(labWorkDTO.getDiscipline_id())>1){
                Discipline discipline1 = Discipline.builder().name(labWorkDTO.getDiscipline_name())
                        .lectureHours(labWorkDTO.getDiscipline_lectureHours()).build();
                disciplineService.add(discipline1);
            } else {
                disciplineService.update(discipline);
            }
        }
        Location location = Location.builder().x(labWorkDTO.getPerson_location_x()).y(labWorkDTO.getPerson_location_y()).id(labWorkDTO.getPerson_location_id()).build();
        if(location!=locationService.findById(labWorkDTO.getPerson_location_id())){
            if(labWorkService.countByLocation(labWorkDTO.getPerson_location_id())>1){
                Location location1 = Location.builder().x(labWorkDTO.getPerson_location_x())
                        .y(labWorkDTO.getPerson_location_y()).z(labWorkDTO.getPerson_location_z()).build();
                locationService.add(location1);
            } else {
                locationService.update(location);
            }
        }

        Person person = Person.builder().eyeColor(labWorkDTO.getPerson_eyeColor())
                .hairColor(labWorkDTO.getPerson_hairColor()).weight(labWorkDTO.getPerson_weight())
                .nationality(labWorkDTO.getPerson_nationality()).location(location).id(labWorkDTO.getPerson_id()).build();
        if(person!=personService.findById(labWorkDTO.getPerson_id())){
            if(labWorkService.countByPerson(labWorkDTO.getPerson_location_id())>1){
                Person person1 = Person.builder().eyeColor(labWorkDTO.getPerson_eyeColor())
                        .hairColor(labWorkDTO.getPerson_hairColor()).weight(labWorkDTO.getPerson_weight())
                        .nationality(labWorkDTO.getPerson_nationality()).location(location).build();
                personService.add(person1);
            } else {
                personService.update(person);
            }
        }
        return LabWork.builder().name(labWorkDTO.getName()).id(labWorkDTO.getId())
                .coordinates(coordinates)
                .creationDate(labWorkDTO.getCreation_date())
                .description(labWorkDTO.getDescription())
                .difficulty(labWorkDTO.getDifficulty())
                .discipline(discipline)
                .minimalPoint(labWorkDTO.getMinimalPoint())
                .averagePoint(labWorkDTO.getAveragePoint())
                .person(person)
                .Username(user.getUsername())
                .permission(labWorkDTO.isPermission())
                .build();
    }
}