package com.testament.veltahleon.services.entities.repo.implementation.places;

import com.testament.veltahleon.model.entities.places.Province;
import com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.places.ProvinceRepository;
import com.testament.veltahleon.services.entities.repo.ifc.places.ProvinceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public Collection<Province> getProvincesWithPagination(int pageNumber, int numberOfRecords) {
        return provinceRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Province> getProvinces() {
        return provinceRepository.findAll();
    }

    @Override
    public Collection<Province> getProvincesByNationName(String name) {
        return provinceRepository.findByNation_Name(name);
    }

    @Override
    public Province getProvinceByID(Long id) {
        return provinceRepository.findById(id).orElseThrow();
    }

    @Override
    public Province getProvinceByName(String name) {
        return provinceRepository.findByName(name);
    }

    @Override
    public long getNumberOfProvincesInNation(String provinceName) {
        return provinceRepository.countByName(provinceName);
    }

    @Override
    public Boolean deleteProvinceByID(Long id) {
        provinceRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Province saveProvince(Province province) {
        return provinceRepository.save(province);
    }

    @Override
    public Province updateProvince(Long id, Province province) {
        Province newProvince = provinceRepository.findById(id).orElseThrow();

        if(province.getName() != null && newProvince.getName() != province.getName()) {
            newProvince.setName(province.getName());
        }

        if(province.getDescription() != null && newProvince.getDescription() != province.getDescription()) {
            newProvince.setDescription(province.getDescription());
        }

        if(province.getCapital() != null && newProvince.getCapital() != province.getCapital()) {
            newProvince.setCapital(province.getCapital());
        }

        if(province.getNation() != null && newProvince.getNation() != province.getNation()) {
            newProvince.setNation(province.getNation());
        }

        return provinceRepository.save(newProvince);
    }
}
