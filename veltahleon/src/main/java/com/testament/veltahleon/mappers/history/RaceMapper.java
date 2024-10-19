//package com.testament.veltahleon.mappers.history;
//
//import com.testament.veltahleon.dto.history.RaceDTO;
//import com.testament.veltahleon.model.entities.history.Race;
//import com.testament.veltahleon.model.entities.history.SubRace;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Component;
//
//@Component
//@AllArgsConstructor
//public class RaceMapper {
//
//    public RaceDTO convertToDTO(Race race) {
//        return RaceDTO.builder()
//                .id(race.getId())
//                .name(race.getName())
//                .description(race.getDescription())
//                .subRaces(race.getSubRaces().stream().map(SubRace::getName).toList())
//                .build();
//    }
//
//    public Race convertToEntity(RaceDTO raceDTO) {
//        Race race = new Race();
//        race.setId(raceDTO.getId());
//        race.setDescription(raceDTO.getDescription());
//        race.setImageURL(raceDTO.getImageURL());
//        //race.setSubRaces(raceDTO.getSubRaces());
//        return race;
//    }
//}
