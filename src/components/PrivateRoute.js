import React, { useContext } from 'react'
import { Navigate, Outlet, Route, Routes } from 'react-router-dom'

import AddRecipe from './AddRecipe';
import { UserContext } from '../App';

function PrivateRoute({component: Component, ...rest}) {
    const { loggedInUser } = useContext(UserContext);
  return (
    loggedInUser ? <Component {...rest} /> : <Navigate to="/login" />
    
  )
}

export default PrivateRoute
