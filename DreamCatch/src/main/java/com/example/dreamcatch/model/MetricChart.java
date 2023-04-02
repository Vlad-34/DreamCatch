package com.example.dreamcatch.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MetricChart {
    private String title;
    private List<Date> dates = new ArrayList<>();
    private List<Integer> dataPoints = new ArrayList<>();
}
