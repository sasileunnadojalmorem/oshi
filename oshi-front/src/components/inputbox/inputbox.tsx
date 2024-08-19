import React, { forwardRef} from 'react';
import './style.css';

interface Props  { 
  // 추가적인 prop을 필요로 한다면 여기에 정의
}

const Inputbox = forwardRef<HTMLInputElement, Props>((props, ref) => {
  return (
    <div className='inputbox'>
      <div className="inputbox-label">{'비밀번호*'}</div>
      <div className="inputbox-container">
        <input 
          className='input' 
          ref={ref} 
          {...props} // props를 전달하여 사용자가 넘긴 속성들도 포함되도록 처리
        />
        <div className='input-icon'>
          <div className=''></div>
        </div>
      </div>
      <div className="inputbox-message">{'비밀번호는 8자리 이상 입력해주세요'}</div>
    </div>
  );
});

export default Inputbox;
