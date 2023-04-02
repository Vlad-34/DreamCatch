package com.example.dreamcatch.factory;

import com.example.dreamcatch.model.Entry;
import com.example.dreamcatch.model.MetricChart;

import java.util.List;

public abstract class MetricFactory {
    public MetricChart makeChart(List<Entry> metricsValues) {
        IMetricChart metricChart = createMetricChart();
        return metricChart.generateChart(metricsValues);
    }

    protected abstract IMetricChart createMetricChart();
}
