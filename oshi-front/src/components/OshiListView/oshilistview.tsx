import React from 'react';
import './style.css';
import { Oshiitem } from 'types/interface';
import { useNavigate } from 'react-router-dom';  // useNavigate 훅을 가져옴

interface Props {
  Oshiinfo: Oshiitem;
}

export default function OshiListView({ Oshiinfo }: Props) {
  const { oshiId, oshiName, oshiImage } = Oshiinfo;
  const navigate = useNavigate();  // useNavigate 훅 초기화
  // 클릭 핸들러 함수 정의
  const handleClick = () => {
    navigate(`/oshi/show/${oshiId}/category`);  // 해당 경로로 내비게이션
  };

  return (
    <div className='main-middle'>
      <div className='oshi-box'>
        <div className='image-box'>
          <div 
            className='oshi-image' 
            style={{ backgroundImage: `url(${oshiImage})` }} 
            onClick={handleClick}  // 이미지 박스 클릭 시 handleClick 호출
          ></div>
        </div>
      </div>
    </div>
  );
}