//package com.testament.veltahleon.mappers.places;
//
//import com.testament.veltahleon.dto.places.CapitalDTO;
//import com.testament.veltahleon.model.entities.places.Capital;
//import com.testament.veltahleon.services.ifc.places.NationService;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//@AllArgsConstructor
//public class CapitalMapper {
//
//    @Autowired
//    private NationService nationService;
//
//    public CapitalDTO convertToDTO(Capital capital) {
//        return CapitalDTO.builder()
//                .id(capital.getId())
//                .name(capital.getName())
//                .description(capital.getDescription())
//                .nation(capital.getNationDetails().getNation().getName())
//                .build();
//    }
//
//    public Capital convertToEntity(CapitalDTO capitalDTO) {
//        Capital capital = new Capital();
//        capital.setName(capitalDTO.getName());
//        capital.setDescription(capitalDTO.getDescription());
//        capital.setNationDetails(nationService.getNationByName(capitalDTO.getNation()));
//        return capital;
//    }
//}
