import React, { useState, useRef } from 'react';
import './style.css';
import Inputbox from 'components/inputbox/inputbox';

export default function Authentication() {

  // State: 로그인/회원가입 여부
  const [view, setView] = useState<'sign-in' | 'sign-up'>('sign-in');

  // Component: Sign In Card 컴포넌트
  const SignInCard = () => {

    // State: 이메일 요소 참조 상태
    const emailRef = useRef<HTMLInputElement | null>(null);
    // State: 비밀번호 요소 참조 상태
    const passwordRef = useRef<HTMLInputElement | null>(null);
    // State: 이메일 상태
    const [email, setEmail] = useState<string>('');
    // State: 비밀번호 상태
    const [password, setPassword] = useState<string>('');
    // State: 비밀번호 타입 상태
    const [passwordType, setPasswordType] = useState<'password' | 'text'>('password');
    // State: 비밀번호 아이콘 상태
    const [passwordIcon, setPasswordIcon] = useState<'eye-light-off-icon' | 'eye-light-on-icon'>('eye-light-off-icon');
    // State: 에러 상태
    const [error, setError] = useState<boolean>(false);

    // Function: 패스워드 아이콘 버튼 클릭 핸들러
    const onPasswordButtonClickHandler = () => {
      if (passwordType === 'text') {
        setPasswordType('password');  // 수정된 부분
        setPasswordIcon('eye-light-off-icon');
      } else {
        setPasswordType('text');
        setPasswordIcon('eye-light-on-icon');
      }
    }

    // Function: 이메일 인풋 키 다운 이벤트 처리
    const onEmailKeyDownHandler = (event: React.KeyboardEvent<HTMLInputElement>) => {
      if (event.key !== 'Enter') return;
      if (!passwordRef.current) return;
      passwordRef.current.focus();
    }

    // Function: 패스워드 인풋 키 다운 이벤트 처리
    const onPasswordKeyDownHandler = (event: React.KeyboardEvent<HTMLInputElement>) => {
      if (event.key !== 'Enter') return;
      onSignInButtonClickHandler();
    }

    // Function: 로그인 버튼 클릭 이벤트 처리
    const onSignInButtonClickHandler = () => {
      // 로그인 처리 로직
      console.log('로그인');
      if (email === '' || password === '') {
        setError(true);
      } else {
        setError(false);
        // 로그인 로직 추가
      }
    }

    // Render: Sign In Card
    return (
      <div className='auth-card'>
        <div className='auth-card-box'>
          <div className="auth-card-top">
            <div className="auth-title-box">
              <div className='auth-title-text'>{'로그인'}</div>
            </div>
            <Inputbox
              ref={emailRef}
              label='이메일'
              type='text'
              placeholder='이메일 주소를 입력해주세요'
              error={error}
              value={email}
              setvalue={setEmail}
              onKeyDown={onEmailKeyDownHandler}
            />
            <Inputbox
              ref={passwordRef}
              label='비밀번호'
              type={passwordType}
              placeholder='비밀번호를 입력해주세요'
              error={error}
              value={password}
              setvalue={setPassword}
              icon={passwordIcon}
              onButtonClick={onPasswordButtonClickHandler}
              onKeyDown={onPasswordKeyDownHandler}
            />
          </div>
          <div className="auth-card-bottom">
            {error && (
              <div className="auth-sign-in-error-box">
                <div className='auth-sign-in-error-message'>
                  {'이메일 주소 또는 비밀번호를 잘못 입력했습니다. 내용을 다시 확인해주세요'}
                </div>
              </div>
            )}
            <div className='black-large-full-button' onClick={onSignInButtonClickHandler}>{'로그인'}</div>
          </div>
        </div>
      </div>
    );
  }

  // Component: Sign Up Card 컴포넌트
  const SignUpCard = () => {
    return (
      <div className='auth-card'></div>
    );
  }

  // Render: 인증 화면 렌더
  return (
    <div className='auth-wrapper'>
      <div className='auth-container'>
        <div className='auth-jumbotron-box'>
          <div className='auth-jumbotron-content'>
            <div className='auth-jumbotron-logo-box'>
              <div className='auth-jumbotron-logo'></div>
            </div>
            <div className='auth-jumbotron-text-box'>
              <div className='auth-jumbotron-text'>{'오시'}</div>
              <div className='auth-jumbotron-text'>{'오시인'}</div>
            </div>
          </div>
          <div className='card'>
            {view === 'sign-in' && <SignInCard />}
            {view === 'sign-up' && <SignUpCard />}
          </div>
        </div>
      </div>
    </div>
  );
}
