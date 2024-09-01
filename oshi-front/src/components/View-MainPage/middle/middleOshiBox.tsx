import React from 'react';
import './style.css';
import { Oshiitem } from 'types/interface';

interface Props {
  Oshiinfo: Oshiitem;
}

export default function MiddleOshiBox({ Oshiinfo }: Props) {
  const { oshiId, oshiName, oshiImage } = Oshiinfo;
  
  return (
    <div className='main-middle-container'>
      <div className='oshi-box-container'>
        <div className='oshi-image-container'>
          <div className='oshi-image-background' style={{ backgroundImage: `url(${oshiImage})` }}></div>
          <div className='oshi-name-text'>{oshiName}</div> 
        </div>
      </div>
    </div>
  );
}
