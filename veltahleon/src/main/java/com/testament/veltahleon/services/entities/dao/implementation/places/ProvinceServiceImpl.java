package com.testament.veltahleon.services.entities.dao.implementation.places;

import com.testament.veltahleon.model.entities.places.Province;
import com.testament.veltahleon.repositories.dao.ifc.places.ProvinceDAO;
import com.testament.veltahleon.services.entities.dao.ifc.places.ProvinceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceDAO provinceDAO;

    @Override
    public Collection<Province> getProvinces() {
        return provinceDAO.getProvinces();
    }

    @Override
    public Province getProvinceByID(Long id) {
        return provinceDAO.getProvinceByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteProvinceByID(Long id) {
        return provinceDAO.deleteProvinceByID(id);
    }

    @Override
    @Transactional
    public Province saveProvince(Province province) {
        return provinceDAO.saveProvince(province);
    }

    @Override
    @Transactional
    public Province updateProvince(Province province) {
        return provinceDAO.updateProvince(province);
    }
}
