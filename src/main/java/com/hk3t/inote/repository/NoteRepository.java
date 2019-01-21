package com.hk3t.inote.repository;

import com.hk3t.inote.model.Note;
import com.hk3t.inote.model.NoteType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NoteRepository extends PagingAndSortingRepository <Note, Integer> {
    Page <Note> findAllByTitleContaining(String title, Pageable pageable);

    Page <Note> findAllByNoteType(NoteType noteType, Pageable pageable);
}