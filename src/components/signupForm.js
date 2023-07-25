// Done by Wasik and Jannatul

import "./signupForm.css";
import { Link } from "react-router-dom";
import React, { useContext, useState } from "react";
import { UserContext } from "../App";
import { useLocation, useNavigate } from "react-router-dom";

const SignupForm = () => {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [email, setEmail] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    const newUser = {
      firstName: firstName,
      lastName: lastName,
      username: username,
      password: password,
      email: email,
    };

    try {
      const res = fetch("http://localhost:8080/users", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(newUser),
      });

      console.log("User created successfully:", res);
    } catch (error) {
      console.log("An error occurred while creating the new user:", error);
    }
    // Reset form fields
    // Send the username and password to the backend
    setUsername("");
    setPassword("");
    setFirstName("");
    setLastName("");
    setEmail("");
  };

  // Call the sendCredentials function with the desired username and password
  //sendCredentials("exampleUser", "examplePassword");

  return (
    <div>
      <form onSubmit={handleSubmit} className="cover-sign">
        <h1>Sign Up</h1>
        <input
          type="text"
          placeholder="First Name"
          value={firstName}
          onChange={(e) => setFirstName(e.target.value)}
        />
        <input
          type="text"
          placeholder="Last Name"
          value={lastName}
          onChange={(e) => setLastName(e.target.value)}
        />
        <input
          type="text"
          placeholder="User Name"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <input
          type="text"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <button id="SignUpbutton" type="submit">
          Sign Up
        </button>
        <div className="already-account">
          <Link to="/login">Already have an account?</Link>
        </div>
      </form>
    </div>
  );
};

export default SignupForm;
