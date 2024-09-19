// src/views/Authentification.tsx
import React, { useState, useRef, ChangeEvent } from 'react';
import './style.css';
import Inputbox from 'components/inputbox/inputbox';
import { SignInRequestDto, SignUpRequestDto } from 'apis/request/auth';
import { ResponseDto } from 'apis/response';
import { SignInResponseDto, SignUpResponseDto } from 'apis/response/auth';
import { MAIN_PATH } from 'constant';
import { useNavigate } from 'react-router-dom';
import { signInRequest, signUpRequest } from 'apis/controller/Auth';
import useAuthStore from 'stores/useAuthStore';

export default function Authentication() {
  // State: 로그인/회원가입 여부
  const [view, setView] = useState<'sign-in' | 'sign-up'>('sign-in');
  // Zustand 스토어
  const { setUser, setAccessToken, resetAuth } = useAuthStore();
  // function: navigate 함수
  const navigate = useNavigate();

  // Component: Sign In Card 컴포넌트
  const SignInCard = () => {
    // Refs
    const emailRef = useRef<HTMLInputElement | null>(null);
    const passwordRef = useRef<HTMLInputElement | null>(null);

    // State
    const [email, setEmail] = useState<string>('');
    const [password, setPassword] = useState<string>('');
    const [passwordType, setPasswordType] = useState<'password' | 'text'>('password');
    const [passwordIcon, setPasswordIcon] = useState<'eye-light-off-icon' | 'eye-light-on-icon'>('eye-light-off-icon');
    const [error, setError] = useState<boolean>(false);
    const [errorMessage, setErrorMessage] = useState<string>('');

    // Function: sign in response 처리 함수
    const signInResponse = (response: SignInResponseDto | ResponseDto | null) => {
      if (!response) {
        alert('네트워크 상태를 확인해주세요.');
        return;
      }

      // ResponseDto 타입인지 확인 (에러 응답)
      if ('code' in response) {
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
        return;
      }

      // SignInResponseDto 타입인 경우 (성공 응답)
      if ('token' in response && 'expirationTime' in response) {
        setAccessToken(response.token, response.expirationTime);
        // 사용자 정보를 서버에서 가져오는 로직이 필요하다면 추가
        // 예: getSignInUserRequest(response.token).then(setUser)
        navigate(MAIN_PATH());
      } else {
        alert('로그인에 실패했습니다.');
      }
    };

    // Event Handlers
    const onPasswordToggle = () => {
      if (passwordType === 'text') {
        setPasswordType('password');
        setPasswordIcon('eye-light-off-icon');
      } else {
        setPasswordType('text');
        setPasswordIcon('eye-light-on-icon');
      }
    };

    const onEmailKeyDown = (event: React.KeyboardEvent<HTMLInputElement>) => {
      if (event.key === 'Enter') {
        passwordRef.current?.focus();
      }
    };

    const onPasswordKeyDown = (event: React.KeyboardEvent<HTMLInputElement>) => {
      if (event.key === 'Enter') {
        onSignIn();
      }
    };

    const onEmailChange = (event: ChangeEvent<HTMLInputElement>) => {
      setError(false);
      setErrorMessage('');
      setEmail(event.target.value);
    };

    const onPasswordChange = (event: ChangeEvent<HTMLInputElement>) => {
      setError(false);
      setErrorMessage('');
      setPassword(event.target.value);
    };

    const onSignIn = async () => {
      // 유효성 검사
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

    // Render: Sign In Card
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
              onChange={onEmailChange}
              onKeyDown={onEmailKeyDown}
              message={errorMessage}
            />
            <Inputbox
              ref={passwordRef}
              label='비밀번호'
              type={passwordType}
              placeholder='비밀번호를 입력해주세요'
              error={error}
              value={password}
              onChange={onPasswordChange}
              icon={passwordIcon}
              onButtonClick={onPasswordToggle}
              onKeyDown={onPasswordKeyDown}
              message={errorMessage}
            />
          </div>
          <div className="auth-card-bottom">
            {error && (
              <div className="auth-sign-in-error-box">
                <div className='auth-sign-in-error-message'>{errorMessage}</div>
              </div>
            )}
            <button
              className='black-large-full-button'
              onClick={onSignIn}
              disabled={!email || !password}
            >
              로그인
            </button>
            <div
              className='auth-gotosignup-box'
              onClick={() => setView('sign-up')}
              style={{ cursor: 'pointer', color: 'blue', textDecoration: 'underline' }}
            >
              혹시 처음 사이트에 오셨나요? <span>회원가입</span>
            </div>
          </div>
        </div>
      </div>
    );
  };

  // Component: Sign Up Card 컴포넌트
  const SignUpCard = () => {
    // Refs
    const emailRef = useRef<HTMLInputElement | null>(null);
    const usernameRef = useRef<HTMLInputElement | null>(null);
    const passwordRef = useRef<HTMLInputElement | null>(null);
    const passwordCheckRef = useRef<HTMLInputElement | null>(null);

    // State
    const [email, setEmail] = useState<string>('');
    const [username, setUsername] = useState<string>('');
    const [password, setPassword] = useState<string>('');
    const [passwordCheck, setPasswordCheck] = useState<string>('');
    const [passwordType, setPasswordType] = useState<'password' | 'text'>('password');
    const [passwordIcon, setPasswordIcon] = useState<'eye-light-off-icon' | 'eye-light-on-icon'>('eye-light-off-icon');

    const [emailError, setEmailError] = useState<boolean>(false);
    const [usernameError, setUsernameError] = useState<boolean>(false);
    const [passwordError, setPasswordError] = useState<boolean>(false);
    const [passwordCheckError, setPasswordCheckError] = useState<boolean>(false);

    const [emailErrorMessage, setEmailErrorMessage] = useState<string>('');
    const [usernameErrorMessage, setUsernameErrorMessage] = useState<string>('');
    const [passwordErrorMessage, setPasswordErrorMessage] = useState<string>('');
    const [passwordCheckErrorMessage, setPasswordCheckErrorMessage] = useState<string>('');

    // Function: sign up response 처리 함수
    const signUpResponse = (response: SignUpResponseDto | ResponseDto | null) => {
      if (!response) {
        alert('서버 에러');
        return;
      }

      if ('code' in response) {
        const { code } = response;
        switch (code) {
          case 'DBE':
            alert('데이터베이스 오류입니다.');
            break;
          case 'DE':
            setEmailError(true);
            setEmailErrorMessage('이미 존재하는 이메일입니다.');
            break;
          case 'DN':
            setUsernameError(true);
            setUsernameErrorMessage('이미 존재하는 닉네임입니다.');
            break;
          case 'VF':
            alert('모든 값을 입력하세요.');
            break;
          case 'SU':
            alert('회원가입 성공');
            setView('sign-in');
            break;
          default:
            alert('알 수 없는 오류가 발생했습니다.');
            break;

        }
        if ('token' in response && 'expirationTime' in response) {
          setAccessToken(response.token, response.expirationTime);
          // 사용자 정보를 서버에서 가져오는 로직이 필요하다면 추가
          // 예: getSignInUserRequest(response.token).then(setUser)
          navigate(MAIN_PATH());
          
        } else {
          alert('로그인에 실패했습니다.');
        }
        return;
      }

      // 추가적인 응답 처리 로직 (필요 시)
    };

    // Function: 패스워드 아이콘 버튼 클릭 핸들러
    const onPasswordToggle = () => {
      if (passwordType === 'text') {
        setPasswordType('password');
        setPasswordIcon('eye-light-off-icon');
      } else {
        setPasswordType('text');
        setPasswordIcon('eye-light-on-icon');
      }
    };

    // Event Handlers
    const onEmailKeyDown = (event: React.KeyboardEvent<HTMLInputElement>) => {
      if (event.key === 'Enter') {
        usernameRef.current?.focus();
      }
    };

    const onUsernameKeyDown = (event: React.KeyboardEvent<HTMLInputElement>) => {
      if (event.key === 'Enter') {
        passwordRef.current?.focus();
      }
    };

    const onPasswordKeyDown = (event: React.KeyboardEvent<HTMLInputElement>) => {
      if (event.key === 'Enter') {
        passwordCheckRef.current?.focus();
      }
    };

    const onPasswordCheckKeyDown = (event: React.KeyboardEvent<HTMLInputElement>) => {
      if (event.key === 'Enter') {
        onSignUp();
      }
    };

    const onEmailChange = (event: ChangeEvent<HTMLInputElement>) => {
      setEmailError(false);
      setEmailErrorMessage('');
      setEmail(event.target.value);
    };

    const onUsernameChange = (event: ChangeEvent<HTMLInputElement>) => {
      setUsernameError(false);
      setUsernameErrorMessage('');
      setUsername(event.target.value);
    };

    const onPasswordChange = (event: ChangeEvent<HTMLInputElement>) => {
      setPasswordError(false);
      setPasswordErrorMessage('');
      setPassword(event.target.value);
    };

    const onPasswordCheckChange = (event: ChangeEvent<HTMLInputElement>) => {
      setPasswordCheckError(false);
      setPasswordCheckErrorMessage('');
      setPasswordCheck(event.target.value);
    };

    const onSignUp = async () => {
      // 모든 에러 상태 초기화
      setEmailError(false);
      setUsernameError(false);
      setPasswordError(false);
      setPasswordCheckError(false);

      let isValid = true;

      // 이메일 유효성 검사
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      if (!emailRegex.test(email)) {
        setEmailError(true);
        setEmailErrorMessage('유효한 이메일 주소를 입력해주세요.');
        isValid = false;
      }

      // 유저네임 유효성 검사
      if (!username.trim()) {
        setUsernameError(true);
        setUsernameErrorMessage('닉네임을 입력해주세요.');
        isValid = false;
      }

      // 비밀번호 길이 검사
      if (password.length < 8) {
        setPasswordError(true);
        setPasswordErrorMessage('비밀번호는 최소 8자리 이상이어야 합니다.');
        isValid = false;
      }

      // 비밀번호 확인 검사
      if (password !== passwordCheck) {
        setPasswordCheckError(true);
        setPasswordCheckErrorMessage('비밀번호와 비밀번호 확인이 일치하지 않습니다.');
        isValid = false;
      }

      if (!isValid) return;

      const requestBody: SignUpRequestDto = { email, password, username };

      try {
        const response = await signUpRequest(requestBody);
        signUpResponse(response);
      } catch (error) {
        console.error('회원가입 중 오류 발생:', error);
        alert('회원가입 중 오류가 발생했습니다.');
      }
    };

    // Render: Sign Up Card
    return (
      <div className='auth-card'>
        <div className='auth-card-box'>
          <div className="auth-card-top">
            <div className="auth-title-box">
              <div className='auth-title-text'>회원가입</div>
            </div>
            <Inputbox
              ref={emailRef}
              label='이메일'
              type='text'
              placeholder='이메일 주소를 입력해주세요'
              error={emailError}
              value={email}
              onChange={onEmailChange}
              onKeyDown={onEmailKeyDown}
              message={emailErrorMessage}
            />
            <Inputbox
              ref={usernameRef}
              label='닉네임'
              type='text'
              placeholder='닉네임을 입력해주세요'
              error={usernameError}
              value={username}
              onChange={onUsernameChange}
              onKeyDown={onUsernameKeyDown}
              message={usernameErrorMessage}
            />
            <Inputbox
              ref={passwordRef}
              label='비밀번호'
              type={passwordType}
              placeholder='비밀번호를 입력해주세요'
              error={passwordError}
              value={password}
              onChange={onPasswordChange}
              icon={passwordIcon}
              onButtonClick={onPasswordToggle}
              onKeyDown={onPasswordKeyDown}
              message={passwordErrorMessage}
            />
            <Inputbox
              ref={passwordCheckRef}
              label='비밀번호 확인'
              type={passwordType}
              placeholder='비밀번호를 재입력해주세요'
              error={passwordCheckError}
              value={passwordCheck}
              onChange={onPasswordCheckChange}
              icon={passwordIcon}
              onButtonClick={onPasswordToggle}
              onKeyDown={onPasswordCheckKeyDown}
              message={passwordCheckErrorMessage}
            />
          </div>
          <div className="auth-card-bottom">
            <button
              className='black-large-full-button'
              onClick={onSignUp}
              disabled={!email || !username || !password || !passwordCheck}
            >
              회원가입
            </button>
          </div>
        </div>
      </div>
    );
  };

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
              <div className='auth-jumbotron-text'>오시</div>
              <div className='auth-jumbotron-text'>오시인</div>
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