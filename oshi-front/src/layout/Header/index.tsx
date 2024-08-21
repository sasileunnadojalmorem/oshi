import { useState } from 'react';
import './style.css';
import { useNavigate } from 'react-router-dom';
import { MAIN_PATH } from 'constant';
import View_Myoshi from 'components/OshiListView';
import Dropdown from 'components/View-CategoryPage/dropdown';
import { Goodstypeitem } from 'types/interface';

export default function Header() {
  const navigate = useNavigate();
  
  // * 네비게이션 핸들러 함수입니다.
  const onoshilogclickhandler = () => {
    navigate(MAIN_PATH());
  };

  // # Types 예시 데이터 (실제 데이터로 교체해야 함)
  const Types: Goodstypeitem = {
    goodstype: ['Type1', 'Type2', 'Type3'], // # 예시 데이터
  };

  // state 선택된 타입 상태 관리
  const [selectedType, setSelectedType] = useState<string>(Types.goodstype[0]);
  
  // event handler 콜백 타입 변경 처리
  const handleTypeChange = (newType: string) => {
    setSelectedType(newType); // state 선택된 타입 업데이트
  };

  //    component 로그인 컴포넌트 

  const LoginMypageButton = () =>{

    // render 로그인 컴포넌트 랜더
    return <div className='log-in-button-box'><div className='log-in-button-text'>{'로그인'}</div></div>

  }

  

  //    component 검색 컴포넌트 
  const SearchButton = () => {
    // state 검색 버튼 상태 관리
    const [status, setStatus] = useState<Boolean>(false);
    // state 검색어 상태 관리
    const [searchTerm, setSearchTerm] = useState<string>('');

    // event handler 검색 버튼 클릭 핸들러
    const onSearchButtonClickHandler = () => {
      setStatus(!status);
    };

    // event handler 검색어 변경 핸들러
    const onSearchTermChangeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
      setSearchTerm(e.target.value);
    };

    // event handler 검색 제출 핸들러
    const onSearchSubmitHandler = (e: React.KeyboardEvent<HTMLInputElement>) => {
      if (e.key === 'Enter') {
        console.log(`Searching for: ${searchTerm} in ${selectedType}`);
        // TODO: 검색 실행 로직 추가 가능
      }
    };

    return (
      <div className='header-search-input-box'>
        <Dropdown
          Types={Types}
          selectedType={selectedType} // state 부모 컴포넌트의 상태 전달
          onChange={handleTypeChange} // event handler 타입 변경 시 호출
        />
        <input
          className='header-search-input'
          placeholder='최애를 검색하세요'
          value={searchTerm}
          onChange={onSearchTermChangeHandler} // event handler 검색어 변경 핸들러
          onKeyDown={onSearchSubmitHandler} // event handler 엔터키로 검색 기능 실행
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
          <div className='header-user-box'><LoginMypageButton/></div>
        </div>
      </div>
    </div>
  );
}
