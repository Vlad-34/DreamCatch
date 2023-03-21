package com.example.dreamcatch.service;

import com.example.dreamcatch.model.Entry;
import com.example.dreamcatch.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class EntryServiceImpl implements EntryService {
    @Autowired
    private EntryRepository entryRepository;

    @Override
    public List<Entry> getAll() {
        return entryRepository.findAll();
    }

    @Override
    public ResponseEntity<Entry> getById(Long id) {
        Entry entry = entryRepository.findById(id).orElse(null);
        if (entry != null)
            return ResponseEntity.ok(entry);
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Entry> save(Entry entry) {
        entryRepository.save(entry);
        return ResponseEntity.created(URI.create("/users/" + entry.getId())).body((entry));
    }

    @Override
    public ResponseEntity<Entry> edit(Long id, Entry entryDetails) {
        Entry entry = entryRepository.findById(id).orElse(null);
        if(entry != null) {
            entry.setDescription(entryDetails.getDescription());
            entry.setDuration(entryDetails.getDuration());
            entry.setEnergy(entryDetails.getEnergy());
            entry.setStress(entryDetails.getStress());
            return ResponseEntity.ok(entry);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Entry> delete(Long id) {
        Optional<Entry> entry = entryRepository.findById(id);
        if(entry.isPresent()) {
            entryRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
