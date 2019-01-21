package com.hk3t.inote.service;

import com.hk3t.inote.model.Note;
import com.hk3t.inote.model.NoteType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoteService {
    Page <Note> findAllByTitleContaining(String title, Pageable pageable);

    Page <Note> findAll(Pageable pageable);

    Note findById(Integer id);

    void save(Note note);

    void remove(Integer id);

    Page <Note> findAllByNoteType(NoteType noteType, Pageable pageable);
}
