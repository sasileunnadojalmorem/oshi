import React, { ChangeEvent, forwardRef, Dispatch, SetStateAction } from 'react';
import './style.css';

interface Props { 
  label: string;
  type: 'text' | 'password';
  error: boolean;
  placeholder: string;
  value: string;
  setvalue: Dispatch<SetStateAction<string>>;
}

const Inputbox = forwardRef<HTMLInputElement, Props>((props, ref) => {

  const { label, type, error, placeholder, value, setvalue } = props;

  // 이벤트 핸들러: 인풋 값 변경 이벤트 처리 함수
  const onChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
    setvalue(event.target.value);  // 입력된 값으로 상태 업데이트
  }

  return (
    <div className='inputbox'>
      <div className="inputbox-label">{label}</div>
      <div className={error ? 'inputbox-container-error' : 'inputbox-container'}>
        <input 
          type={type} 
          className='input' 
          placeholder={placeholder} 
          value={value} 
          onChange={onChangeHandler}  // onChange 이벤트 핸들러 추가
          ref={ref}  // forwardRef로 받은 ref를 input에 연결
        />
        <div className='icon-button'>
          <div className='icon eye-light-off-icon'></div>
        </div>
      </div>
      <div className="inputbox-message">{'비밀번호는 8자리 이상 입력해주세요'}</div>
    </div>
  );
});

export default Inputbox;
