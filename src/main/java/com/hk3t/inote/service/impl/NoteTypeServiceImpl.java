package com.hk3t.inote.service.impl;

import com.hk3t.inote.model.NoteType;
import com.hk3t.inote.repository.NoteTypeRepository;
import com.hk3t.inote.service.NoteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class NoteTypeServiceImpl implements NoteTypeService {
    @Autowired
    private NoteTypeRepository noteTypeRepository;

    @Override
    public Page <NoteType> findAll(Pageable pageable) {
        return noteTypeRepository.findAll(pageable);
    }

    @Override
    public NoteType findById(Integer id) {
        return noteTypeRepository.findById(id).get();
    }

    @Override
    public void save(NoteType noteType) {
        noteTypeRepository.save(noteType);
    }

    @Override
    public void remove(Integer id) {
        noteTypeRepository.deleteById(id);
    }
}
