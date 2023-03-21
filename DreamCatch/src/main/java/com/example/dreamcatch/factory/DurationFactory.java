package com.example.dreamcatch.factory;

public class DurationFactory extends MetricFactory {
    @Override
    public MetricChart createChart() {
        return new DurationChart();
    }
}
