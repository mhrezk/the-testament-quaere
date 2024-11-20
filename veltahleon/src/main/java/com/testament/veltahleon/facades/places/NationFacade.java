package com.testament.veltahleon.facades.places;

import com.testament.veltahleon.model.entities.dogma.Religion;
import com.testament.veltahleon.model.entities.dogma.mythology.Deity;
import com.testament.veltahleon.model.entities.history.Language;
import com.testament.veltahleon.model.entities.places.Capital;
import com.testament.veltahleon.model.entities.places.Nation;
import com.testament.veltahleon.model.entities.places.NationDetails;
import com.testament.veltahleon.model.entities.society.Person;
import com.testament.veltahleon.services.ifc.dogma.ReligionService;
import com.testament.veltahleon.services.ifc.dogma.mythology.DeityService;
import com.testament.veltahleon.services.ifc.history.LanguageService;
import com.testament.veltahleon.services.ifc.places.CapitalService;
import com.testament.veltahleon.services.ifc.places.NationDetailsService;
import com.testament.veltahleon.services.ifc.places.NationService;
import com.testament.veltahleon.services.ifc.society.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class NationFacade {

    @Autowired
    private CapitalService capitalService;

    @Autowired
    private DeityService deityService;

    @Autowired
    private NationService nationService;

    @Autowired
    private NationDetailsService nationDetailsService;

    @Autowired
    private LanguageService languageService;

    @Autowired
    private ReligionService religionService;

    @Autowired
    private PersonService personService;

//    @Autowired
//    private ProvinceService provinceService;

    public Deity getDeity(String deityName) {
        return deityService.getDeityByName(deityName);
    }

    public Capital getCapital(String capitalName) {
        return capitalService.getCapitalByName(capitalName);
    }

    public Language getLanguage(String languageName) {
        return languageService.getLanguageByName(languageName);
    }

    public List<Language> getLanguages(List<String> languageNames) {
        return languageNames.stream()
                .map(l -> languageService.getLanguageByName(l))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Person getNationLeader(String firstName, String lastName) {
        return personService.getPersonByFirstNameAndLastName(firstName, lastName);
    }

    public Religion getReligion(String religionName) {
        return religionService.getReligionByName(religionName);
    }

    public List<Nation> getNations(List<String> nationNames) {
        return nationNames.stream()
                .map(n -> nationService.getNationByName(n))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Nation getNation(String name) {
        return nationService.getNationByName(name);
    }

    public Boolean deleteNationByName(String name) {
        return nationService.deleteNationByName(name);
    }

    public Boolean deleteNation(Nation nation) {
        nationService.deleteNation(nation);
        return Boolean.TRUE;
    }

    public NationDetails getNationDetails(String nationName) {
        return nationDetailsService.getNationDetailsByNationName(nationName);
    }

//    public Province getProvince(String provinceName) {
//        return provinceService.getProvinceByName(provinceName);
//    }
//
//    public List<Province> getProvinces(List<String> provinces) {
//        return provinces.stream().map(p -> provinceService.getProvinceByName(p)).toList();
//    }

//    public Set<Province> getProvinces(Set<String> provinces) {
//        return provinces.stream().map(p -> provinceService.getProvinceByName(p)).collect(Collectors.toSet());
//    }
}
