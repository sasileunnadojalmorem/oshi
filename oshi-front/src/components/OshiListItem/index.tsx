import React, { useState } from 'react';
import './style.css';

export default function OshiListItem() {
  const [dropdownVisible, setDropdownVisible] = useState(false);

  // 드롭다운 표시 토글 함수
  const toggleDropdown = () => {
    setDropdownVisible(!dropdownVisible);
  };

  return (
    <div className='oshi-list-item'>
      <div className='oshi-list-main'>
        <div className='oshi-list-item-top'> 
          <div className='oshi-list-item-logo'>
            <div className='oshi-list-item-imagebox'>
              <div className='oshi-list-item-image' style={{ backgroundImage: `url(/아이유.jpg)` }}></div>
            </div>
            <div className='oshi-list-item-select'></div>
          </div>
          <div className='oshi-list-item-who'>
            <div className='oshi-list-item-oshi'>{'아이유'}</div>
            <div className='ohsi-list-item-category'>{'공식: 2024 시즌 그리팅'}</div>
            <div className='oshi-list-item-goods'>{'포토 카드'}</div>
          </div>
        </div>
        <div className='oshi-list-item-middle'> 
          <div className='ohsi-list-item-search'>
            <span>카테고리별</span>
            <span> | </span>
            <span>굿즈별</span>
          </div>
        </div>
        <div className='oshi-list-item-bottom'> 
          <div className='oshi-list-item-goods-type'>
            <div className='oshi-list-item-goods-type-text'>{'굿즈 종류'}</div>
            <div 
              className='oshi-list-item-goods-type-select' 
              onClick={toggleDropdown}
            >
              {'모두 보기'}
            </div>
            {dropdownVisible && (
              <ul className='dropdown-menu'>
                <li>굿즈 종류 1</li>
                <li>굿즈 종류 2</li>
                <li>굿즈 종류 3</li>
              </ul>
            )}
          </div>
          <div className='oshi-list-item-goods-sort'>
            {'최신순 | 발매순 | 인기순'}
          </div>
        </div>
      </div>
    </div>
  );
}
