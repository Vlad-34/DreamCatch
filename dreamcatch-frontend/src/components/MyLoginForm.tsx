import { Fragment, useState } from "react";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import { Navigate } from "react-router-dom";
import MyNavbar from "./MyNavbar";

export const MyUserForm = () => {
  const [ok, setOk] = useState(false);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async (event: { preventDefault: () => void }) => {
    event.preventDefault();
    if (ok === true) {
      const response = await fetch("http://localhost:8080/user/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ username, password }),
      });
      // .then((data) => console.log(data))
      // .catch((error) => console.error(error));
      if (response.ok) {
        setOk(true);
      }
    }
  };

  return (
    <Fragment>
      //
      <MyNavbar />
      <div>{ok && <Navigate to="../home" />}</div>
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
    </Fragment>
  );
};

export default MyUserForm;
