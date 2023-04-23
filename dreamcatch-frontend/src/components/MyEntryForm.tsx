import { Fragment, useEffect, useState } from "react";
import MyNavbar from "./MyNavbar";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import Select from "react-select";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { MyChart } from "./MyChart";

function MyEntryForm() {
  const [description, setDescription] = useState("");
  const [tags, setTags] = useState([] as string[]);
  const [startDate, setStartDate] = useState<Date | null>(null);
  const [duration, setDuration] = useState("");
  const [energy, setEnergy] = useState("");
  const [stress, setStress] = useState("");

  const [title, setTitle] = useState("");
  const [chartType, setChartType] = useState("");
  const [strategy, setStrategy] = useState("");
  const [dataPoints, setDataPoints] = useState([] as number[]);
  const [timestamps, setTimestamps] = useState([] as string[]);
  const [userId, setUserId] = useState(0);

  useEffect(() => {
    const id = Number(localStorage.getItem("id"));
    if (id) {
      setUserId(id);
    }
  }, []);

  useEffect(() => {
    console.log({ chartType });
  }, [chartType]);

  useEffect(() => {
    console.log({ strategy });
  }, [strategy]);

  const chartOptions = [
    { value: "duration", label: "Duration" },
    { value: "energy", label: "Energy" },
    { value: "stress", label: "Stress" },
  ];

  const strategyOptions = [
    { value: "weekly", label: "Weekly" },
    { value: "monthly", label: "Monthly" },
  ];

  const tagOptions = [
    { value: "HAPPY", label: "Happy" },
    { value: "SAD", label: "Sad" },
    { value: "ENERGIZED", label: "Energized" },
    { value: "EXHAUSTED", label: "Exhausted" },
    { value: "SURPRIZED", label: "Surprized" },
    { value: "SCARED", label: "Scared" },
  ];

  const handleSelectChart = (selectedOption: any) => {
    setChartType(selectedOption.value);
    console.log({ chartType });
  };

  const handleSelectStrategy = (selectedOption: any) => {
    setStrategy(selectedOption.value);
    console.log({ strategy });
  };

  const handleGet = () => {
    if (startDate) {
      const dateString = startDate.toISOString().substr(0, 10);
      console.log(dateString);

      fetch(
        `http://localhost:8080/entry/get-chart?chartType=${chartType}&userId=${userId}&startDate=${dateString}&strategy=${strategy}`,
        {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
          },
          mode: "cors",
        }
      )
        .then((response) => {
          if (response.ok) return response.json();
        })
        .then((data) => {
          console.log("Response body:", data);
          setTitle(data.title);
          setDataPoints(data.dataPoints);
          setTimestamps(data.timestamps);
          // const { title, dataPoints, timestamps } = data;
          console.log(title, dataPoints, timestamps);
        })
        .catch((error) => console.error("Error fetching data", error));
    }
  };

  const handleSelectTags = (selectedOptions: any) => {
    const selectedTags = selectedOptions.map((option: any) => option.value);
    console.log({ selectedTags });
    setTags(selectedTags);
  };

  const handleSelectStartDate = (date: Date | null) => {
    setStartDate(date);
  };

  const handleSubmit = (event: { preventDefault: () => void }) => {
    event.preventDefault();
    fetch("http://localhost:8080/entry/add", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        description,
        tags,
        duration,
        energy,
        stress,
        userId,
      }),
    })
      .then((data) => {
        console.log(data);
      })
      .catch((error) => console.error(error));
  };

  return (
    <Fragment>
      <MyNavbar />
      <div
        style={{
          display: "flex",
        }}
      >
        <div
          style={{
            textAlign: "center",
            verticalAlign: "middle",
            marginLeft: "20%",
            marginTop: "8%",
          }}
        >
          <Form>
            <Form.Group className="mb-3" controlId="formTextareaDescription">
              <Form.Label>Description</Form.Label>
              <Form.Control
                as="textarea"
                rows={3}
                placeholder="Enter dream description"
                onChange={(event) => setDescription(event.target.value)}
              />
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicDuration">
              <Form.Label>Duration</Form.Label>
              <Form.Control
                type="number"
                placeholder="Enter duration (in hours)"
                onChange={(event) => setDuration(event.target.value)}
              />
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicEnergy">
              <Form.Label>Energy</Form.Label>
              <Form.Control
                type="number"
                placeholder="Enter energy level (1-5)"
                onChange={(event) => setEnergy(event.target.value)}
              />
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicStress">
              <Form.Label>Stress</Form.Label>
              <Form.Control
                type="number"
                placeholder="Enter stress level (1-5)"
                onChange={(event) => setStress(event.target.value)}
              />
            </Form.Group>

            <Select options={tagOptions} onChange={handleSelectTags} isMulti />

            <Button variant="success" type="submit" onClick={handleSubmit}>
              Submit Dream
            </Button>
          </Form>
        </div>
        <div
          style={{
            textAlign: "center",
            verticalAlign: "middle",
            marginLeft: "15%",
            marginRight: "5%",
            marginTop: "10%",
          }}
        >
          <Select options={chartOptions} onChange={handleSelectChart} />

          <DatePicker
            selected={startDate}
            onChange={handleSelectStartDate}
            dateFormat="yyyy-MM-dd"
            isClearable
            showYearDropdown
            scrollableMonthYearDropdown
            maxDate={new Date()}
          />

          <Select options={strategyOptions} onChange={handleSelectStrategy} />

          <Button variant="success" type="submit" onClick={handleGet}>
            Get Chart
          </Button>

          <MyChart title={title} data={dataPoints} timestamps={timestamps} />
        </div>
      </div>
    </Fragment>
  );
}

export default MyEntryForm;
