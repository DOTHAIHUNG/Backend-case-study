package com.hk3t.inote.service;

import com.hk3t.inote.model.NoteType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoteTypeService {
    Page <NoteType> findAll(Pageable pageable);

    NoteType findById(Integer id);

    void save(NoteType noteType);

    void remove(Integer id);
}
