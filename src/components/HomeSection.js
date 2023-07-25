// Done by Jannatul 


import CustomImage from "./CustomImage"
import "./home.scss"
import React from 'react'
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome"
import {faQuoteLeft} from "@fortawesome/free-solid-svg-icons"
import {Link} from "react-router-dom"

function HomeSection() {
  const images = [
    "/gallery/img1.avif",
    "/gallery/img2.avif",
    "/gallery/img3.avif",
    "/gallery/img4.avif",
    "/gallery/img5.avif",
    "/gallery/img6.avif.jpg",
    "/gallery/img7.avif.jpg",
    "/gallery/img8.avif.jpg",
    "/gallery/img9.avif.jpg",
  ]
  return (
    <>
    <div className="home-section top" >
        <div className="col typography">
          <h1 className="tittle">About Us</h1>
          <p className="info">Recipico is a free App where you can find delicious recipes of different categories to please your both body and mind.So start Exploring our Recipes for free now and become part of our community!</p>
          <Link to="/signup" className="btn">SIGN UP FOR FREE</Link>
        </div>
        <div className="col gallery">
          {images.map((src, index)=> (<CustomImage key={index} imgSrc={src} pt={"85%"}/>))}
        </div>
    </div>
    <div className="home-section bottom" >
       <div className="col img">
          <img src="/gallery/img10.avif" alt=""/>
        </div>
        <div className="col typography">
          <h1 className="tittle">Share Recipes</h1>
          <p className="info">Recipico is an interesting app where you can share your recipes to the whole world and show your culinary skills. It is also a platform where you can share your ethnicity,region, tribe, personal history through food.</p>
          <Link to="/add" className="btn">ADD RECIPES</Link>
        </div>
    </div>
    <div className="home-section quote">
       <p className="quote-text"><FontAwesomeIcon icon = {faQuoteLeft}/>
        Let food be thy medicine and medicine be thy food.</p>
       <p className="quote-author">–Hippocrates</p>
    </div>
    </>
  )
}

export default HomeSection
