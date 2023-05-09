package com.example.dreamcatch.decorator;

import com.example.dreamcatch.model.MetricChart;
import org.springframework.http.ResponseEntity;

public interface IChartColor {
    ResponseEntity<MetricChart> updateColor(ResponseEntity<MetricChart> chart, String chartType);
}
