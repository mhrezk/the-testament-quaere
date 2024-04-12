package com.testament.veltahleon.services.ifc.society;

import com.testament.veltahleon.model.entities.society.Family;

import java.util.Collection;

public interface FamilyService {

    Collection<Family> getFamiliesWithPagination(int pageNumber, int numberOfRecords);
    Collection<Family> getFamilies();
    Collection<Family> getFamiliesByLineage(String lineage);
    Family getFamilyByID(Long id);
    //Family getFamilyByPersonName(String personName);
    Family getFamilyByFamilyName(String personName);
    Boolean deleteFamilyByID(Long id);
    Family saveFamily(Family family);
    Family updateFamily(Long id, Family family);
}
