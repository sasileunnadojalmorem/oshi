import React, { useState, useRef, ChangeEvent } from 'react';
import Inputbox from 'components/inputbox/inputbox';
import { SignInRequestDto } from 'apis/request/auth';
import { SignInResponseDto } from 'apis/response/auth';
import { ResponseDto } from 'apis/response';
import { useNavigate } from 'react-router-dom';
import { signInRequest } from 'apis/controller/Auth';
import useAuthStore from 'stores/useAuthStore';
import { MAIN_PATH } from 'constant';

export default function SignInCard({ setView }: { setView: (view: 'sign-in' | 'sign-up') => void }) {
  const { setAccessToken } = useAuthStore();
  const navigate = useNavigate();

  const emailRef = useRef<HTMLInputElement | null>(null);
  const passwordRef = useRef<HTMLInputElement | null>(null);

  const [email, setEmail] = useState<string>('');
  const [password, setPassword] = useState<string>('');
  const [passwordType, setPasswordType] = useState<'password' | 'text'>('password');
  const [passwordIcon, setPasswordIcon] = useState<'eye-light-off-icon' | 'eye-light-on-icon'>('eye-light-off-icon');
  const [error, setError] = useState<boolean>(false);
  const [errorMessage, setErrorMessage] = useState<string>('');

  const signInResponse = (response: SignInResponseDto | ResponseDto | null) => {
    if (!response) {
      alert('네트워크 상태를 확인해주세요.');
      return;
    }

    if ('code' in response) {
      handleErrorResponse(response);
      return;
    }

    if ('token' in response && 'expirationTime' in response) {
      setAccessToken(response.token, response.expirationTime);
      navigate(MAIN_PATH());
    } else {
      alert('로그인에 실패했습니다.');
    }
  };

  const handleErrorResponse = (response: ResponseDto) => {
    const { code } = response;
    switch (code) {
      case 'DBE':
        alert('데이터베이스 오류입니다.');
        break;
      case 'SF':
      case 'VF':
        setError(true);
        setErrorMessage('이메일 주소 또는 비밀번호를 잘못 입력했습니다. 내용을 다시 확인해주세요.');
        break;
      default:
        alert('알 수 없는 오류가 발생했습니다.');
        break;
    }
  };

  const onPasswordToggle = () => {
    setPasswordType(prev => (prev === 'text' ? 'password' : 'text'));
    setPasswordIcon(prev => (prev === 'eye-light-off-icon' ? 'eye-light-on-icon' : 'eye-light-off-icon'));
  };

  const onSignIn = async () => {
    if (!email || !password) {
      setError(true);
      setErrorMessage('모든 필드를 입력해주세요.');
      return;
    }

    const requestBody: SignInRequestDto = { email, password };
    try {
      const response = await signInRequest(requestBody);
      signInResponse(response);
    } catch (error) {
      console.error('로그인 중 오류 발생:', error);
      alert('로그인 중 오류가 발생했습니다.');
    }
  };

  return (
    <div className='auth-card'>
      <div className='auth-card-box'>
        <div className="auth-card-top">
          <div className="auth-title-box">
            <div className='auth-title-text'>로그인</div>
          </div>
          <Inputbox
            ref={emailRef}
            label='이메일'
            type='text'
            placeholder='이메일 주소를 입력해주세요'
            error={error}
            value={email}
            onChange={(e: ChangeEvent<HTMLInputElement>) => {
              setError(false);
              setEmail(e.target.value);
            }}
            onKeyDown={(e: React.KeyboardEvent<HTMLInputElement>) => e.key === 'Enter' && passwordRef.current?.focus()}
            message={errorMessage}
          />
          <Inputbox
            ref={passwordRef}
            label='비밀번호'
            type={passwordType}
            placeholder='비밀번호를 입력해주세요'
            error={error}
            value={password}
            onChange={(e: ChangeEvent<HTMLInputElement>) => {
              setError(false);
              setPassword(e.target.value);
            }}
            icon={passwordIcon}
            onButtonClick={onPasswordToggle}
            onKeyDown={(e: React.KeyboardEvent<HTMLInputElement>) => e.key === 'Enter' && onSignIn()}
            message={errorMessage}
          />
        </div>
        <div className="auth-card-bottom">
          {error && <div className="auth-sign-in-error-box"><div className='auth-sign-in-error-message'>{errorMessage}</div></div>}
          <button className='black-large-full-button' onClick={onSignIn} disabled={!email || !password}>로그인</button>
          <div className='auth-gotosignup-box' onClick={() => setView('sign-up')} style={{ cursor: 'pointer', color: 'blue', textDecoration: 'underline' }}>
            혹시 처음 사이트에 오셨나요? <span>회원가입</span>
          </div>
        </div>
      </div>
    </div>
  );
}