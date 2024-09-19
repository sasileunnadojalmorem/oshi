import React, { useState, useRef, ChangeEvent } from 'react';
import Inputbox from 'components/inputbox/inputbox';
import { SignUpRequestDto } from 'apis/request/auth';
import { SignUpResponseDto } from 'apis/response/auth';
import { ResponseDto } from 'apis/response';
import { useNavigate } from 'react-router-dom';
import { signUpRequest } from 'apis/controller/Auth';
import useAuthStore from 'stores/useAuthStore';
import { MAIN_PATH } from 'constant';

export default function SignUpCard({ setView }: { setView: (view: 'sign-in' | 'sign-up') => void }) {
  const { setAccessToken } = useAuthStore();
  const navigate = useNavigate();

  const emailRef = useRef<HTMLInputElement | null>(null);
  const usernameRef = useRef<HTMLInputElement | null>(null);
  const passwordRef = useRef<HTMLInputElement | null>(null);
  const passwordCheckRef = useRef<HTMLInputElement | null>(null);

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

  const signUpResponse = (response: SignUpResponseDto | ResponseDto | null) => {
    if (!response) {
      alert('서버 에러');
      return;
    }

    if ('code' in response) {
      handleErrorResponse(response);
      return;
    }

    if ('token' in response && 'expirationTime' in response) {
      setAccessToken(response.token, response.expirationTime);
      navigate(MAIN_PATH());
    }
  };

  const handleErrorResponse = (response: ResponseDto) => {
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
  };

  const onPasswordToggle = () => {
    setPasswordType(prev => (prev === 'text' ? 'password' : 'text'));
    setPasswordIcon(prev => (prev === 'eye-light-off-icon' ? 'eye-light-on-icon' : 'eye-light-off-icon'));
  };

  const onSignUp = async () => {
    setEmailError(false);
    setUsernameError(false);
    setPasswordError(false);
    setPasswordCheckError(false);

    let isValid = true;

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
      setEmailError(true);
      setEmailErrorMessage('유효한 이메일 주소를 입력해주세요.');
      isValid = false;
    }

    if (!username.trim()) {
      setUsernameError(true);
      setUsernameErrorMessage('닉네임을 입력해주세요.');
      isValid = false;
    }

    if (password.length < 8) {
      setPasswordError(true);
      setPasswordErrorMessage('비밀번호는 최소 8자리 이상이어야 합니다.');
      isValid = false;
    }

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
            onChange={(e: ChangeEvent<HTMLInputElement>) => {
              setEmailError(false);
              setEmail(e.target.value);
            }}
            message={emailErrorMessage}
          />
          <Inputbox
            ref={usernameRef}
            label='닉네임'
            type='text'
            placeholder='닉네임을 입력해주세요'
            error={usernameError}
            value={username}
            onChange={(e: ChangeEvent<HTMLInputElement>) => {
              setUsernameError(false);
              setUsername(e.target.value);
            }}
            message={usernameErrorMessage}
          />
          <Inputbox
            ref={passwordRef}
            label='비밀번호'
            type={passwordType}
            placeholder='비밀번호를 입력해주세요'
            error={passwordError}
            value={password}
            onChange={(e: ChangeEvent<HTMLInputElement>) => {
              setPasswordError(false);
              setPassword(e.target.value);
            }}
            icon={passwordIcon}
            onButtonClick={onPasswordToggle}
            message={passwordErrorMessage}
          />
          <Inputbox
            ref={passwordCheckRef}
            label='비밀번호 확인'
            type={passwordType}
            placeholder='비밀번호를 재입력해주세요'
            error={passwordCheckError}
            value={passwordCheck}
            onChange={(e: ChangeEvent<HTMLInputElement>) => {
              setPasswordCheckError(false);
              setPasswordCheck(e.target.value);
            }}
            icon={passwordIcon}
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
}