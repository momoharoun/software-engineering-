// Done by Wasik and Jannatul 

import React, { useContext, useState, useRef } from 'react';
import "./loginform.css";
import { Link } from "react-router-dom";
import { UserContext } from '../App';
// import { useLocation, useNavigate } from 'react-router-dom';

const LoginForm = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const { loggedInUser, setLoggedInUser } = useContext(UserContext);
  // const history = useNavigate();
  // const location = useLocation();
  // let { from } = location.state || { from :{pathname: "/add"}};  //need to be edited

  const handleSubmit = (e) => {
    e.preventDefault();

    try {
      fetch(`http://localhost:8080/login/${email}`, {
        method: "GET",
        headers: { 
          "Content-Type": "application/json",
          "password": password }
      })
      .then(res => res.json())
      .then(data => {
        if(data){
          const userId = data.id;
          console.log("User logged in successfully:", data);

          // set logged in user
          setLoggedInUser(userId);

          // message to user
          alert("You are logged in successfully!");
        }
      })
    } catch (error) {
      console.log("An error occurred while logging in:", error);
    }

    // Reset form fields
    setEmail('');
    setPassword('');
  };

  const handleLogout = () => {
    setLoggedInUser(null);
  };

  return (
    <div>
      <form onSubmit={handleSubmit}  className="cont cover">
      <h1>Login</h1>
      <div>
        <label htmlFor="username">Email:</label>
        <input
          type="text"
          id="username"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
      </div>
      <div>
        <label htmlFor="password">Password:</label>
        <input
          type="text"
          id="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
      </div>
      <button className="login-btn" type="submit">LOGIN</button>
      <h3>Or</h3>
      <div className="create-account">
        <Link to="/signup">Create New Account</Link>
      </div>
      {loggedInUser && (
          <button className="logout-btn" onClick={handleLogout}>Log Out</button>
        )}

      </form>
    </div>
  );
};

export default LoginForm;