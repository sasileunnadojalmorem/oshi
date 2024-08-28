import React from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Main from 'views/Main';  
import Authentication from 'views/Authentification';
import Oshiadd from 'views/Oshi/Oshiadd';
import Oshilistcategory from 'views/Oshi/Oshishow/Category';
import Oshilistgoods from 'views/Oshi/Oshishow/Goods';
import User from 'views/User';
import Goodsadd from 'views/Goods/add';
import Goodsupdate from 'views/Goods/update';
import Goodsdetail from 'views/Goods/detail';
import Salesadd from 'views/sale/add';
import Salesdetail from 'views/sale/detail';
import Salesupdate from 'views/sale/update';
import Container from 'layout/container';
import { ADD_PATH, AUTH_PATH, CATEGORY_PATH, GOODS_DETAIL_PATH, GOODS_PATH, GOODS_UPDATE_PATH, MAIN_PATH, OSHI_PATH, OSHI_SHOW_PATH, SALE_PATH, SALES_DETAIL_PATH, SALES_UPDATE_PATH, USER_PATH } from 'constant';
import View_MainPage from 'components/View-MainPage';
import View_Myoshi from 'components/OshiListView';
import Header from 'layout/Header';
function App() {

    return (
      <Router>
      <Routes>
      <Route path={MAIN_PATH( )} element={<footer/>}/>

      </Routes>
      {/* Container 컴포넌트를 사용하여 헤더, 푸터와 함께 페이지 렌더 */}
      {/* <Routes>
        <Route element={<Container/>}>
          
          {/* 메인 화면 */}
          {/* <Route path={MAIN_PATH()} element={<Main/>} />
          
          {/* 회원가입 및 로그인 */}
          {/* <Route path={AUTH_PATH()} element={<Authentication/>}/>
          
          {/* 오시 관련 */}
          {/* <Route path={OSHI_PATH()}>
            <Route path={ADD_PATH()} element={<Oshiadd/>}/>  {/* 오시 추가하기 */}
            {/* <Route path={OSHI_SHOW_PATH(':oshid')}>
              <Route path={CATEGORY_PATH()} element={<Oshilistcategory/>}/>  {/* 오시-카테고리별 보기 */}
              {/* <Route path={GOODS_PATH()} element={<Oshilistgoods/>}/>  {/* 오시-굿즈별 보기 */}
            {/* </Route>
          </Route>
          
          {/* 굿즈 관련 */}
          {/* <Route path={`/goods/:oshiid`}>
            <Route path={ADD_PATH()} element={<Goodsadd/>}/>  {/* 굿즈 추가하기 */}
            {/* <Route path={GOODS_UPDATE_PATH(':goodsid')}  element={<Goodsupdate/>}/>  {/* 굿즈 수정하기 */}
            {/* <Route path={GOODS_DETAIL_PATH(':goodsid')}  element={<Goodsdetail/>}/>  {/* 굿즈 상세보기 */}
          {/* </Route>

          {/* 상품 판매 관련 */}
          {/* <Route path={SALE_PATH()}>
            <Route path={ADD_PATH()} element={<Salesadd/>}/>  {/* 상품 판매 등록하기 */}
            {/* <Route path={SALES_DETAIL_PATH(':salesid')} element={<Salesdetail/>}/>  {/* 상품 판매 상세보기 */}
            {/* <Route path={SALES_UPDATE_PATH(':salesid')} element={<Salesupdate/>}/>  {/* 상품 판매 수정하기 */}
          {/* </Route>  

          {/* 유저 페이지 */}
          {/* <Route path={USER_PATH(':userEmail')} element={<User/>}/> 
        </Route>
        <Route path='*' element={<h1>404 not found</h1>}/>
      </Routes> */}
    </Router>
  );
}

export default App;
