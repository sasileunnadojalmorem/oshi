import React, { useState, useRef, ChangeEvent } from 'react';
import './style.css';
import Inputbox from 'components/inputbox/inputbox';
import { SignInRequestDto, SignUpRequestDto } from 'apis/request/auth';
import { signInRequset, signUpRequest } from 'apis';
import { ResponseDto } from 'apis/response';
import { SignInResponseDto, SignUpResponseDto } from 'apis/response/auth';
import { useCookies } from 'react-cookie';
import { MAIN_PATH } from 'constant';
import { useNavigate } from 'react-router-dom';

export default function Authentication() {
  // State: 로그인/회원가입 여부
  const [view, setView] = useState<'sign-in' | 'sign-up'>('sign-in');
  // State: 쿠키 상태
  const [cookie, setCookie] = useCookies();
  // function: navigate 함수
  const navigator = useNavigate();

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

    // function: sign in response 처리 함수
    const signInResponse = (response: SignInResponseDto | ResponseDto | null) => {
      if (!response) {
        alert('네트워크 상태를 확인해주세요');
        return;
      }

      const { code } = response;
      if (code === 'DBE') alert('데이터베이스 오류입니다');
      if (code === 'SF' || code === 'VF') {
        setError(true);
      }
      if (code !== 'SU') return;
      const { token, expirationTime } = response as SignInResponseDto;
      const now = new Date().getTime();
      const expires = new Date(now + expirationTime * 1000);

      // 쿠키 설정
      setCookie('accessToken', token, { expires, path: MAIN_PATH() });

      navigator(MAIN_PATH());
    };

    // event handler: 패스워드 아이콘 버튼 클릭 핸들러
    const onPasswordButtonClickHandler = () => {
      if (passwordType === 'text') {
        setPasswordType('password'); // 수정된 부분
        setPasswordIcon('eye-light-off-icon');
      } else {
        setPasswordType('text');
        setPasswordIcon('eye-light-on-icon');
      }
    };

    // event handler: 이메일 인풋 키 다운 이벤트 처리
    const onEmailKeyDownHandler = (event: React.KeyboardEvent<HTMLInputElement>) => {
      if (event.key !== 'Enter') return;
      if (!passwordRef.current) return;
      passwordRef.current.focus();
    };

    // event handler: 패스워드 인풋 키 다운 이벤트 처리
    const onPasswordKeyDownHandler = (event: React.KeyboardEvent<HTMLInputElement>) => {
      if (event.key !== 'Enter') return;
      onSignInButtonClickHandler();
    };

    // event handler: 이메일 변경 이벤트 처리
    const onEmailChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
      setError(false);
      const value = event.target.value;
      setEmail(value);
    };

    // event handler: 비밀번호 변경 이벤트 처리
    const onPasswordChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
      setError(false);
      const value = event.target.value;
      setPassword(value);
    };

    // event handler: 로그인 버튼 클릭 이벤트 처리
    const onSignInButtonClickHandler = () => {
      // 로그인 처리 로직
      const requestbody: SignInRequestDto = { email, password };
      signInRequset(requestbody).then(signInResponse);
    };

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
              onChange={onEmailChangeHandler}
              onKeyDown={onEmailKeyDownHandler}
            />
            <Inputbox
              ref={passwordRef}
              label='비밀번호'
              type={passwordType}
              placeholder='비밀번호를 입력해주세요'
              error={error}
              value={password}
              onChange={onPasswordChangeHandler}
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
            <div className='auth-gotosignup-box'>{'혹시 처음 사이트에 오셨나요?'}<span>회원가입</span></div>
          </div>
        </div>
      </div>
    );
  }

  // Component: Sign Up Card 컴포넌트
  const SignUpCard = () => {
    const emailRef = useRef<HTMLInputElement | null>(null);
    // State: 유저네임 요소 참조 상태
    const usernameRef = useRef<HTMLInputElement | null>(null);
    // State: 비밀번호 요소 참조 상태
    const passwordRef = useRef<HTMLInputElement | null>(null);
    // State: 비밀번호확인 요소 참조 상태
    const passwordcheckRef = useRef<HTMLInputElement | null>(null);

    // State: 이메일 상태
    const [email, setEmail] = useState<string>('');
    // State: 유저 이름 상태
    const [username, setUsername] = useState<string>('');
    // State: 비밀번호 상태
    const [password, setPassword] = useState<string>('');
    // State: 비밀번호 확인 상태
    const [passwordcheck, setPasswordcheck] = useState<string>('');
    // State: 비밀번호 타입 상태
    const [passwordType, setPasswordType] = useState<'password' | 'text'>('password');
    // State: 비밀번호 아이콘 상태
    const [passwordIcon, setPasswordIcon] = useState<'eye-light-off-icon' | 'eye-light-on-icon'>('eye-light-off-icon');
    // State: 이메일  에러 상태
    const [emailerror, setEmailError] = useState<boolean>(false);
    // State: 유저 네임 에러 상태
    const [usernameerror, setUsernameError] = useState<boolean>(false);
    // State: 비밀번호  에러 상태
    const [passworderror, setPasswordError] = useState<boolean>(false);
    // State: 비밀번호 확인 에러 상태
    const [passwordcheckerror, setpasswordcheckError] = useState<boolean>(false);
    // State: 이메일 오류 메시지 벨류 상태
    const [emailerrormessage, setEmailErrorMessage] = useState<string>('');
    // State: 닉네임 오류 메시지 벨류 상태
    const [usernameerrormessage, setUsernameErrorMessage] = useState<string>('');
    // State: 비밀번호 오류 메시지 벨류 상태
    const [passworderrormessage, setPasswordErrorMessage] = useState<string>('');
    // State: 비밀번호 확인 오류 메시지 벨류 상태
    const [passwordcheckerrormessage, setPasswordcheckErrorMessage] = useState<string>('');

    // Function: sign up response 처리 함수
    const signUpResponse = (response: SignUpResponseDto | ResponseDto | null) => {
      if (!response) {
        alert('서버 에러');
        return;
      }
      const { code } = response;
      if (code === 'DBE') alert('데이터베이스 오류입니다');
      if (code === 'DE') {
        setEmailError(true);
        setEmailErrorMessage('이미 존재하는 이메일입니다');
      }
      if (code === 'DN') {
        setUsernameError(true);
        setUsernameErrorMessage('이미 존재하는 닉네임입니다');
      }
      if (code === 'VF') {
        alert('모든 값을 입력하세요');
      }
      if (code === 'DBE') {
        alert('데이터베이스 오류입니다');
      }
      if (code !== 'SU') {
        return;}
      if (code === 'SU') {
        alert('회원가입 성공');
        setView('sign-in');
      }

      
      // 회원가입 성공 후 로직
    };

    // Function: 패스워드 아이콘 버튼 클릭 핸들러
    const onPasswordButtonClickHandler = () => {
      if (passwordType === 'text') {
        setPasswordType('password'); // 수정된 부분
        setPasswordIcon('eye-light-off-icon');
      } else {
        setPasswordType('text');
        setPasswordIcon('eye-light-on-icon');
      }
    };

    // Function: 이메일 인풋 키 다운 이벤트 처리
    const onEmailKeyDownHandler = (event: React.KeyboardEvent<HTMLInputElement>) => {
      if (event.key !== 'Enter') return;
      if (!usernameRef.current) return;
      usernameRef.current?.focus();
    };

    // Function: 닉네임 인풋 키 다운 이벤트 처리
    const onUsernameKeyDownHandler = (event: React.KeyboardEvent<HTMLInputElement>) => {
      if (event.key !== 'Enter') return;
      if (!passwordRef.current) return;
      passwordRef.current?.focus();
    };

    // Function: 패스워드 인풋 키 다운 이벤트 처리
    const onPasswordKeyDownHandler = (event: React.KeyboardEvent<HTMLInputElement>) => {
      if (event.key !== 'Enter') return;
      passwordcheckRef.current?.focus();
    };

    // Function: 비밀번호 확인 인풋 키 다운 이벤트 처리
    const onPasswordcheckKeyDownHandler = (event: React.KeyboardEvent<HTMLInputElement>) => {
      if (event.key !== 'Enter') return;
      onSignUpButtonClickHandler();
    };

    // event handler: 이메일 변경 이벤트 처리
    const onEmailChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
      setEmailError(false);
      setEmailErrorMessage('');
      const value = event.target.value;
      setEmail(value);
    };

    // event handler: 유저 이름 변경 이벤트 처리
    const onUsernameChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
      setUsernameError(false);
      setUsernameErrorMessage('');
      const value = event.target.value;
      setUsername(value);
    };

    // event handler: 비밀번호 변경 이벤트 처리
    const onPasswordChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
      setPasswordError(false);
      setPasswordErrorMessage('');
      const value = event.target.value;
      setPassword(value);
    };

    // event handler: 비밀번호 확인 변경 이벤트 처리
    const onPasswordcheckChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
      setpasswordcheckError(false);
      setPasswordcheckErrorMessage('');
      const value = event.target.value;
      setPasswordcheck(value);
    };

    // Function: 회원가입 버튼 클릭 이벤트 처리
    const onSignUpButtonClickHandler = () => {
      // 모든 에러 상태 초기화
      setEmailError(false);
      setUsernameError(false);
      setPasswordError(false);
      setpasswordcheckError(false);

      let isValid = true;

      // 이메일 유효성 검사
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      if (!emailRegex.test(email)) {
        setEmailError(true);
        setEmailErrorMessage('유효한 이메일 주소를 입력해주세요.');
        isValid = false;
      }

      // 비밀번호 길이 검사
      if (password.length < 8) {
        setPasswordError(true);
        setPasswordErrorMessage('비밀번호는 최소 8자리 이상이어야 합니다.');
        isValid = false;
      }

      // 비밀번호 확인 검사
      if (password !== passwordcheck) {
        setpasswordcheckError(true);
        setPasswordcheckErrorMessage('비밀번호와 비밀번호 확인이 일치하지 않습니다.');
        isValid = false;
      }

      // 모든 검사가 통과된 경우에만 회원가입 로직 실행
      if (!isValid) return;

      const requestbody: SignUpRequestDto = { email, password, username };
      signUpRequest(requestbody).then(signUpResponse);
      console.log('회원가입');
    };

    // render : Sign up card 컴포넌트 렌더
    return (
      <div className='auth-card'>
        <div className='auth-card-box'>
          <div className="auth-card-top">
            <div className="auth-title-box">
              <div className='auth-title-text'>{'회원가입'}</div>
            </div>
            <Inputbox
              ref={emailRef}
              label='이메일'
              type='text'
              placeholder='이메일 주소를 입력해주세요'
              error={emailerror}
              value={email}
              onChange={onEmailChangeHandler}
              onKeyDown={onEmailKeyDownHandler}
              message={emailerrormessage}
            />
            <Inputbox
              ref={usernameRef}
              label='닉네임'
              type='text'
              placeholder='닉네임을 입력해주세요'
              error={usernameerror}
              value={username}
              onChange={onUsernameChangeHandler}
              onKeyDown={onUsernameKeyDownHandler}
              message={usernameerrormessage}
            />
            <Inputbox
              ref={passwordRef}
              label='비밀번호'
              type={passwordType}
              placeholder='비밀번호를 입력해주세요'
              error={passworderror}
              value={password}
              onChange={onPasswordChangeHandler}
              icon={passwordIcon}
              onButtonClick={onPasswordButtonClickHandler}
              onKeyDown={onPasswordKeyDownHandler}
              message={passworderrormessage}
            />
            <Inputbox
              ref={passwordcheckRef}
              label='비밀번호 확인'
              type={passwordType}
              placeholder='비밀번호를 재입력해주세요'
              error={passwordcheckerror}
              value={passwordcheck}
              onChange={onPasswordcheckChangeHandler}
              icon={passwordIcon}
              onButtonClick={onPasswordButtonClickHandler}
              onKeyDown={onPasswordcheckKeyDownHandler}
              message={passwordcheckerrormessage}
            />
          </div>
          <div className="auth-card-bottom">
            <div className='black-large-full-button' onClick={onSignUpButtonClickHandler}>{'회원가입'}</div>
          </div>
        </div>
      </div>
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
