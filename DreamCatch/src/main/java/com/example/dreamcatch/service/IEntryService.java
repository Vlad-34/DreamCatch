package com.example.dreamcatch.service;

import com.example.dreamcatch.model.Entry;
import com.example.dreamcatch.model.MetricChart;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IEntryService {
    List<Entry> getAll();
    ResponseEntity<Entry> getById(Long id);
    ResponseEntity<Entry> save(Entry entry);
    ResponseEntity<Entry> edit(Long id, Entry entryDetails);
    ResponseEntity<Entry> delete(Long id);
    ResponseEntity<MetricChart> createChart(String chartType, Long userId);
}
