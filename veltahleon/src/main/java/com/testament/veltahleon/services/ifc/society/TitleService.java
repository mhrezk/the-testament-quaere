package com.testament.veltahleon.services.ifc.society;

import com.testament.veltahleon.model.entities.society.Title;

import java.util.Collection;

public interface TitleService {

    Collection<Title> getTitles();
    Title getTitleByID(Long id);
    Title getTitleByName(String name);
    Boolean deleteTitleByID(Long id);
    Title saveTitle(Title title);
    Title updateTitle(Long id, Title title);
}
