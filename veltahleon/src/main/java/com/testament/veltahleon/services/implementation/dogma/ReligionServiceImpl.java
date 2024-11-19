package com.testament.veltahleon.services.implementation.dogma;

import com.testament.veltahleon.exceptions.DataNotFoundException;
import com.testament.veltahleon.facades.places.NationFacade;
import com.testament.veltahleon.model.entities.dogma.Religion;
import com.testament.veltahleon.model.entities.places.Nation;
import com.testament.veltahleon.repositories.dogma.ReligionRepository;
import com.testament.veltahleon.repositories.places.NationRepository;
import com.testament.veltahleon.services.ifc.dogma.ReligionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ReligionServiceImpl implements ReligionService {

    @Autowired
    private ReligionRepository religionRepository;

    @Autowired
    private NationFacade nationFacade;

    @Override
    public Collection<Religion> getReligionsWithPagination(int pageNumber, int numberOfRecords) {
        return religionRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Religion> getReligions() {
        return religionRepository.findAll();
    }

    @Override
    public Religion getReligionByID(Long id) {
        return religionRepository.findById(id).orElseThrow(() -> new DataNotFoundException(String.format("Entry with id %d does not exist", id)));
    }

    @Override
    public Religion getReligionByName(String name) {
        if(religionRepository.countByName(name) <= 0) {
            Religion newReligion = new Religion();
            List<Nation> nations = new ArrayList<>();
            newReligion.setName(name.toUpperCase());
            newReligion.setNations(nations);
            newReligion.setSymbolURL(defaultImageURL("mosque.png"));
            return religionRepository.save(newReligion);
        } else {
            return religionRepository.findByName(name);
        }
    }

    @Override
    public Boolean deleteReligionByID(Long id) {
        religionRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Religion saveReligion(Religion religion) {
        religion.setName(religion.getName().toUpperCase());
        religion.setSymbolURL(defaultImageURL("mosque.png"));
        return religionRepository.save(religion);
    }

    private String defaultImageURL(String imageName) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/dogma/religions/images/" + imageName).toUriString();
    }

    @Override
    public Religion updateReligion(Long id, Religion religion) {
        Religion newReligion = religionRepository.findById(id).orElseThrow();

        if(religion.getName() != null && newReligion.getName() != religion.getName()) {
            newReligion.setName(religion.getName().toUpperCase());
        }

        if(religion.getSymbolURL() != null && newReligion.getSymbolURL() != religion.getSymbolURL()) {
            newReligion.setSymbolURL(religion.getSymbolURL());
        }

        if(religion.getDescription() != null && newReligion.getDescription() != religion.getDescription()) {
            newReligion.setDescription(religion.getDescription());
        }

        return religionRepository.save(newReligion);
    }

    @Override
    public Religion modifyReligion(Long id, Religion religion) {
        Religion newReligion = religionRepository.findById(id).orElseThrow();
        newReligion.setName(religion.getName().toUpperCase());
        newReligion.setSymbolURL(religion.getSymbolURL());
        newReligion.setDescription(religion.getDescription());
        List<Nation> updatedNations = nationFacade.getNations(
                religion.getNations().stream()
                        .map(Nation::getName)
                        .collect(Collectors.toList())
        );

        newReligion.setNations(updatedNations);
        return religionRepository.save(newReligion);
    }

    @Override
    public Religion removeNationFromReligion(Long religionId, String nationName) {
        // Find the Religion and Nation
        Religion newReligion = religionRepository.findById(religionId).orElseThrow();
        Nation nation = nationFacade.getNation(nationName);

        // Remove the nation from the religion's nations list
        newReligion.getNations().remove(nation);

        // Save the updated religion (the association is updated)
        religionRepository.save(newReligion);

        // Delete the nation from the database
        //nationFacade.deleteNation(nation);

        return newReligion;
    }

    @Override
    public long countReligions() {
        return religionRepository.count();
    }
}
