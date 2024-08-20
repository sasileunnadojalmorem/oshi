import React from 'react'
import './style.css'
import Footer from 'layout/Footer'
import Header from 'layout/Header'
import { Outlet, useLocation } from 'react-router-dom'

//component : 레이아웃//
export default function Container() {
  // state: 현재 페이지 path name 상태
  const {pathname} = useLocation();
  console.log(pathname);
    //render: 레이아웃//
  return (
    <div>
      <Header/>
      <Outlet/>
      <Footer/>
    </div>
  )
}
