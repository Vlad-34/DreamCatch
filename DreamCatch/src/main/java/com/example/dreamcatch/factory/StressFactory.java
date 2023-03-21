package com.example.dreamcatch.factory;

public class StressFactory extends MetricFactory {
    @Override
    public MetricChart createChart() {
        return new StressChart();
    }
}
