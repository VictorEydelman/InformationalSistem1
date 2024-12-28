package org.example.lab1.Service.Builder;


import org.example.lab1.Service.*;
import org.example.lab1.entities.*;
import org.example.lab1.entities.DTO.LabWorkDTO;

import java.util.Date;

public class BuilderLabworks {
    public LabWork add(LabWorkDTO labWorkDTO, User user, CoordinateSevice coordinateSevice, DisciplineService disciplineService, PersonService personService, LocationService locationService) {
        Coordinates coordinates = coordinateSevice.findByXAndY(labWorkDTO.getCoordinates().getX(), labWorkDTO.getCoordinates().getY());
        if(coordinates==null) {
            coordinates = Coordinates.builder().x(labWorkDTO.getCoordinates().getX()).y(labWorkDTO.getCoordinates().getY()).build();
            coordinateSevice.add(coordinates);
        }
        Discipline discipline=null;
        if(labWorkDTO.getDiscipline()!=null) {
            discipline = disciplineService.findByNameAndLectureHours(labWorkDTO.getDiscipline().getName(), labWorkDTO.getDiscipline().getLectureHours());
            if (discipline == null) {
                discipline = Discipline.builder().name(labWorkDTO.getDiscipline().getName())
                        .lectureHours(labWorkDTO.getDiscipline().getLectureHours()).build();
                disciplineService.add(discipline);
            }
        }
        Location location = null;
        Person person = null;
        if(labWorkDTO.getPerson()!=null){
            location = locationService.findByXAndYAndZ(labWorkDTO.getPerson().getLocation().getX(), labWorkDTO.getPerson().getLocation().getY(), labWorkDTO.getPerson().getId());
            if(location==null) {
                location = Location.builder().x(labWorkDTO.getPerson().getLocation().getX()).y(labWorkDTO.getPerson().getLocation().getY()).z(labWorkDTO.getPerson().getLocation().getZ()).build();
                locationService.add(location);
            }
            person = personService.find(labWorkDTO.getPerson().getName(), labWorkDTO.getPerson().getEyeColor(),
                    labWorkDTO.getPerson().getHairColor(),location,labWorkDTO.getPerson().getWeight(),labWorkDTO.getPerson().getNationality());
            if(person==null) {
                person = Person.builder().eyeColor(labWorkDTO.getPerson().getEyeColor())
                        .hairColor(labWorkDTO.getPerson().getHairColor()).weight(labWorkDTO.getPerson().getWeight())
                        .nationality(labWorkDTO.getPerson().getNationality()).name(labWorkDTO.getPerson().getName())
                        .location(location).build();
                if(personService.find(person.getName(),person.getEyeColor(),person.getHairColor(),person.getLocation(),
                        person.getWeight(),person.getNationality())==null) {
                    personService.add(person);
                }
            }
        }
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
        Coordinates coordinates = Coordinates.builder().x(labWorkDTO.getCoordinates().getX()).y(labWorkDTO.getCoordinates().getY()).id(labWorkDTO.getCoordinates().getId()).build();
        if(coordinates!=coordinateSevice.findById(labWorkDTO.getCoordinates().getId())){
            if(labWorkService.countByCoordinates(labWorkDTO.getCoordinates().getId())>1){
                Coordinates coordinates1 = Coordinates.builder().x(labWorkDTO.getCoordinates().getX()).y(labWorkDTO.getCoordinates().getY()).build();
                coordinateSevice.add(coordinates1);
            } else {
                coordinateSevice.update(coordinates);
            }
        }
        Discipline discipline = null;
        if(labWorkDTO.getDiscipline()!=null) {
            discipline = Discipline.builder().name(labWorkDTO.getDiscipline().getName()).lectureHours(labWorkDTO.getDiscipline().getLectureHours()).id(labWorkDTO.getDiscipline().getId()).build();
            if (labWorkDTO.getDiscipline().getId() == 0) {
                disciplineService.add(discipline);
            } else if (discipline != disciplineService.findById(labWorkDTO.getDiscipline().getId())) {
                if (labWorkService.countByDiscipline(labWorkDTO.getDiscipline().getId()) > 1) {
                    Discipline discipline1 = Discipline.builder().name(labWorkDTO.getDiscipline().getName())
                            .lectureHours(labWorkDTO.getDiscipline().getLectureHours()).build();
                    disciplineService.add(discipline1);
                } else {
                    disciplineService.update(discipline);
                }
            }
        }
        Location location = null;
        Person person = null;
        if(labWorkDTO.getPerson()!=null) {
            if(labWorkDTO.getPerson().getId()!=0) {
                location = Location.builder().x(labWorkDTO.getPerson().getLocation().getX()).y(labWorkDTO.getPerson().getLocation().getY()).id(labWorkDTO.getPerson().getLocation().getId()).build();
                if (location != locationService.findById(labWorkDTO.getPerson().getLocation().getId())) {
                    if (labWorkService.countByLocation(labWorkDTO.getPerson().getLocation().getId()) > 1) {
                        Location location1 = Location.builder().x(labWorkDTO.getPerson().getLocation().getX())
                                .y(labWorkDTO.getPerson().getLocation().getY()).z(labWorkDTO.getPerson().getLocation().getZ()).build();
                        locationService.add(location1);
                    } else {
                        locationService.update(location);
                    }
                }

                person = Person.builder().name(labWorkDTO.getName()).eyeColor(labWorkDTO.getPerson().getEyeColor())
                        .hairColor(labWorkDTO.getPerson().getHairColor()).weight(labWorkDTO.getPerson().getWeight())
                        .nationality(labWorkDTO.getPerson().getNationality()).location(location).id(labWorkDTO.getPerson().getId()).build();
                if (person != personService.findById(labWorkDTO.getPerson().getId())) {
                    if (labWorkService.countByPerson(labWorkDTO.getPerson().getId()) > 1) {
                        Person person1 = Person.builder().name(labWorkDTO.getName()).eyeColor(labWorkDTO.getPerson().getEyeColor())
                                .hairColor(labWorkDTO.getPerson().getHairColor()).weight(labWorkDTO.getPerson().getWeight())
                                .nationality(labWorkDTO.getPerson().getNationality()).location(location).build();
                        if(personService.find(person.getName(),person.getEyeColor(),person.getHairColor(),person.getLocation(),
                                person.getWeight(),person.getNationality())==null) {
                            personService.add(person1);
                        }
                    } else {
                        personService.update(person);
                    }
                }
            } else {
                location = Location.builder().x(labWorkDTO.getPerson().getLocation().getX())
                        .y(labWorkDTO.getPerson().getLocation().getY()).z(labWorkDTO.getPerson().getLocation().getZ()).build();
                locationService.add(location);

                person = Person.builder().name(labWorkDTO.getName()).eyeColor(labWorkDTO.getPerson().getEyeColor())
                        .hairColor(labWorkDTO.getPerson().getHairColor()).weight(labWorkDTO.getPerson().getWeight())
                        .nationality(labWorkDTO.getPerson().getNationality()).location(location).build();
                if(personService.find(person.getName(),person.getEyeColor(),person.getHairColor(),person.getLocation(),
                        person.getWeight(),person.getNationality())==null) {
                    personService.add(person);
                }
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