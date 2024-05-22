package com.testament.veltahleon.facades.places;

import com.testament.veltahleon.model.entities.history.Language;
import com.testament.veltahleon.model.entities.places.Capital;
import com.testament.veltahleon.model.entities.places.Province;
import com.testament.veltahleon.model.entities.society.Person;
import com.testament.veltahleon.services.ifc.history.LanguageService;
import com.testament.veltahleon.services.ifc.places.CapitalService;
import com.testament.veltahleon.services.ifc.places.ProvinceService;
import com.testament.veltahleon.services.ifc.society.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class NationFacade {

    @Autowired
    private CapitalService capitalService;

    @Autowired
    private LanguageService languageService;

    @Autowired
    private PersonService personService;

    @Autowired
    private ProvinceService provinceService;

    public Capital getCapital(String capitalName) {
        return capitalService.getCapitalByName(capitalName);
    }

    public Language getLanguage(String languageName) {
        return languageService.getLanguageByName(languageName);
    }

    public Person getNationLeader(String nationLeaderName) {
        return personService.getPersonByName(nationLeaderName);
    }

    public Province getProvince(String provinceName) {
        return provinceService.getProvinceByName(provinceName);
    }

    public Set<Province> getProvinces(Set<String> provinces) {
        return provinces.stream().map(p -> provinceService.getProvinceByName(p)).collect(Collectors.toSet());
    }
}
