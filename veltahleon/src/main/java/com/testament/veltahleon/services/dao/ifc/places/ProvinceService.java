package com.testament.veltahleon.services.dao.ifc.places;

import com.testament.veltahleon.model.entities.places.Province;

import java.util.Collection;

public interface ProvinceService {

    Collection<Province> getProvinces();
    Province getProvinceByID(Long id);
    Boolean deleteProvinceByID(Long id);
    Province saveProvince(Province province);
    Province updateProvince(Province province);
}
