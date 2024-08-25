import React from 'react'
import './style.css'
export default function header() {
    //메인 페이지 프로젝트 소개
  return (
    <div>
        <div className="top">{'Project'}</div>
        <div className="middle">{'Oshi'}</div>
        <div className="bottom">{'최애의 굿즈 정보를 공유하고 쉽게 사고 팔아요!'}</div>
    </div>
  )
}
