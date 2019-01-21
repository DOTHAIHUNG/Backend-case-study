package com.hk3t.inote.service.impl;

import com.hk3t.inote.model.Note;
import com.hk3t.inote.model.NoteType;
import com.hk3t.inote.repository.NoteRepository;
import com.hk3t.inote.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteRepository noteRepository;

    @Override
    public Page <Note> findAll(Pageable pageable) {
        return noteRepository.findAll(pageable);
    }

    @Override
    public Note findById(Integer id) {
        return noteRepository.findById(id).get();
    }

    @Override
    public Page <Note> findAllByTitleContaining(String title, Pageable pageable) {
        return noteRepository.findAllByTitleContaining(title, pageable);
    }

    @Override
    public void save(Note note) {
        noteRepository.save(note);
    }

    @Override
    public void remove(Integer id) {
        noteRepository.deleteById(id);
    }

    @Override
    public Page <Note> findAllByNoteType(NoteType noteType, Pageable pageable) {
        return noteRepository.findAllByNoteType(noteType, pageable);
    }
}
