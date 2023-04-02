import ApexCharts from "apexcharts";
import React from "react";

interface Props {
  title: string;
  data: number[];
  timestamps: string[];
}

export const MyChart = ({ title, data, timestamps }: Props) => {
  const chartRef = React.useRef(null);

  React.useEffect(() => {
    const chartData = {
      series: [
        {
          title: title,
          data: data,
        },
      ],
      xaxis: {
        categories: timestamps,
      },
    };

    const chartOptions = {
      chart: {
        type: "area",
        height: 350,
        width: "200%",
      },
      dataLabels: {
        enabled: true,
      },
      series: chartData.series,
      xaxis: chartData.xaxis,
      title: {
        text: title,
        align: "center",
      },
    };

    const chart = new ApexCharts(chartRef.current, chartOptions);
    chart.render();

    return () => {
      chart.destroy();
    };
  }, [title, data, timestamps]);

  return (
    <React.Fragment>
      <div ref={chartRef}></div>
    </React.Fragment>
  );
};
