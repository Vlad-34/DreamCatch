package com.example.dreamcatch.strategy;

import com.example.dreamcatch.model.Entry;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MonthlyStrategy implements IStrategy {
    @Override
    public List<Entry> execute(List<Entry> entries, String startDate) {
        List<Entry> filteredList = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = df.parse(startDate);
            for(Entry entry : entries) {
                long daysBetween = ChronoUnit.DAYS.between(entry.getDate().toInstant(), start.toInstant());
                if(daysBetween <= 30)
                    filteredList.add(entry);

            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return filteredList;
    }
}
