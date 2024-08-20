import React from 'react';
import Footer from 'layout/footer';
import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Main from 'views/Main';  
import Authentication from 'views/Authentification';

// component : Application 컴포넌트 
function App() {

  //render: Application 컴포넌트
  //description 메인화면 : '/' + Main
  //description 회원가입 + 로그인 : '/auth' +  Authentication
  //description 오시 추가하기 : '/oshi/add/' - oshiadd
  //description 오시-카테고리별로 보기 : '/oshi/show/category/' - oshishowcategory
  //description 오시-굿즈별로 보기 : '/oshi/show/' - oshishow
  //description 굿즈 추가하기: 'goods/add/' - goodsadd
  //description 상품 판매하기: 'goods/sale' - goodssale
  //description 굿즈 상세보기: 'goods/show/:goodsid' - goodsshow
  //description 상품 상세보기: 'goods/sales/:goodsid' - goodssaleshow
  //description 유저 페이지: 'user/:userEmail' - user

  return (
    <Router>
      <Routes>
      {/* Route 태그의 셀프 클로징 수정 */}
        <Route path='auth' element={<Authentication/>}/>
        <Route path='/' element={<Main/>} />
        
      </Routes>
    </Router>
    
  );
}

export default App;
