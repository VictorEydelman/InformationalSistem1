package org.IS.lab1.Service.Builder;

import org.IS.lab1.Service.*;
import org.IS.lab1.entities.*;
import org.example.lab1.entities.*;
import org.example.lab1.Service.*;

public class BuilderHistory {
    public void add(LabWork labWork, String action, String username, HistoryLabWorkService historyLabWorkService, HistoryCoordinateSevice historyCoordinateSevice, HistoryDisciplineService historyDisciplineService, HistoryLocationService historyLocationService, HistoryPersonService historyPersonService){
        HistoryCoordinates historyCoordinates = HistoryCoordinates.builder()
                .x(labWork.getCoordinates().getX())
                .y(labWork.getCoordinates().getY()).build();
        HistoryDiscipline historyDiscipline =null;
        if(labWork.getDiscipline()!=null) {
            historyDiscipline = HistoryDiscipline.builder()
                    .name(labWork.getDiscipline().getName())
                    .lectureHours(labWork.getDiscipline().getLectureHours()).build();
        }
        HistoryLocation historyLocation = null;
        if(labWork.getPerson()!=null){
            historyLocation = HistoryLocation.builder()
                    .x(labWork.getPerson().getLocation().getX())
                    .y(labWork.getPerson().getLocation().getY()).z(labWork.getPerson().getLocation().getZ()).build();
        }
        HistoryPerson historyPerson = null;
        if(labWork.getPerson()!=null) {
            historyPerson = HistoryPerson.builder()
                    .name(labWork.getPerson().getName()).eyeColor(labWork.getPerson().getEyeColor())
                    .hairColor(labWork.getPerson().getHairColor()).location(historyLocation)
                    .weight(labWork.getPerson().getWeight()).nationality(labWork.getPerson().getNationality()).build();
        }
        historyCoordinateSevice.add(historyCoordinates);
        if(labWork.getPerson()!=null) {
            historyLocationService.add(historyLocation);
        }
        if(labWork.getDiscipline()!=null) {
            historyDisciplineService.add(historyDiscipline);
        }
        if(labWork.getPerson()!=null) {
            historyPersonService.add(historyPerson);
        }
        historyLabWorkService.add(HistoryLabWorks.builder().labwork_id(labWork.getId())
                .usernamechanger(username).action(action)
                .name(labWork.getName()).coordinates(historyCoordinates).creationDate(labWork.getCreationDate())
                .description(labWork.getDescription()).difficulty(labWork.getDifficulty())
                .discipline(historyDiscipline).minimalPoint(labWork.getMinimalPoint())
                .averagePoint(labWork.getAveragePoint()).person(historyPerson).build());
    }
}