package com.example.dreamcatch.factory;

public class DurationFactory extends MetricFactory {
    @Override
    protected IMetricChart createMetricChart() {
        return new DurationChart();
    }
}
