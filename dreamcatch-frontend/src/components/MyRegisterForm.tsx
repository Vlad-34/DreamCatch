import { Fragment, useState } from "react";
import { Button } from "react-bootstrap";
import Form from "react-bootstrap/Form";
import { Navigate } from "react-router-dom";
import MyNavbar from "./MyNavbar";

export const MyRegisterForm = () => {
  const [ok, setOk] = useState(false);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [repeatedPassword, setRepeatPassword] = useState("");

  const handleRegister = (event: { preventDefault: () => void }) => {
    event.preventDefault();
    if (password === repeatedPassword) {
      fetch("http://localhost:8080/user/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ username, password }),
      })
        .then((response) => {
          if (response.ok) return response.json();
        })
        .then((data) => {
          console.log(data);
          setOk(true);
        })
        .catch((error) => console.error(error));
    }
  };

  return (
    <Fragment>
      <MyNavbar />
      <div>{ok && <Navigate to="../login" />}</div>
      <div
        style={{
          textAlign: "center",
          verticalAlign: "middle",
          marginLeft: "40%",
          marginRight: "40%",
          marginTop: "12%",
        }}
      >
        <Form>
          <Form.Group className="mb-3" controlId="formBasicUsername">
            <Form.Label>Username</Form.Label>
            <Form.Control
              type="text"
              placeholder="Enter your username"
              onChange={(event) => setUsername(event.target.value)}
            />
          </Form.Group>

          <Form.Group className="mb-3" controlId="formBasicPassword">
            <Form.Label>Password</Form.Label>
            <Form.Control
              type="password"
              placeholder="Enter your password"
              onChange={(event) => setPassword(event.target.value)}
            />
          </Form.Group>

          <Form.Group className="mb-3" controlId="formBasicRepeatedPassword">
            <Form.Label>Repeat password</Form.Label>
            <Form.Control
              type="password"
              placeholder="Enter your password (again)"
              onChange={(event) => setRepeatPassword(event.target.value)}
            />
          </Form.Group>

          <Button variant="success" type="submit" onClick={handleRegister}>
            Register
          </Button>
        </Form>
      </div>
    </Fragment>
  );
};

export default MyRegisterForm;
