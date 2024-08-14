import React, { useState, ChangeEvent } from 'react';
import { Goodstypeitem } from 'types/interface';

interface DropdownProps {
  goodsTypes: Goodstypeitem;  // Goodstypeitem 객체로 정의
}

export default function Dropdown({ goodsTypes }: DropdownProps) {
  const [dropdownVisible, setDropdownVisible] = useState<boolean>(false);
  const [searchTerm, setSearchTerm] = useState<string>('');

  const toggleDropdown = () => {
    setDropdownVisible(!dropdownVisible);
  };

  const handleSearchChange = (e: ChangeEvent<HTMLInputElement>) => {
    setSearchTerm(e.target.value);
  };

  const filteredGoodsTypes = goodsTypes.goodstype.filter(type =>
    type.toLowerCase().includes(searchTerm.toLowerCase())  // 배열 내 문자열 필터링
  );

  return (
    <div className='oshi-list-item-goods-type'>
      <div className='oshi-list-item-goods-type-text'>{'굿즈 종류:'}</div>
      <div 
        className='oshi-list-item-goods-type-select' 
        onClick={toggleDropdown}
      >
        {'모두 보기'}
      </div>
      {dropdownVisible && (
        <div className='dropdown-menu'>
          <input
            type='text'
            className='dropdown-search'
            placeholder='검색...'
            value={searchTerm}
            onChange={handleSearchChange}
          />
          <ul>
            {filteredGoodsTypes.length > 0 ? (
              filteredGoodsTypes.map((type, index) => (
                <li key={index}>{type}</li>  // 배열 요소 출력
              ))
            ) : (
              <li>검색 결과가 없습니다.</li>
            )}
          </ul>
        </div>
      )}
    </div>
  );
}
