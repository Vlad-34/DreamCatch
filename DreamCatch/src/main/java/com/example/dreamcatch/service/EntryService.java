package com.example.dreamcatch.service;

import com.example.dreamcatch.model.Entry;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EntryService {
    List<Entry> getAll();
    ResponseEntity<Entry> getById(Long id);
    ResponseEntity<Entry> save(Entry entry);
    ResponseEntity<Entry> edit(Long id, Entry entryDetails);
    ResponseEntity<Entry> delete(Long id);
}
