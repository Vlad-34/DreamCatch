package com.example.dreamcatch.factory;

import com.example.dreamcatch.model.Entry;
import com.example.dreamcatch.model.MetricChart;

import java.util.List;

public interface IMetricChart {
    MetricChart generateChart(List<Entry> metricsValues);
}
