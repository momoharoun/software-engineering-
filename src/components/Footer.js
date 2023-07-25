// Done by Jannatul 


import React from 'react'
import "./footer.scss"

function Footer() {
  return (
    <div className='footer container'>
      <div className='footer-section'>
         <p className='title'>Recipico.com</p>
         <p>Recipico is a free App where you can find delicious recipes of different categories to please your both body and mind.</p>
         <p>&copy; 2021 | All Rights Reserved</p>
      </div>
      <div className='footer-section'>
      <p className="title">Contact Us</p>
                <p>recipico@gmail.com</p>
                <p>+4901929394261</p>
                <p>Deggendorf, Germany</p>
      </div>
      <div className='footer-section'>
      <p className="title">Socials</p>
                <p>Facebook</p>
                <p>Twitter</p>
                <p>Instagram</p>
      </div>
    </div>
  )
}
export default Footer
