// Done by Wasik and Jannatul 

import React, { useState } from "react";
import "./loginform.css";
import { Link } from "react-router-dom";

const LoginForm = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();

    // Send the username and password to the backend
    sendCredentials(username, password);
  };

  const sendCredentials = (username, password) => {
    const axios = require("axios");

async function sendCredentials(username, password) {
  try {
    const data = {
      username: username,
      password: password
    };

    const response = await axios.post("http://localhost:8080/login", data);
    console.log("Credentials sent to the backend:", response.data);
    // Process the response from the backend as needed
  } catch (error) {
    console.error("Error sending credentials:", error);
  }
}

// Call the sendCredentials function with the desired username and password
sendCredentials("exampleUser", "examplePassword");
  };

  return (
    <div className="cont cover">
      <h1>Login</h1>
      
        <input onSubmit={handleSubmit}
          type="text"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <input onSubmit={handleSubmit}
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <button onSubmit={handleSubmit} className="login-btn" type="submit">LOGIN</button>

      <h3>Or</h3>
      <div className="create-account">
        <Link to="/signup">Create New Account</Link>
      </div>
    </div>
  );
};

export default LoginForm;