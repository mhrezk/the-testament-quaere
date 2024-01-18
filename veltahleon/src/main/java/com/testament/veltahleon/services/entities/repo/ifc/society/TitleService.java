package com.testament.veltahleon.services.entities.repo.ifc.society;

import com.testament.veltahleon.model.entities.society.Title;

import java.util.Collection;

public interface TitleService {

    Collection<Title> getTitles();
    Title getTitleByID(Long id);
    Boolean deleteTitleByID(Long id);
    Title saveTitle(Title title);
    Title updateTitle(Title title);
}
