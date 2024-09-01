import './style.css';
import React from 'react';
import { oshimock } from 'mocks';
import MiddleOshiBox from './middle/middleOshiBox';
import project_description from 'assets/project-header.png';
import MiddleAddBox from './middle/middleAddBox';
import OshiListView from 'components/OshiListView/oshilistview';
export default function View_MainPage() {
  return (
    <div className='mainpage'>
      <div className="header-image-box">
        <div className='header-image' style={{ backgroundImage: `url(${project_description})` }}></div>
      </div>
      <div className='middle'>
        <div className="oshi-main-middle-box">
              
          {/* middleAddBox 컴포넌트 추가 */}
        </div>
        <div className='bottom'></div>
      </div>
    </div>
  );
}
