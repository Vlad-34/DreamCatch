package com.example.dreamcatch.factory;

public class StressFactory extends MetricFactory {
    @Override
    protected IMetricChart createMetricChart() {
        return new StressChart();
    }
}
