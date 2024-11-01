//package com.testament.veltahleon.mappers.places;
//
//import com.testament.veltahleon.dto.places.NationDTO;
//import com.testament.veltahleon.enumerations.GovernanceType;
//import com.testament.veltahleon.enumerations.NationType;
//import com.testament.veltahleon.facades.places.NationFacade;
//import com.testament.veltahleon.model.entities.places.Nation;
//import com.testament.veltahleon.model.entities.places.Province;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Component
//@AllArgsConstructor
//public class NationMapper {
//
//    @Autowired
//    private NationFacade nationFacade;
//
//    public NationDTO convertToDTO(Nation nation) {
//        return NationDTO.builder()
//                .id(nation.getId())
//                .name(nation.getName())
//                .type(String.valueOf(nation.getType()))
//                .governanceType(String.valueOf(nation.getGovernanceType()))
//                .capital(nation.getCapital().getName())
//                .language(nation.getLanguage().getName())
//                .leaderFirstName(nation.getLeader().getFirstName())
//                .leaderSecondName(nation.getLeader().getSecondName())
//                .provinces(nation.getProvinces().stream().map(Province::getName).toList())
//                //.provinces(nation.getProvinces().stream().map(Province::getName).collect(Collectors.toSet()))
//                .description(nation.getDescription())
//                .urlFlag(nation.getUrlFlag())
//                .build();
//    }
//
//    public Nation convertToEntity(NationDTO nationDTO) {
//        Nation nation = new Nation();
//        nation.setId(nationDTO.getId());
//        nation.setName(nationDTO.getName());
//        List<String> provinceNames = nationDTO.getProvinces();
//        //Set<String> provinceNames = nationDTO.getProvinces();
//        nation.setType(NationType.valueOf(nationDTO.getType()));
//        nation.setGovernanceType(GovernanceType.valueOf(nationDTO.getGovernanceType()));
//        nation.setCapital(nationFacade.getCapital(nationDTO.getCapital()));
//        nation.setLanguage(nationFacade.getLanguage(nationDTO.getLanguage()));
//        nation.setLeader(nationFacade.getNationLeader(nationDTO.getLeaderFirstName(), nationDTO.getLeaderSecondName()));
//        nation.setDescription(nationDTO.getDescription());
//        nation.setUrlFlag(nationDTO.getUrlFlag());
//        nation.setProvinces(nationFacade.getProvinces(provinceNames));
//        return nation;
//    }
//}
