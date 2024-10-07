package com.testament.veltahleon.services.ifc.society;

import com.testament.veltahleon.model.entities.society.Pedigree;

import java.util.Collection;
import java.util.List;

public interface PedigreeService {

    Collection<Pedigree> getPedigreesWithPagination(int pageNumber, int numberOfRecords);
    Collection<Pedigree> getPedigrees();
    Collection<Pedigree> getPedigreesByGender(String gender);
    Pedigree getPedigreeByID(Long id);
    Pedigree getPedigreeByMotherID(Long motherID);
    Pedigree getPedigreeByFatherID(Long fatherID);
    Pedigree getPedigreeBySpousalIDs(List<Long> spousalIDs);
    Pedigree getPedigreeByLineageName(String lineageName);
    Pedigree getPedigreeByFirstName(String firstName);
    Boolean deletePedigreeByID(Long id);
    Pedigree savePedigree(Pedigree pedigree);
    Pedigree updatePedigree(Long id, Pedigree pedigree);
    Pedigree modifyPedigree(Long id, Pedigree pedigree);
}
