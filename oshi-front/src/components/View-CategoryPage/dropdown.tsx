import React, { useState, ChangeEvent } from 'react';
import { Goodstypeitem } from 'types/interface';
import './style.css';
import { on } from 'events';

interface DropdownProps {
  Types: Goodstypeitem;
  selectedType: string; // 부모 컴포넌트에서 전달된 선택된 타입
  onChange: (selectedType: string) => void; // 타입 변경 콜백 함수
}

export default function Dropdown({ Types, selectedType, onChange }: DropdownProps) {
  const [dropdownVisible, setDropdownVisible] = useState<boolean>(false);
  const [searchTerm, setSearchTerm] = useState<string>('');
  
  // event handler: 드롭다운 표시/숨기기 토글
  const toggleDropdown = () => {
    setDropdownVisible(!dropdownVisible);
  };

  // event handler: 검색어 변경 처리
  const handleSearchChange = (e: ChangeEvent<HTMLInputElement>) => {
    setSearchTerm(e.target.value);
  };

  // event handler: 검색어에 따른 타입 필터링
  const filteredTypes = Types.goodstype.filter(type =>
    type.toLowerCase().includes(searchTerm.toLowerCase())
  );

  // event handler: 타입 선택 처리
  const onTypeButtonClickHandler = (type: string) => {
    setDropdownVisible(false);
    onChange(type); // 부모 컴포넌트로 선택된 타입 전달
  };

  return (
    <div className='oshi-list-item-goods-type'>
      <div
        className='oshi-list-item-goods-type-select'
        onClick={toggleDropdown}
      >
        {selectedType} {/* props로 전달된 selectedType 사용 */}
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
            {filteredTypes.length > 0 ? (
              filteredTypes.map((type, index) => (
                <li
                  key={index}
                  onClick={() => onTypeButtonClickHandler(type)} // 타입 선택 처리
                  className='dropdown-item'
                >
                  {type}
                </li>
              ))
            ) : (
              <li className='dropdown-no-results'>검색 결과가 없습니다.</li>
            )}
          </ul>
        </div>
      )}
    </div>
  );
}
