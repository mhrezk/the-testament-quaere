package com.testament.veltahleon.services.entities.dao.implementation.politics;

import com.testament.veltahleon.model.entities.politics.Vassal;
import com.testament.veltahleon.repositories.dao.ifc.politics.VassalDAO;
import com.testament.veltahleon.services.entities.dao.ifc.politics.VassalService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class VassalServiceImpl implements VassalService {

    @Autowired
    private VassalDAO vassalDAO;

    @Override
    public Collection<Vassal> getVassals() {
        return vassalDAO.getVassals();
    }

    @Override
    public Vassal getVassalByID(Long id) {
        return vassalDAO.getVassalByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteVassalByID(Long id) {
        return vassalDAO.deleteVassalByID(id);
    }

    @Override
    @Transactional
    public Vassal saveVassal(Vassal vassal) {
        return vassalDAO.saveVassal(vassal);
    }

    @Override
    @Transactional
    public Vassal updateVassal(Vassal vassal) {
        return vassalDAO.updateVassal(vassal);
    }
}
