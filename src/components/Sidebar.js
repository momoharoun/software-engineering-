// Done by Jannatul 

import React from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import "./sidebar.scss"
import {Link} from "react-router-dom";


function Sidebar({links, close}) {
  return (
    <div className='sidebar' onClick = {close}>
      {links.map(link => (
        <Link to={link.path} className='sidebar-link' href = "#!" key= {link.name}>
          <FontAwesomeIcon icon = {link.icon} />
          {link.name}
        </Link> 
      ))}
      <p className='sidebar-text'>click here to close</p>
      
    </div>
  )
}

export default Sidebar
