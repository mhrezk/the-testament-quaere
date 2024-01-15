package com.testament.veltahleon.repositories.dao.ifc.society;

import com.testament.veltahleon.model.entities.society.Family;

import java.util.Collection;

public interface FamilyDAO {

    Collection<Family> getFamilies();
    Family getFamilyByID(Long id);
    Boolean deleteFamilyByID(Long id);
    Family saveFamily(Family family);
    Family updateFamily(Family family);
}
