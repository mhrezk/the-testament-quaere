package com.testament.veltahleon.facades.dogma;

import com.testament.veltahleon.model.entities.dogma.Prophet;
import com.testament.veltahleon.model.entities.dogma.Religion;
import com.testament.veltahleon.model.entities.dogma.mythology.Deity;
import com.testament.veltahleon.services.ifc.dogma.ProphetService;
import com.testament.veltahleon.services.ifc.dogma.ReligionService;
import com.testament.veltahleon.services.ifc.dogma.mythology.DeityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ReligionFacade {

    @Autowired
    private ReligionService religionService;

    @Autowired
    private ProphetService prophetService;

    @Autowired
    private DeityService deityService;

    public Religion getReligion(String religionName) {
        return religionService.getReligionByName(religionName);
    }

    public List<Prophet> getProphetsByReligionName(String religionName) {
        return (List<Prophet>) prophetService.getProphetsByReligionName(religionName);
    }

    public List<Deity> getDeitiesByReligionName(String religionName) {
        return (List<Deity>) deityService.getDeitiesByReligionName(religionName);
    }
}
