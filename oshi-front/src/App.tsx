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
import Footer from 'layout/Footer';

function App() {
    return (
      <Router>
      
      <Routes>
        <Route element={<Container/>}>
          <Route path={MAIN_PATH()} element={<Main/>} />
          <Route path={AUTH_PATH()} element={<Authentication/>}/>
          <Route path={OSHI_PATH()}>
            <Route path={ADD_PATH()} element={<Oshiadd/>}/>
            <Route path={OSHI_SHOW_PATH(':oshid')}>
              <Route path={CATEGORY_PATH()} element={<Oshilistcategory/>}/>
              <Route path={GOODS_PATH()} element={<Oshilistgoods/>}/>
            </Route>
          </Route>
          <Route path={`/goods/:oshiid`}>
            <Route path={ADD_PATH()} element={<Goodsadd/>}/>
            <Route path={GOODS_UPDATE_PATH(':goodsid')} element={<Goodsupdate/>}/>
            <Route path={GOODS_DETAIL_PATH(':goodsid')} element={<Goodsdetail/>}/>
          </Route>
          <Route path={SALE_PATH()}>
            <Route path={ADD_PATH()} element={<Salesadd/>}/>
            <Route path={SALES_DETAIL_PATH(':salesid')} element={<Salesdetail/>}/>
            <Route path={SALES_UPDATE_PATH(':salesid')} element={<Salesupdate/>}/>
          </Route>  
          <Route path={USER_PATH(':userEmail')} element={<User/>}/> 
        </Route>
        <Route path='*' element={<h1>404 not found</h1>}/>
      </Routes>
    </Router>
  );
}

export default App;
