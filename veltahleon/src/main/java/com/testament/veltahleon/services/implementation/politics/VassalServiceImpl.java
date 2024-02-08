package com.testament.veltahleon.services.implementation.politics;

import com.testament.veltahleon.model.entities.politics.Vassal;
import com.testament.veltahleon.repositories.politics.VassalRepository;
import com.testament.veltahleon.services.ifc.politics.VassalService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class VassalServiceImpl implements VassalService {

    @Autowired
    private VassalRepository vassalRepository;

    @Override
    public Collection<Vassal> getVassals() {
        return vassalRepository.findAll();
    }

    @Override
    public Vassal getVassalByID(Long id) {
        return vassalRepository.findById(id).orElseThrow();
    }

    @Override
    public Boolean deleteVassalByID(Long id) {
        vassalRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Vassal saveVassal(Vassal vassal) {
        return vassalRepository.save(vassal);
    }

    @Override
    public Vassal updateVassal(Long id, Vassal vassal) {
        Vassal newVassal = vassalRepository.findById(id).orElseThrow();

        if(vassal.getName() != null && newVassal.getName() != vassal.getName()) {
            newVassal.setName(vassal.getName());
        }

        if(vassal.getNation() != null && newVassal.getNation() != vassal.getNation()) {
            newVassal.setNation(vassal.getNation());
        }

        if(vassal.getRace() != null && newVassal.getRace() != vassal.getRace()) {
            newVassal.setRace(vassal.getRace());
        }

        if(vassal.getImageURL() != null && newVassal.getImageURL() != vassal.getImageURL()) {
            newVassal.setImageURL(vassal.getImageURL());
        }

        if(vassal.getBiography() != null && newVassal.getBiography() != vassal.getBiography()) {
            newVassal.setBiography(vassal.getBiography());
        }

        if(vassal.getFamily() != null && newVassal.getFamily() != vassal.getFamily()) {
            newVassal.setFamily(vassal.getFamily());
        }

        if(vassal.getReligion() != null && newVassal.getReligion() != vassal.getReligion()) {
            newVassal.setReligion(vassal.getReligion());
        }

        if(vassal.getBattles() != null && newVassal.getBattles() != vassal.getBattles()) {
            newVassal.setBattles(vassal.getBattles());
        }

        if(vassal.getFamily() != null && newVassal.getFamily() != vassal.getFamily()) {
            newVassal.setFamily(vassal.getFamily());
        }

        if(vassal.getYearBeginningAndEnd() != null && newVassal.getYearBeginningAndEnd() != vassal.getYearBeginningAndEnd()) {
            newVassal.setYearBeginningAndEnd(vassal.getYearBeginningAndEnd());
        }

        if(vassal.getYearBirthAndDeath() != null && newVassal.getYearBirthAndDeath() != vassal.getYearBirthAndDeath()) {
            newVassal.setYearBirthAndDeath(vassal.getYearBirthAndDeath());
        }

        if(vassal.getTitle() != null && newVassal.getTitle() != vassal.getTitle()) {
            newVassal.setTitle(vassal.getTitle());
        }

        if(vassal.getGender() != null && newVassal.getGender() != vassal.getGender()) {
            newVassal.setGender(vassal.getGender());
        }

        return vassalRepository.save(newVassal);
    }
}
