package com.example.dreamcatch.factory;

import com.example.dreamcatch.model.Entry;
import com.example.dreamcatch.model.MetricChart;

import java.util.ArrayList;
import java.util.List;

public class StressChart implements IMetricChart {
    @Override
    public MetricChart generateChart(List<Entry> metricsValues) {
        MetricChart metricChart = new MetricChart();
        metricChart.setTitle("Stress Chart");
        for (Entry entry : metricsValues) {
            metricChart.getDataPoints().add(entry.getStress());
            metricChart.getDates().add(entry.getDate());
        }
        return metricChart;
    }
}