import React, { ChangeEvent, forwardRef,  KeyboardEvent } from 'react';
import './style.css';

interface Props { 
  label: string;
  type: 'text' | 'password';
  error: boolean;
  placeholder: string;
  value: string;
  onChange: (event: ChangeEvent<HTMLInputElement>) => void;
  icon?: 'eye-light-off-icon' | 'eye-light-on-icon';  
  onButtonClick?: () => void; 

  message?: string;  // 에러 메시지 
  onKeyDown?: (event: KeyboardEvent<HTMLInputElement>) => void; // onKeyDown 이벤트 핸들러 
}

const Inputbox = forwardRef<HTMLInputElement, Props>((props, ref) => {

  const { label, type, error, placeholder, value, onChange, icon, onButtonClick, message, onKeyDown } = props;



  return (
    <div className='inputbox'>
      <div className="inputbox-label">{label}</div>
      <div className={error ? 'inputbox-container-error' : 'inputbox-container'}>
        <input 
          type={type} 
          className='input' 
          placeholder={placeholder}     
          value={value} 
          onChange={onChange}  // onChange 이벤트 핸들러 추가
          onKeyDown={onKeyDown}  // onKeyDown 이벤트 핸들러 추가
          ref={ref}  // forwardRef로 받은 ref를 input에 연결
        />
        {icon && (
          <div className='icon-button' onClick={onButtonClick}>
            <div className={`icon ${icon}`}></div>
          </div>
        )}
      </div>
      <div className="inputbox-message">{message || ''}</div>
    </div>
  );
});

export default Inputbox;
