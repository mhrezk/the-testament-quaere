package com.testament.veltahleon.services.entities.repo.implementation.society;

import com.testament.veltahleon.model.entities.society.Title;
import com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.society.TitleRepository;
import com.testament.veltahleon.services.entities.repo.ifc.society.TitleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TitleServiceImpl implements TitleService {

    @Autowired
    private TitleRepository titleRepository;

    @Override
    public Collection<Title> getTitles() {
        return titleRepository.findAll();
    }

    @Override
    public Title getTitleByID(Long id) {
        return titleRepository.findById(id).orElseThrow();
    }

    @Override
    public Boolean deleteTitleByID(Long id) {
        titleRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Title saveTitle(Title title) {
        return titleRepository.save(title);
    }

    @Override
    public Title updateTitle(Long id, Title title) {
        Title newTitle = titleRepository.findById(id).orElseThrow();

        if(title.getName() != null && newTitle.getName() != title.getName()) {
            newTitle.setName(title.getName());
        }

        return titleRepository.save(newTitle);
    }
}
