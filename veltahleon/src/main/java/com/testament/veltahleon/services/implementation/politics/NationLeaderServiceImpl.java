//package com.testament.veltahleon.services.implementation.politics;
//
//import com.testament.veltahleon.model.entities.politics.NationLeader;
//import com.testament.veltahleon.repositories.politics.NationLeaderRepository;
//import com.testament.veltahleon.services.ifc.politics.NationLeaderService;
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
//public class NationLeaderServiceImpl implements NationLeaderService {
//
//    @Autowired
//    private NationLeaderRepository nationLeaderRepository;
//
//
//    @Override
//    public Collection<NationLeader> getNationLeadersWithPagination(int pageNumber, int numberOfRecords) {
//        return nationLeaderRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
//    }
//
//    @Override
//    public Collection<NationLeader> getNationLeaders() {
//        return nationLeaderRepository.findAll();
//    }
//
//    @Override
//    public NationLeader getNationLeaderByID(Long id) {
//        return nationLeaderRepository.findById(id).orElseThrow();
//    }
//
//    @Override
//    public NationLeader getNationLeaderByName(String name) {
//        return nationLeaderRepository.findByName(name);
//    }
//
//    @Override
//    public Collection<NationLeader> getNationLeadersByBattleName(String name) {
//        return nationLeaderRepository.findByBattles_Name(name);
//    }
//
//    @Override
//    public Boolean deleteNationLeaderByID(Long id) {
//        nationLeaderRepository.deleteById(id);
//        return Boolean.TRUE;
//    }
//
//    @Override
//    public NationLeader saveNationLeader(NationLeader nationLeader) {
//        return nationLeaderRepository.save(nationLeader);
//    }
//
//    @Override
//    public NationLeader updateNationLeader(Long id, NationLeader nationLeader) {
//        NationLeader newNationLeader = nationLeaderRepository.findById(id).orElseThrow();
//
//        if(nationLeader.getName() != null && newNationLeader.getName() != nationLeader.getName()) {
//            newNationLeader.setName(nationLeader.getName());
//        }
//
//        if(nationLeader.getNation() != null && newNationLeader.getNation() != nationLeader.getNation()) {
//            newNationLeader.setNation(nationLeader.getNation());
//        }
//
//        if(nationLeader.getRace() != null && newNationLeader.getRace() != nationLeader.getRace()) {
//            newNationLeader.setRace(nationLeader.getRace());
//        }
//
//        if(nationLeader.getImageURL() != null && newNationLeader.getImageURL() != nationLeader.getImageURL()) {
//            newNationLeader.setImageURL(nationLeader.getImageURL());
//        }
//
//        if(nationLeader.getBiography() != null && newNationLeader.getBiography() != nationLeader.getBiography()) {
//            newNationLeader.setBiography(nationLeader.getBiography());
//        }
//
//        if(nationLeader.getReligion() != null && newNationLeader.getReligion() != nationLeader.getReligion()) {
//            newNationLeader.setReligion(nationLeader.getReligion());
//        }
//
//        if(nationLeader.getBattles() != null && newNationLeader.getBattles() != nationLeader.getBattles()) {
//            newNationLeader.setBattles(nationLeader.getBattles());
//        }
//
//        if(nationLeader.getFamily() != null && newNationLeader.getFamily() != nationLeader.getFamily()) {
//            newNationLeader.setFamily(nationLeader.getFamily());
//        }
//
//        if(nationLeader.getYearBeginningAndEnd() != null && newNationLeader.getYearBeginningAndEnd() != nationLeader.getYearBeginningAndEnd()) {
//            newNationLeader.setYearBeginningAndEnd(nationLeader.getYearBeginningAndEnd());
//        }
//
//        if(nationLeader.getBirthYear() != null && newNationLeader.getBirthYear() != nationLeader.getBirthYear()) {
//            newNationLeader.setBirthYear(nationLeader.getBirthYear());
//        }
//
//        if(nationLeader.getDeathYear() != null && nationLeader.getDeathYear() != nationLeader.getDeathYear()) {
//            nationLeader.setDeathYear(nationLeader.getDeathYear());
//        }
//
//        if(nationLeader.getTitle() != null && newNationLeader.getTitle() != nationLeader.getTitle()) {
//            newNationLeader.setTitle(nationLeader.getTitle());
//        }
//
//        if(nationLeader.getGender() != null && newNationLeader.getGender() != nationLeader.getGender()) {
//            newNationLeader.setGender(nationLeader.getGender());
//        }
//
//        return nationLeaderRepository.save(newNationLeader);
//    }
//}
