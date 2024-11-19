package com.testament.veltahleon.mappers.places;

import com.testament.veltahleon.dto.places.ProvinceDTO;
import com.testament.veltahleon.facades.places.NationFacade;
import com.testament.veltahleon.model.entities.places.Province;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProvinceMapper {

    @Autowired
    private NationFacade nationFacade;

    public ProvinceDTO convertToDTO(Province province) {
        return ProvinceDTO.builder()
                .id(province.getId())
                .name(province.getName())
                .history(province.getHistory())
                .nation(province.getNation().getName())
                .capital(province.getCapital().getName())
                .flagURL(province.getFlagURL())
                .build();
    }

    public Province convertToEntity(ProvinceDTO provinceDTO) {
        Province province = new Province();
        province.setId(provinceDTO.getId());
        province.setName(province.getName());
        province.setHistory(provinceDTO.getHistory());
        province.setNation(nationFacade.getNation(provinceDTO.getNation()));
        province.setCapital(nationFacade.getCapital(provinceDTO.getCapital()));
        return province;
    }
}
