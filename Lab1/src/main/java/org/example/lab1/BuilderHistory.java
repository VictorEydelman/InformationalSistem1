package org.example.lab1;

import org.example.lab1.entities.*;
import org.example.lab1.Service.*;

public class BuilderHistory {

    public void add(LabWork labWork, String username, HistoryLabWorkService historyLabWorkService, HistoryCoordinateSevice historyCoordinateSevice, HistoryDisciplineService historyDisciplineService, HistoryLocationService historyLocationService, HistoryPersonService historyPersonService){
        HistoryCoordinates historyCoordinates = HistoryCoordinates.builder()
                .id(labWork.getCoordinates().getId()).x(labWork.getCoordinates().getX())
                .y(labWork.getCoordinates().getY()).build();
        HistoryDiscipline historyDiscipline = HistoryDiscipline.builder()
                .id(labWork.getDiscipline().getId()).name(labWork.getDiscipline().getName())
                .lectureHours(labWork.getDiscipline().getLectureHours()).build();
        HistoryLocation historyLocation = HistoryLocation.builder()
                .id(labWork.getPerson().getLocation().getId()).x(labWork.getPerson().getLocation().getX())
                .y(labWork.getPerson().getLocation().getY()).z(labWork.getPerson().getLocation().getZ()).build();
        HistoryPerson historyPerson = HistoryPerson.builder().id(labWork.getPerson().getId())
                .name(labWork.getPerson().getName()).eyeColor(labWork.getPerson().getEyeColor())
                .hairColor(labWork.getPerson().getHairColor()).location(historyLocation)
                .weight(labWork.getPerson().getWeight()).nationality(labWork.getPerson().getNationality()).build();
        historyCoordinateSevice.add(historyCoordinates);
        historyLocationService.add(historyLocation);
        historyDisciplineService.add(historyDiscipline);
        historyPersonService.add(historyPerson);
        historyLabWorkService.add(HistoryLabWorks.builder().labwork_id(labWork.getId())
                .usernamechanger(username).action("INSERT")
                .name(labWork.getName()).coordinates(historyCoordinates).creationDate(labWork.getCreationDate())
                .description(labWork.getDescription()).difficulty(labWork.getDifficulty())
                .discipline(historyDiscipline).minimalPoint(labWork.getMinimalPoint())
                .averagePoint(labWork.getAveragePoint()).person(historyPerson).build());
    }
    public void update(LabWork labWork, String username, HistoryLabWorkService historyLabWorkService, HistoryCoordinateSevice historyCoordinateSevice, HistoryDisciplineService historyDisciplineService, HistoryLocationService historyLocationService, HistoryPersonService historyPersonService){
        HistoryCoordinates historyCoordinates = HistoryCoordinates.builder()
                .id(labWork.getCoordinates().getId()).x(labWork.getCoordinates().getX())
                .y(labWork.getCoordinates().getY()).build();
        HistoryDiscipline historyDiscipline = HistoryDiscipline.builder()
                .id(labWork.getDiscipline().getId()).name(labWork.getDiscipline().getName())
                .lectureHours(labWork.getDiscipline().getLectureHours()).build();
        HistoryLocation historyLocation = HistoryLocation.builder()
                .id(labWork.getPerson().getLocation().getId()).x(labWork.getPerson().getLocation().getX())
                .y(labWork.getPerson().getLocation().getY()).z(labWork.getPerson().getLocation().getZ()).build();
        HistoryPerson historyPerson = HistoryPerson.builder().id(labWork.getPerson().getId())
                .name(labWork.getPerson().getName()).eyeColor(labWork.getPerson().getEyeColor())
                .hairColor(labWork.getPerson().getHairColor()).location(historyLocation)
                .weight(labWork.getPerson().getWeight()).nationality(labWork.getPerson().getNationality()).build();
        historyCoordinateSevice.add(historyCoordinates);
        historyDisciplineService.add(historyDiscipline);
        historyLocationService.add(historyLocation);
        historyPersonService.add(historyPerson);
        historyLabWorkService.add(HistoryLabWorks.builder().labwork_id(labWork.getId())
                .usernamechanger(username).action("UPDATE")
                .name(labWork.getName()).coordinates(historyCoordinates).creationDate(labWork.getCreationDate())
                .description(labWork.getDescription()).difficulty(labWork.getDifficulty())
                .discipline(historyDiscipline).minimalPoint(labWork.getMinimalPoint())
                .averagePoint(labWork.getAveragePoint()).person(historyPerson).build());
    }
    public void delete(LabWork labWork, String username, HistoryLabWorkService historyLabWorkService, HistoryCoordinateSevice historyCoordinateSevice, HistoryDisciplineService historyDisciplineService, HistoryLocationService historyLocationService, HistoryPersonService historyPersonService){
        HistoryCoordinates historyCoordinates = HistoryCoordinates.builder()
                .id(labWork.getCoordinates().getId()).x(labWork.getCoordinates().getX())
                .y(labWork.getCoordinates().getY()).build();
        HistoryDiscipline historyDiscipline = HistoryDiscipline.builder()
                .id(labWork.getDiscipline().getId()).name(labWork.getDiscipline().getName())
                .lectureHours(labWork.getDiscipline().getLectureHours()).build();
        HistoryLocation historyLocation = HistoryLocation.builder()
                .id(labWork.getPerson().getLocation().getId()).x(labWork.getPerson().getLocation().getX())
                .y(labWork.getPerson().getLocation().getY()).z(labWork.getPerson().getLocation().getZ()).build();
        HistoryPerson historyPerson = HistoryPerson.builder().id(labWork.getPerson().getId())
                .name(labWork.getPerson().getName()).eyeColor(labWork.getPerson().getEyeColor())
                .hairColor(labWork.getPerson().getHairColor()).location(historyLocation)
                .weight(labWork.getPerson().getWeight()).nationality(labWork.getPerson().getNationality()).build();
        historyCoordinateSevice.add(historyCoordinates);
        historyDisciplineService.add(historyDiscipline);
        historyLocationService.add(historyLocation);
        historyPersonService.add(historyPerson);
        historyLabWorkService.add(HistoryLabWorks.builder().labwork_id(labWork.getId())
                .usernamechanger(username).action("DELETE")
                .name(labWork.getName()).coordinates(historyCoordinates).creationDate(labWork.getCreationDate())
                .description(labWork.getDescription()).difficulty(labWork.getDifficulty())
                .discipline(historyDiscipline).minimalPoint(labWork.getMinimalPoint())
                .averagePoint(labWork.getAveragePoint()).person(historyPerson).build());
    }
}