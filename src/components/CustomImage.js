// Done by Jannatul 

import React from 'react'
import "./home.scss"

function CustomImage({imgSrc,pt}) {
  return (
    <div className='custom-image' style={{paddingTop: pt}}>
      <img src={imgSrc} alt=""/>
    </div>
  )
}

export default CustomImage
