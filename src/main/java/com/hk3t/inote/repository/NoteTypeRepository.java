package com.hk3t.inote.repository;

import com.hk3t.inote.model.NoteType;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NoteTypeRepository extends PagingAndSortingRepository <NoteType, Integer> {
}
