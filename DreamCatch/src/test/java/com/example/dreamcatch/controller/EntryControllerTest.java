package com.example.dreamcatch.controller;

import com.example.dreamcatch.model.Entry;
import com.example.dreamcatch.model.MetricChart;
import com.example.dreamcatch.service.IEntryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EntryControllerTest {

    @InjectMocks
    private EntryController entryController;

    @Mock
    private IEntryService entryService;

    private Entry testEntry;
    private List<Entry> testEntries;
    private MetricChart testChart;
    private String[] testTags;

    @BeforeEach
    void setUp() {
        testEntry = new Entry(
                1L,
                "Test entry",
                Arrays.asList("HAPPY", "EXHAUSTED"),
                new Date(),
                60,
                5,
                3,
                1L
        );
    }

    @Test
    public void getAllEntriesTest() {
        when(entryService.getAll()).thenReturn(testEntries);

        List<Entry> result = entryController.getAllEntries();

        assertEquals(testEntries, result);
        verify(entryService, times(1)).getAll();
    }

    @Test
    public void getUserByIdTest() {
        when(entryService.getById(1L)).thenReturn(new ResponseEntity<>(testEntry, HttpStatus.OK));

        ResponseEntity<Entry> result = entryController.getUserById(1L);

        assertEquals(testEntry, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(entryService, times(1)).getById(1L);
    }

    @Test
    public void addTest() {
        when(entryService.save(testEntry)).thenReturn(
                new ResponseEntity<>(testEntry, HttpStatus.CREATED));

        ResponseEntity<Entry> result = entryController.add(testEntry);

        assertEquals(testEntry, result.getBody());
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        verify(entryService, times(1)).save(testEntry);
    }

    @Test
    public void editUserTest() {
        when(entryService.edit(1L, testEntry)).thenReturn(
                new ResponseEntity<>(testEntry, HttpStatus.OK));

        ResponseEntity<Entry> result = entryController.editUser(1L, testEntry);

        assertEquals(testEntry, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(entryService, times(1)).edit(1L, testEntry);
    }

    @Test
    public void deleteTest() {
        when(entryService.delete(1L)).thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));

        ResponseEntity<Entry> result = entryController.delete(1L);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(entryService, times(1)).delete(1L);
    }

    @Test
    public void createChartTest() {
        when(entryService.createChart("duration", 1L)).thenReturn(
                new ResponseEntity<>(testChart, HttpStatus.OK));

        ResponseEntity<MetricChart> result = entryController.createChart("duration", 1L);

        assertEquals(testChart, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(entryService, times(1)).createChart("duration", 1L);
    }

    @Test
    public void createChartNotFoundTest() {
        when(entryService.createChart("invalid", 1L)).thenReturn(
                new ResponseEntity<>(HttpStatus.NOT_FOUND));

        ResponseEntity<MetricChart> result = entryController.createChart("invalid", 1L);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        verify(entryService, times(1)).createChart("invalid", 1L);
    }
}