package com.testament.veltahleon.services.entities.repo.ifc.politics;

import com.testament.veltahleon.model.entities.politics.Vassal;

import java.util.Collection;

public interface VassalService {

    Collection<Vassal> getVassals();
    Vassal getVassalByID(Long id);
    Boolean deleteVassalByID(Long id);
    Vassal saveVassal(Vassal vassal);
    Vassal updateVassal(Vassal vassal);
}
