// Done by Jannatul 
import {
  BrowserRouter as Router,
  Routes,
  Route,
} from "react-router-dom"

import Home from "./Allpages/Home";
import Add from "./Allpages/Add";
import Recipe from "./Allpages/Recipe";
import Login from "./Allpages/Login";
import Navbar from "./components/Navbar";
import Footer from "./components/Footer"
import SignupForm from "./components/signupForm"
import PrivateRoute from "./components/PrivateRoute";
import SpecificRecipe from "./components/SpecificRecipe";
import { createContext, useState, useEffect, useHistory } from "react";

export const UserContext = createContext();

export const UserProvider = ({ children }) => {
  const [loggedInUser, setLoggedInUser] = useState();

  useEffect(() => {
    // Check if the userId is already stored in localStorage
    const storedUserId = localStorage.getItem('userId');
    if (storedUserId) {
      setLoggedInUser(storedUserId);
    }
  }, []);

  useEffect(() => {
    // Store the userId in localStorage whenever it changes
    if (loggedInUser) {
      localStorage.setItem('userId', loggedInUser);
    } else {
      localStorage.removeItem('userId');
    }
  }, [loggedInUser]);

  return (
    <UserContext.Provider value={{ loggedInUser, setLoggedInUser }}>
      {children}
    </UserContext.Provider>
  );
};

function App() {
  return (
    <Router>
      <Navbar />
      <div className="container main">
      <UserProvider>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/recipe" element={<Recipe />} />
          <Route path="/recipe/:recipeId" element={<SpecificRecipe />} />
          <Route path='/add'
                 element={
                 <PrivateRoute  component={Add}/>
                 }/>
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<SignupForm />} />
        </Routes>
        </UserProvider>
      </div>
      <Footer />
    </Router>
  );
}

export default App;
