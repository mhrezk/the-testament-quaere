package com.testament.veltahleon.services.entities.dao.implementation.society;

import com.testament.veltahleon.model.entities.society.Title;
import com.testament.veltahleon.repositories.dao.ifc.society.TitleDAO;
import com.testament.veltahleon.services.entities.dao.ifc.society.TitleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
@RequiredArgsConstructor
@Slf4j
public class TitleServiceImpl implements TitleService {

    @Autowired
    private TitleDAO titleDAO;
    
    @Override
    public Collection<Title> getTitles() {
        return titleDAO.getTitles();
    }

    @Override
    public Title getTitleByID(Long id) {
        return titleDAO.getTitleByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteTitleByID(Long id) {
        return titleDAO.deleteTitleByID(id);
    }

    @Override
    @Transactional
    public Title saveTitle(Title title) {
        return titleDAO.saveTitle(title);
    }

    @Override
    @Transactional
    public Title updateTitle(Title title) {
        return titleDAO.updateTitle(title);
    }
}
