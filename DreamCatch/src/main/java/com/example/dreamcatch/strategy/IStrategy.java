package com.example.dreamcatch.strategy;

import com.example.dreamcatch.model.Entry;

import java.util.List;

public interface IStrategy {
    List<Entry> execute(List<Entry> entries, String startDate);
}
