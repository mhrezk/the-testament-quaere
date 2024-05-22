//package com.testament.veltahleon.services.implementation.politics.military;
//
//import com.testament.veltahleon.model.entities.politics.military.ArmyLeader;
//import com.testament.veltahleon.repositories.politics.military.ArmyLeaderRepository;
//import com.testament.veltahleon.services.ifc.politics.military.ArmyLeaderService;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.stereotype.Service;
//
//import java.util.Collection;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//@Slf4j
//public class ArmyLeaderServiceImpl implements ArmyLeaderService {
//
//    @Autowired
//    private ArmyLeaderRepository armyLeaderRepository;
//
//    @Override
//    public Collection<ArmyLeader> getArmyLeadersWithPagination(int pageNumber, int numberOfRecords) {
//        return armyLeaderRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
//    }
//
//    @Override
//    public Collection<ArmyLeader> getArmyLeaders() {
//        return armyLeaderRepository.findAll();
//    }
//
//    @Override
//    public ArmyLeader getArmyLeaderByID(Long id) {
//        return armyLeaderRepository.findById(id).orElseThrow();
//    }
//
//    @Override
//    public ArmyLeader getArmyLeaderByName(String name) {
//        return armyLeaderRepository.findByName(name);
//    }
//
//    @Override
//    public Boolean deleteArmyLeaderByID(Long id) {
//        armyLeaderRepository.deleteById(id);
//        return Boolean.TRUE;
//    }
//
//    @Override
//    public ArmyLeader saveArmyLeader(ArmyLeader armyLeader) {
//        return armyLeaderRepository.save(armyLeader);
//    }
//
//    @Override
//    public ArmyLeader updateArmyLeader(Long id, ArmyLeader armyLeader) {
//        ArmyLeader newArmyLeader = armyLeaderRepository.findById(id).orElseThrow();
//
//        if(armyLeader.getName() != null && newArmyLeader.getName() != armyLeader.getName()) {
//            newArmyLeader.setName(armyLeader.getName());
//        }
//
//        if(armyLeader.getNation() != null && newArmyLeader.getNation() != armyLeader.getNation()) {
//            newArmyLeader.setNation(armyLeader.getNation());
//        }
//
//        if(armyLeader.getRace() != null && newArmyLeader.getRace() != armyLeader.getRace()) {
//            newArmyLeader.setRace(armyLeader.getRace());
//        }
//
//        if(armyLeader.getRank() != null && newArmyLeader.getRank() != armyLeader.getRank()) {
//            newArmyLeader.setRank(armyLeader.getRank());
//        }
//
//        if(armyLeader.getImageURL() != null && newArmyLeader.getImageURL() != armyLeader.getImageURL()) {
//            newArmyLeader.setImageURL(armyLeader.getImageURL());
//        }
//
//        if(armyLeader.getBiography() != null && newArmyLeader.getBiography() != armyLeader.getBiography()) {
//            newArmyLeader.setBiography(armyLeader.getBiography());
//        }
//
//        if(armyLeader.getReligion() != null && newArmyLeader.getReligion() != armyLeader.getReligion()) {
//            newArmyLeader.setReligion(armyLeader.getReligion());
//        }
//
//        if(armyLeader.getBattles() != null && newArmyLeader.getBattles() != armyLeader.getBattles()) {
//            newArmyLeader.setBattles(armyLeader.getBattles());
//        }
//
//        if(armyLeader.getFamily() != null && newArmyLeader.getFamily() != armyLeader.getFamily()) {
//            newArmyLeader.setFamily(armyLeader.getFamily());
//        }
//
//        if(armyLeader.getBirthYear() != null && newArmyLeader.getBirthYear() != armyLeader.getBirthYear()) {
//            newArmyLeader.setBirthYear(armyLeader.getBirthYear());
//        }
//
//        if(armyLeader.getDeathYear() != null && newArmyLeader.getDeathYear() != armyLeader.getDeathYear()) {
//            newArmyLeader.setDeathYear(armyLeader.getDeathYear());
//        }
//
//        if(armyLeader.getTitle() != null && newArmyLeader.getTitle() != armyLeader.getTitle()) {
//            newArmyLeader.setTitle(armyLeader.getTitle());
//        }
//
//        if(armyLeader.getGender() != null && newArmyLeader.getGender() != armyLeader.getGender()) {
//            newArmyLeader.setGender(armyLeader.getGender());
//        }
//
//        return armyLeaderRepository.save(newArmyLeader);
//    }
//}
