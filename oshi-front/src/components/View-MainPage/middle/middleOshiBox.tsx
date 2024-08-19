import React from 'react';
import './style.css';
import { Oshiitem } from 'types/interface';

interface Props {
  Oshiinfo: Oshiitem;
}

export default function MiddleOshiBox({ Oshiinfo }: Props) {
  const { oshiId, oshiName, oshiImage } = Oshiinfo;
  
  return (
    <div className='main-middle'>
      <div className='oshi-box'>
        <div className='image-box'>
          <div className='oshi-image' style={{ backgroundImage: `url(${oshiImage})` }}></div>
          <div className='oshi-name'>{oshiName}</div> 
        </div>
      </div>
    </div>
  );
}
