import React from 'react';
import './style.css';
import image from 'assets/addbutton.png'
export default function MiddleAddBox() {
  return (
    <div className='add-box'>
      <div className='oshi-box'>
        <div className='image-box'>
          <div className='oshi-image'>
            <div className='image-box'style={{backgroundImage:`url(${image})`}}></div>
          </div>
          <div className='oshi-name'>{'최애 추가'}</div> 
        </div>
      </div>
    </div>
  );
}
