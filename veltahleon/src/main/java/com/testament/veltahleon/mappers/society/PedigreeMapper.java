//package com.testament.veltahleon.mappers.society;
//
//import com.testament.veltahleon.dto.society.PedigreeDTO;
//import com.testament.veltahleon.enumerations.Gender;
//import com.testament.veltahleon.model.entities.society.Pedigree;
//import com.testament.veltahleon.services.ifc.society.PersonService;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//@AllArgsConstructor
//public class PedigreeMapper {
//
//    @Autowired
//    private PersonService personService;
//
//    public PedigreeDTO convertToDTO(Pedigree pedigree) {
//        return PedigreeDTO.builder()
//                .id(pedigree.getId())
//                .name(pedigree.getName())
//                .gender(String.valueOf(pedigree.getGender()))
//                .mid(personService.getPersonByID(pedigree.getMid()).getId())
//                .motherName(personService.getPersonByID(pedigree.getId()).getName())
//                .fatherName(personService.getPersonByID(pedigree.getId()).getName())
//                .fid(personService.getPersonByID(pedigree.getFid()).getId())
//                .spouses(pedigree.getPids().stream().map(id -> personService.getPersonByID(id).getName()).toList())
//                .pids(pedigree.getPids().stream().map(id -> personService.getPersonByID(id).getId()).toList())
//                .img(pedigree.getImageURL())
//                .build();
//    }
//
//    public Pedigree convertToEntity(PedigreeDTO pedigreeDTO) {
//        Pedigree pedigree = new Pedigree();
//        pedigree.setName(pedigreeDTO.getName());
//        pedigree.setFid(pedigreeDTO.getFid());
//        pedigree.setMid(pedigreeDTO.getMid());
//        pedigree.setPids(pedigreeDTO.getPids());
//        pedigree.setGender(Gender.valueOf(pedigreeDTO.getGender()));
//        pedigree.setImageURL(pedigreeDTO.getImg());
//        return pedigree;
//    }
//}
