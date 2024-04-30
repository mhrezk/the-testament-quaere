package com.testament.veltahleon.facades.places;

import com.testament.veltahleon.model.entities.places.Capital;
import com.testament.veltahleon.model.entities.places.Nation;
import com.testament.veltahleon.services.ifc.places.CapitalService;
import com.testament.veltahleon.services.ifc.places.NationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProvinceFacade {

    @Autowired
    private CapitalService capitalService;

    @Autowired
    private NationService nationService;

    public Capital getCapital(String capitalName) {
        return capitalService.getCapitalByName(capitalName);
    }

    public Nation getNation(String nationName) {
        return nationService.getNationByName(nationName);
    }
}
