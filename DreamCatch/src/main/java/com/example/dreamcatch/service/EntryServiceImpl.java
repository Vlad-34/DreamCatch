package com.example.dreamcatch.service;

import com.example.dreamcatch.factory.DurationFactory;
import com.example.dreamcatch.factory.EnergyFactory;
import com.example.dreamcatch.factory.MetricFactory;
import com.example.dreamcatch.factory.StressFactory;
import com.example.dreamcatch.model.Entry;
import com.example.dreamcatch.model.MetricChart;
import com.example.dreamcatch.repository.IEntryRepository;
import com.example.dreamcatch.strategy.IStrategy;
import com.example.dreamcatch.strategy.MonthlyStrategy;
import com.example.dreamcatch.strategy.WeeklyStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EntryServiceImpl implements IEntryService {
    @Autowired
    private IEntryRepository entryRepository;

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
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        String formattedDate = dateFormat.format(currentDate);
        Date date;
        try {
            date = dateFormat.parse(formattedDate);
            entry.setDate(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        entryRepository.save(entry);
        return ResponseEntity.created(URI.create("/entry/" + entry.getId())).body((entry));
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

    @Override
    public ResponseEntity<MetricChart> createChart(String chartType, Long userId, String startDate, String strategy) {
        MetricFactory metricFactory;
        IStrategy iStrategy = switch (strategy) {
            case "weekly" -> new WeeklyStrategy();
            case "monthly" -> new MonthlyStrategy();
            default -> null;
        };
        List<Entry> entries = entryRepository.findAllByUserId(userId);
        if(entries.isEmpty()) {
            return ResponseEntity.ok().build();
        } else {
            assert iStrategy != null;
            switch (chartType) {
                case "duration" -> {
                    metricFactory = new DurationFactory();
                    return ResponseEntity.ok(metricFactory.makeChart(iStrategy.execute(entries, startDate)));
                }
                case "energy" -> {
                    metricFactory = new EnergyFactory();
                    return ResponseEntity.ok(metricFactory.makeChart(iStrategy.execute(entries, startDate)));
                }
                case "stress" -> {
                    metricFactory = new StressFactory();
                    return ResponseEntity.ok(metricFactory.makeChart(iStrategy.execute(entries, startDate)));
                }
            }
        }
        return ResponseEntity.ok().build();
    }
}
