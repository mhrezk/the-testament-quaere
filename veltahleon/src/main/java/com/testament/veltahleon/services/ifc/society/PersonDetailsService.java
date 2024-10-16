package com.testament.veltahleon.services.ifc.society;

import com.testament.veltahleon.model.entities.society.PersonDetails;

public interface PersonDetailsService {

    PersonDetails getPersonByFirstNameAndSecondName(String firstName, String secondName);
    Boolean deletePersonDetailsByID(Long id);
    PersonDetails updatePersonDetails(Long id, PersonDetails personDetails);
    PersonDetails modifyPersonDetails(Long id, PersonDetails personDetails);
}
