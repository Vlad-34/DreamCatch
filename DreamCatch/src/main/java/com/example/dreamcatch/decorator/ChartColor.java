package com.example.dreamcatch.decorator;

import com.example.dreamcatch.model.MetricChart;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

public class ChartColor implements IChartColor {
    @Override
    public ResponseEntity<MetricChart> updateColor(ResponseEntity<MetricChart> chart, String chartType) {
        float average = 0;
        for(Integer dataPoint : Objects.requireNonNull(chart.getBody()).getDataPoints())
            average += dataPoint;
        average /= chart.getBody().getDataPoints().size();
        chart.getBody().setColor("#2ECC71");
        switch(chartType) {
            case "duration":
                if(average < 4)
                    chart.getBody().setColor("#FF5733");
                break;
            case "stress":
                if(average > 2)
                    chart.getBody().setColor("#FF5733");
                break;
            case "energy":
                if(average < 3)
                    chart.getBody().setColor("#FF5733");
                break;
        }
        return chart;
    }
}
