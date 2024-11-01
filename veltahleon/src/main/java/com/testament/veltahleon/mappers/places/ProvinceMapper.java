//package com.testament.veltahleon.mappers.places;
//
//import com.testament.veltahleon.dto.places.ProvinceDTO;
//import com.testament.veltahleon.facades.places.ProvinceFacade;
//import com.testament.veltahleon.model.entities.places.Province;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//@AllArgsConstructor
//public class ProvinceMapper {
//
//    @Autowired
//    private ProvinceFacade provinceFacade;
//
//    public ProvinceDTO convertToDTO(Province province) {
//        return ProvinceDTO.builder()
//                .id(province.getId())
//                .name(province.getName())
//                .description(province.getDescription())
//                .nation(province.getNation().getName())
//                .capital(province.getCapital().getName())
//                .build();
//    }
//
//    public Province convertToEntity(ProvinceDTO provinceDTO) {
//        Province province = new Province();
//        province.setName(province.getName());
//        province.setDescription(provinceDTO.getDescription());
//        province.setNation(provinceFacade.getNation(provinceDTO.getNation()));
//        province.setCapital(provinceFacade.getCapital(provinceDTO.getCapital()));
//        return province;
//    }
//}
