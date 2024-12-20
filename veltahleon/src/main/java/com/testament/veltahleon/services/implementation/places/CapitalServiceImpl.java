package com.testament.veltahleon.services.implementation.places;

import com.testament.veltahleon.exceptions.DataInsertionException;
import com.testament.veltahleon.model.entities.places.Capital;
import com.testament.veltahleon.model.entities.places.Nation;
import com.testament.veltahleon.repositories.places.CapitalRepository;
import com.testament.veltahleon.repositories.places.NationRepository;
import com.testament.veltahleon.services.ifc.places.CapitalService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CapitalServiceImpl implements CapitalService {

    @Autowired
    private CapitalRepository capitalRepository;
    @Autowired
    private NationRepository nationRepository;

    @Override
    public Collection<Capital> getCapitalsWithPagination(int pageNumber, int numberOfRecords) {
        return capitalRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Capital> getCapitals() {
        return capitalRepository.findAll();
    }

    @Override
    public Capital getCapitalByID(Long id) {
        return capitalRepository.findById(id).orElseThrow();
    }

    @Override
    public Capital getCapitalByName(String name) {
        if(capitalRepository.countByName(name) <= 0) {
            Capital capital = new Capital();
            capital.setName(name.toUpperCase());
            capital.setFlagURL(defaultImageURL("square.png"));
            return capitalRepository.save(capital);
        } else {
            return capitalRepository.findByName(name);
        }
    }

//    @Override
//    public Capital getCapitalByNationName(String name) {
//        return capitalRepository.findByNation_Name(name);
//    }

    @Override
    public Boolean deleteCapitalByID(Long id) {
        capitalRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Capital saveCapital(Capital capital) {
        if(capitalRepository.countByName(capital.getName().toLowerCase()) >= 1) {
            throw new DataInsertionException("Capital name");
        }
        capital.setName(capital.getName().toUpperCase());
        capital.setFlagURL(defaultImageURL("square.png"));
        return capitalRepository.save(capital);
    }

    private String defaultImageURL(String imageName) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/places/capitals/images/" + imageName).toUriString();
    }

    @Override
    public Capital updateCapital(Long id, Capital capital) {
        Capital newCapital = capitalRepository.findById(id).orElseThrow();

        if(capital.getName() != null && newCapital.getName() != capital.getName()) {
            newCapital.setName(capital.getName());
        }

        if(capital.getHistory() != null && newCapital.getHistory() != capital.getHistory()) {
            newCapital.setHistory(capital.getHistory());
        }

        if(capital.getFlagURL() != null && newCapital.getFlagURL() != capital.getFlagURL()) {
            newCapital.setFlagURL(capital.getFlagURL());
        }

//        if(capital.getNation() != null && newCapital.getNation() != capital.getNation()) {
//            newCapital.setNation(checkNationForUpdate(capital.getNation(), newCapital.getNation()));
//        }

        return capitalRepository.save(newCapital);
    }



    //Helper Methods
    private Nation checkNationForUpdate(Nation nation, Nation newNation) {
        if(nation.getName() != null && newNation.getName() != nation.getName()) {
            newNation.setName(nation.getName());
        }

//        if(nation.getDescription() != null && newNation.getDescription() != nation.getDescription()) {
//            newNation.setDescription(nation.getDescription());
//        }
//
//        if(nation.getCapital() != null && newNation.getCapital() != nation.getCapital()) {
//            newNation.setCapital(nation.getCapital());
//        }
//
////        if(nation.getContinent() != null && newNation.getContinent() != nation.getContinent()) {
////            newNation.setContinent(nation.getContinent());
////        }
//
//        if(nation.getLeader() != null && newNation.getLeader() != nation.getLeader()) {
//            newNation.setLeader(nation.getLeader());
//        }
//
//        if(nation.getLanguage() != null && newNation.getLanguage() != nation.getLanguage()) {
//            newNation.setLanguage(nation.getLanguage());
//        }
//
//        if(nation.getProvinces() != null && newNation.getProvinces() != nation.getProvinces()) {
//            newNation.setProvinces(nation.getProvinces());
//        }
//
//        if(nation.getType() != null && newNation.getType() != nation.getType()) {
//            newNation.setType(nation.getType());
//        }

        return newNation;
    }
}
