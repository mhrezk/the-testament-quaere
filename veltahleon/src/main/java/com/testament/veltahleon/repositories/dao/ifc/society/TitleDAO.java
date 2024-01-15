package com.testament.veltahleon.repositories.dao.ifc.society;

import com.testament.veltahleon.model.entities.society.Title;
import com.testament.veltahleon.model.entities.society.Title;

import java.util.Collection;

public interface TitleDAO {

    Collection<Title> getTitles();
    Title getTitleByID(Long id);
    Boolean deleteTitleByID(Long id);
    Title saveTitle(Title Title);
    Title updateTitle(Title Title);
}
