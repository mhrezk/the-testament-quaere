package com.testament.veltahleon.services.ifc.places;

import com.testament.veltahleon.model.entities.places.Province;

import java.util.Collection;

public interface ProvinceService {

    Collection<Province> getProvincesWithPagination(int pageNumber, int numberOfRecords);
    Collection<Province> getProvinces();
    Collection<Province> getProvincesByNationName(String name);
    Province getProvinceByID(Long id);
    Province getProvinceByName(String name);
    long getNumberOfProvincesInNation(String provinceName);
    Boolean deleteProvinceByID(Long id);
    Province saveProvince(Province province);
    Province updateProvince(Long id, Province province);
}
