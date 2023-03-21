import { Fragment, useState } from "react";
import MyNavbar from "./MyNavbar";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";

function MyEntryForm() {
  const [description, setDescription] = useState("");
  const [duration, setDuration] = useState("");
  const [energy, setEnergy] = useState("");
  const [stress, setStress] = useState("");

  const handleSubmit = (event: { preventDefault: () => void }) => {
    event.preventDefault();

    fetch("http://localhost:8080/entry/add", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ description, duration, energy, stress }),
    })
      .then((data) => console.log(data))
      .catch((error) => console.error(error));
  };

  return (
    <Fragment>
      //
      <MyNavbar />
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

        <Button variant="success" type="submit" onClick={handleSubmit}>
          Submit Dream
        </Button>
      </Form>
    </Fragment>
  );
}

export default MyEntryForm;
