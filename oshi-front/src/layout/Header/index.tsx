import React from 'react'
import './style.css'
import { useNavigate } from 'react-router-dom'
import { MAIN_PATH } from 'constant';

//component : 헤더 레이아웃//
export default function Header() {
    // function 네비게이트 함수
    const navigate = useNavigate();
    // event handler 로고 클릭 처리 함수
    const onoshilogclickhandler = () =>{
        navigate(MAIN_PATH());
    }
  return (
    <div className='header'>
        <div className='header-container'>
            <div className='header-top'>
                <div className='header-logo-box'>
                    <div className='icon-box'>
                        <div className='icon oshi-logo' onClick={onoshilogclickhandler}></div>
                    </div>
                    <div className='header-logo-text text'>{'굿즈 정보 공유 플랫폼'}</div>
                </div>
            </div>
            <div className='header-middle'>
                <div className='header-search'>{'최애를 검색하세요'}</div>
            </div>

            <div className='header-bottom'>
                <div className='header-user-box'></div>
            </div>

        </div>
    </div>
  )
}
