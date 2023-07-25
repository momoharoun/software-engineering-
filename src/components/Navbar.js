// Done by Jannatul 

import {Link} from "react-router-dom";
import "./navbar.scss";
import { FontAwesomeIcon }  from "@fortawesome/react-fontawesome";
import { faUtensils, faHome, faList, faSquarePlus,faRightToBracket} from '@fortawesome/free-solid-svg-icons';
import Sidebar from './Sidebar';

import React, { useState} from 'react';



function Navbar() {
    const [showSidebar, setShowSidebar] = useState(false);
    const links = [
        {
            name: "Home",
            path: "/",
            icon: faHome
        },
        {
            name: "Recipe",
            path: "/recipe",
            icon: faList
        },
        {
            name: "Add",
            path: "/add",
            icon: faSquarePlus
        },
        {
            name: "Login",
            path: "/login",
            icon: faRightToBracket
        }
    ]

    function closeSidebar(){
        setShowSidebar(false)
    }
  return (
    <>

        <div className='navbar container'>
            <a href="#!" className='logo'>Recipico  <FontAwesomeIcon icon = {faUtensils}></FontAwesomeIcon></a>
     
            <div className="nav-links">
                { links.map(link => (
                        <Link to={link.path} key = {link.name}>{link.name}</Link>
                    ))
                }
                {/*<a href="#!">Home</a>
                <a href="#!">Recipes</a>
                <a href="#!">Add</a>
                <a href="#!">Settings</a>*/}   
            </div>
            <div onClick={() => setShowSidebar(true)} className= {showSidebar ? "sidebar-btn active" : "sidebar-btn"}> 
                <div className='bar'></div>
                <div className='bar'></div>
                <div className='bar'></div>
            </div>
        </div>
        {showSidebar && <Sidebar close={closeSidebar} links={links}/>}
    </>
  )
}

export default Navbar
