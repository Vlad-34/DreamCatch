package com.example.dreamcatch.factory;

public class EnergyFactory extends MetricFactory {
    protected IMetricChart createMetricChart() {
        return new EnergyChart();
    }
}
