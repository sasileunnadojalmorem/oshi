import React from 'react';
import './App.css';  // App 관련 스타일 파일
import CategoryItemList from './components/CategoryListitem';  // CategoryItemList 컴포넌트 가져오기
import OshiListItem from 'components/OshiListItem';
import { goodsmock,oshimock,categorymock } from 'mocks';
function App() {
  return (
    <div className="App">
     {oshimock.map((oshi, index) => (
        <OshiListItem key={index} oshiitem={oshi} categoryitem={categorymock[index]} 
        goodsitem={goodsmock[index]}/>
      ))}
      <CategoryItemList />
    </div>
  );
}

export default App;
