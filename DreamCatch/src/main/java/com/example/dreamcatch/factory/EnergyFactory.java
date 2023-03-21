package com.example.dreamcatch.factory;

public class EnergyFactory extends MetricFactory {
    @Override
    public MetricChart createChart() {
        return new EnergyChart();
    }
}
