import React from 'react';

import './App.css';
import OshiListItem from 'components/OshiListItem';
import { oshimock,categorymock,goodsmock } from 'mocks';
function App() {
  return (
    <>
    {
      oshimock.map((oshiitem ,index )=>
      < OshiListItem oshiitem={oshiitem} categoryitem={categorymock[index]}
      goodsitem={goodsmock[index]}/>)
    }
    </>
  );
};

export default App;
