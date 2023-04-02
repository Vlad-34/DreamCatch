import { Fragment } from "react";
import MyNavbar from "../components/MyNavbar";
import { useNavigate } from "react-router-dom";

export const HomePage = () => {
  let navigate = useNavigate();

  function handleRegister() {
    navigate("../register");
  }

  function handleLogin() {
    navigate("../login");
  }

  return (
    <Fragment>
      <MyNavbar />
      <div
        style={{
          textAlign: "center",
          verticalAlign: "middle",
          marginTop: "21%",
        }}
      >
        <button
          type="button"
          className="btn btn-success"
          onClick={handleRegister}
        >
          Register
        </button>
      </div>
      <div
        style={{
          textAlign: "center",
          verticalAlign: "middle",
          marginTop: "2%",
        }}
      >
        <button type="button" className="btn btn-success" onClick={handleLogin}>
          Login
        </button>
      </div>
    </Fragment>
  );
};

export default HomePage;
