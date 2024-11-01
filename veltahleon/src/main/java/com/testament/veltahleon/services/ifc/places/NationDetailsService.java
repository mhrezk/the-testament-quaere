package com.testament.veltahleon.services.ifc.places;

import com.testament.veltahleon.model.entities.places.NationDetails;

public interface NationDetailsService {
    NationDetails getNationDetailsByID(Long id);
    NationDetails getNationDetailsByNationName(String name);
    NationDetails updateNationDetails(Long id, NationDetails nationDetails);
    NationDetails modifyNationDetails(Long id, NationDetails nationDetails);
    Boolean deleteNationByID(Long id);
}
