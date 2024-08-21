import { useState } from 'react';
import './style.css';
import { useNavigate } from 'react-router-dom';
import { MAIN_PATH } from 'constant';
import View_Myoshi from 'components/OshiListView';
import Dropdown from 'components/View-CategoryPage/dropdown';
import { Goodstypeitem } from 'types/interface';

export default function Header() {
  const navigate = useNavigate();
  const onoshilogclickhandler = () => {
    navigate(MAIN_PATH());
  };

  // Types 예시 데이터 (실제 데이터로 교체해야 함)
  const Types: Goodstypeitem = {
    goodstype: ['Type1', 'Type2', 'Type3'], // 예시 데이터
  };

  // 선택된 타입 상태 관리
  const [selectedType, setSelectedType] = useState<string>(Types.goodstype[0]);
  
  const handleTypeChange = (newType: string) => {
    
    setSelectedType(newType); // 선택된 타입 업데이트
  };

  const SearchButton = () => {
    const [status, setStatus] = useState<Boolean>(false);
    const [searchTerm, setSearchTerm] = useState<string>('');

    const onSearchButtonClickHandler = () => {
      setStatus(!status);
    };

    const onSearchTermChangeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
      setSearchTerm(e.target.value);
    };

    const onSearchSubmitHandler = (e: React.KeyboardEvent<HTMLInputElement>) => {
      if (e.key === 'Enter') {
        console.log(`Searching for: ${searchTerm} in ${selectedType}`);
        // 검색 실행 로직 추가 가능
      }
    };

    

    return (
      <div className='header-search-input-box'>
        <Dropdown
          Types={Types}
          selectedType={selectedType} // 부모 컴포넌트의 상태 전달

          onChange={handleTypeChange} // 타입 변경 시 호출
        />
        <input
          className='header-search-input'
          placeholder='최애를 검색하세요'
          value={searchTerm}
          onChange={onSearchTermChangeHandler}
          onKeyDown={onSearchSubmitHandler} // 엔터키로 검색 기능 실행
        />
        <div className='icon-button' onClick={onSearchButtonClickHandler}>
          <div className='icon icon-search-light-icon'></div>
        </div>
      </div>
    );
  };

  return (
    <div className='header'>
      <div className='header-container'>
        <div className='header-top'>
          <div className='header-logo-box'>
            <div className='icon-box'>
              <div className='icon oshi-logo' onClick={onoshilogclickhandler}></div>
            </div>
            <div className='header-logo-text text'>{'굿즈 정보 공유 플랫폼'}</div>
          </div>
          <div className='Myoshi-view'>
            <View_Myoshi />
          </div>
        </div>
        <div className='header-middle'>
          <div className='header-search'>
            <SearchButton />
          </div>
        </div>
        <div className='header-bottom'>
          <div className='header-user-box'></div>
        </div>
      </div>
    </div>
  );
}
