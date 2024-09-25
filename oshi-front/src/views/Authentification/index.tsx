import React, { useState } from 'react';
import './style.css';
import SignInCard from 'components/Sign/SignIn';
import SignUpCard from 'components/Sign/SignUp';

export default function Authentication() {
  const [view, setView] = useState<'sign-in' | 'sign-up'>('sign-in');
  return (
    <div className='auth-wrapper'>
      <div className='auth-container'>
        <div className='auth-jumbotron-box'>
          <div className='auth-jumbotron-content'>
            <div className='auth-jumbotron-logo-box'>
              <div className='auth-jumbotron-logo'>
              </div>
            </div>
            <div className='auth-jumbotron-text-box'>
              <div className='auth-jumbotron-text'>{'오시에서 최애의 굿즈 정보를 공유해요!'}</div>
            </div>
          </div>
          <div className='card'>
            {view === 'sign-in' && <SignInCard setView={setView} />}
            {view === 'sign-up' && <SignUpCard setView={setView} />}
          </div>
        </div>
      </div>
    </div>
  );
}