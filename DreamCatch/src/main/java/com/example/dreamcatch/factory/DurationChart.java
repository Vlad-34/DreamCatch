package com.example.dreamcatch.factory;

import com.example.dreamcatch.model.Entry;
import com.example.dreamcatch.model.MetricChart;

import java.util.List;

public class DurationChart implements IMetricChart {
    @Override
    public MetricChart generateChart(List<Entry> metricsValues) {
        MetricChart metricChart = new MetricChart();
        metricChart.setTitle("Duration Chart");
        for (Entry entry : metricsValues) {
            metricChart.getDataPoints().add(entry.getDuration());
            metricChart.getDates().add(entry.getDate());
        }
        return metricChart;
    }
}
