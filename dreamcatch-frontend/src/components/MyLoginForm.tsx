import { Fragment, useState } from "react";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import { Navigate } from "react-router-dom";
import MyNavbar from "./MyNavbar";

export const MyUserForm = () => {
  const [ok, setOk] = useState(false);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = (event: { preventDefault: () => void }) => {
    event.preventDefault();
    fetch("http://localhost:8080/user/login", {
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
        localStorage.setItem("id", data.id);
        setOk(true);
      })
      .catch((error) => console.error(error));
  };

  return (
    <Fragment>
      <MyNavbar />
      <div>{ok && <Navigate to="../dream" />}</div>
      <div
        style={{
          textAlign: "center",
          verticalAlign: "middle",
          marginLeft: "40%",
          marginRight: "40%",
          marginTop: "15%",
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

          <Button variant="success" type="submit" onClick={handleLogin}>
            Login
          </Button>
        </Form>
      </div>
    </Fragment>
  );
};

export default MyUserForm;
