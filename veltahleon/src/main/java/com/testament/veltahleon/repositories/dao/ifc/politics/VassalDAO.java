package com.testament.veltahleon.repositories.dao.ifc.politics;

import com.testament.veltahleon.model.entities.politics.Vassal;

import java.util.Collection;

public interface VassalDAO {

    Collection<Vassal> getVassals();
    Vassal getVassalByID(Long id);
    Boolean deleteVassalByID(Long id);
    Vassal saveVassal(Vassal vassal);
    Vassal updateVassal(Vassal vassal);
}
