package com.example.dreamcatch.controller;

import com.example.dreamcatch.model.Entry;
import com.example.dreamcatch.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/entry")
public class EntryController {
    @Autowired
    private EntryService entryService;

    @GetMapping("/get")
    public List<Entry> getAllEntries() {
        return entryService.getAll();
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Entry> getUserById(@PathVariable Long id) {
        return entryService.getById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Entry> add(@RequestBody Entry entry) {
        return entryService.save(entry);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Entry> editUser(@PathVariable Long id, @RequestBody Entry entryDetails) {
        return entryService.edit(id, entryDetails);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Entry> delete(@PathVariable Long id) {
        return entryService.delete(id);
    }
}
