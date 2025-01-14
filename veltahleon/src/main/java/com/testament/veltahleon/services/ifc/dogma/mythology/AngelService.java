package com.testament.veltahleon.services.ifc.dogma.mythology;

import com.testament.veltahleon.model.entities.dogma.mythology.Angel;

import java.util.Collection;

public interface AngelService {

    Collection<Angel> getAngelsWithPagination(int pageNumber, int numberOfRecords);
    Collection<Angel> getAngelsByReligionNameWithPagination(int pageNumber, int numberOfRecords, String religionName);
    Collection<Angel> getAngels();
    Angel getAngelByID(Long id);
    Angel getAngelByName(String name);
    Boolean deleteAngelByID(Long id);
    Angel saveAngel(Angel angel);
    Angel updateAngel(Long id, Angel angel);
    Angel modifyAngel(Long id, Angel angel);
    long countAngelsByReligionName(String religionName);
    long countAngels();
}
