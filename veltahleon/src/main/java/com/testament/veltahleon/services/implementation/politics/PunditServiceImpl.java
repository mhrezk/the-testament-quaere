//package com.testament.veltahleon.services.implementation.politics;
//
//import com.testament.veltahleon.model.entities.politics.Pundit;
//import com.testament.veltahleon.repositories.politics.PunditRepository;
//import com.testament.veltahleon.services.ifc.politics.PunditService;
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
//public class PunditServiceImpl implements PunditService {
//
//    @Autowired
//    private PunditRepository punditRepository;
//
//    @Override
//    public Collection<Pundit> getPunditsWithPagination(int pageNumber, int numberOfRecords) {
//        return punditRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
//    }
//
//    @Override
//    public Collection<Pundit> getPundits() {
//        return punditRepository.findAll();
//    }
//
//    @Override
//    public Pundit getPunditByID(Long id) {
//        return punditRepository.findById(id).orElseThrow();
//    }
//
//    @Override
//    public Pundit getPunditByName(String name) {
//        return punditRepository.findByName(name);
//    }
//
//    @Override
//    public Pundit getPunditByOrganizationName(String name) {
//        return punditRepository.findByOrganization_Name(name);
//    }
//
//    @Override
//    public Boolean deletePunditByID(Long id) {
//        punditRepository.deleteById(id);
//        return Boolean.TRUE;
//    }
//
//    @Override
//    public Pundit savePundit(Pundit pundit) {
//        return punditRepository.save(pundit);
//    }
//
//    @Override
//    public Pundit updatePundit(Long id, Pundit pundit) {
//        Pundit newPundit = punditRepository.findById(id).orElseThrow();
//
//        if(pundit.getName() != null && newPundit.getName() != pundit.getName()) {
//            newPundit.setName(pundit.getName());
//        }
//
//        if(pundit.getNation() != null && newPundit.getNation() != pundit.getNation()) {
//            newPundit.setNation(pundit.getNation());
//        }
//
//        if(pundit.getRace() != null && newPundit.getRace() != pundit.getRace()) {
//            newPundit.setRace(pundit.getRace());
//        }
//
//        if(pundit.getImageURL() != null && newPundit.getImageURL() != pundit.getImageURL()) {
//            newPundit.setImageURL(pundit.getImageURL());
//        }
//
//        if(pundit.getBiography() != null && newPundit.getBiography() != pundit.getBiography()) {
//            newPundit.setBiography(pundit.getBiography());
//        }
//
//        if(pundit.getFamily() != null && newPundit.getFamily() != pundit.getFamily()) {
//            newPundit.setFamily(pundit.getFamily());
//        }
//
//        if(pundit.getReligion() != null && newPundit.getReligion() != pundit.getReligion()) {
//            newPundit.setReligion(pundit.getReligion());
//        }
//
//        if(pundit.getBirthYear() != null && newPundit.getBirthYear() != pundit.getBirthYear()) {
//            newPundit.setBirthYear(pundit.getBirthYear());
//        }
//
//        if(pundit.getDeathYear() != null && newPundit.getDeathYear() != pundit.getDeathYear()) {
//            newPundit.setDeathYear(pundit.getDeathYear());
//        }
//
//        if(pundit.getTitle() != null && newPundit.getTitle() != pundit.getTitle()) {
//            newPundit.setTitle(pundit.getTitle());
//        }
//
//        if(pundit.getGender() != null && newPundit.getGender() != pundit.getGender()) {
//            newPundit.setGender(pundit.getGender());
//        }
//
//        if(pundit.getOrganization() != null && newPundit.getOrganization() != pundit.getOrganization()) {
//            newPundit.setOrganization(pundit.getOrganization());
//        }
//
//        if(pundit.getUrlCoatOfArms() != null && newPundit.getUrlCoatOfArms() != pundit.getUrlCoatOfArms()) {
//            newPundit.setUrlCoatOfArms(pundit.getUrlCoatOfArms());
//        }
//
//        return punditRepository.save(newPundit);
//    }
//}
