import { useState, useRef } from 'react';
import './style.css';
import Inputbox from 'components/inputbox/inputbox';
import { OshiAddRequset } from 'apis';
import { useCookies } from 'react-cookie';
import { ResponseDto } from 'apis/response';
import { OshiAddResponseDto } from 'apis/response/oshi';
import { OshiAddRequestDto } from 'apis/request/oshi';
// component 최애 추가 컴포넌트//
export default function Oshiadd() {
  // state : 쿠키 상태
  const [cookies, setCookies] = useCookies();
  // state: 최애 이름 참조 상태
  const ohsinameRef = useRef<HTMLInputElement | null>(null);
  // state: 최애 desc 참조 상태
  const oshidescRef = useRef<HTMLInputElement | null>(null);

  // state: 최애 이름 밸류 상태
  const [oshiname, setOshiname] = useState<string>('');
  // state: 최애 description 밸류 상태
  const [oshidesc, setOshidesc] = useState<string>('');
  const [image,setImage] = useState<string>('dddd');
  // state : 최애 이름 에러 상태
  const [oshinameError, setOshinameError] = useState(false);
  // state : 최애 description 에러 상태
  const [oshidescError, setOshidescError] = useState(false);

  // state : 최애 이름 에러 메시지 상태
  const [oshinameErrorMessage, setOshinameErrorMessage] = useState('');
  // state : 최애 description 에러 메시지 상태
  const [oshidescErrorMessage, setOshidescErrorMessage] = useState('');

  // event handler : 최애 이름 변경 이벤트 처리
  const onOshinameChangeHandler = (event: React.ChangeEvent<HTMLInputElement>) => {
    setOshiname(event.target.value);
    setOshinameError(false);
    setOshinameErrorMessage('');
  };

  // event handler : 최애 description 변경 이벤트 처리
  const onOshidescChangeHandler = (event: React.ChangeEvent<HTMLInputElement>) => {
    setOshidesc(event.target.value);
    setOshidescError(false);
    setOshidescErrorMessage('');
  };

  // API 응답 처리 함수
  const OshiAddResponse = (response: OshiAddResponseDto | ResponseDto | null) => {
    // 여기서 response 처리, 예를 들어 성공/실패 메시지 표시 등
    if (response === null) {
      alert("네트워크 상태 확인")
      return
    }
    const { code } = response;

    if (code === 'DBE') alert('데이터베이스 오류입니다');
      if (code === 'SF' || code === 'VF') {
        alert("오류")
      }
      if (code !== 'SU') return;

  };

  // event handler : 최애 추가 버튼 클릭 이벤트 처리
  const onAddButtonClickHandler = () => {
    setOshinameError(false);
    setOshidescError(false);

    let isValid = true;

    if (oshiname.length > 50) {
      setOshinameError(true);
      setOshinameErrorMessage('50자 이하로 입력하세요');
      isValid = false;
    }
    if (oshidesc.length > 50) {
      setOshidescError(true);
      setOshidescErrorMessage('50자 이하로 입력하세요');
      isValid = false;
    }

    if (!isValid) return;

    const requestBody: OshiAddRequestDto = {
      name: oshiname,
      description: oshidesc,
      profileImageUrl: image
    };

    OshiAddRequset(cookies.accessToken, requestBody)
      .then(OshiAddResponse);
      

    return;
  };

  // render 최애 추가 컴포넌트 랜더//
  return (
    <div id='oshi-add-wrapper'>
      <div className='oshi-add-container'>
        <div className='oshi-add-box'>
          <div className='oshi-add'>
            <div className='oshi-add-top'>
              <div className='oshi-add-top-text'>{'Osh! 추가'}</div>
            </div>
            <div className='oshi-add-bottom'>
              <div className='oshi-add-bottom-imagebox'>
                <div className='oshi-add-bottom-image'></div>
              </div>
              <div className='oshi-add-bottom-box'>
                <div className='oshi-add-bottom-box-top'>
                  <Inputbox
                    ref={ohsinameRef}
                    label='최애 이름'
                    type='text'
                    placeholder='최애 이름'
                    error={oshinameError}
                    value={oshiname}
                    message={oshinameErrorMessage}
                    onChange={onOshinameChangeHandler}
                  />
                </div>
                <div className='oshi-add-bottom-inputbox-middle'>
                  <Inputbox
                    ref={oshidescRef}
                    label='최애 설명'
                    type='text'
                    placeholder='최애 설명을 적어주세요!'
                    error={oshidescError}
                    value={oshidesc}
                    message={oshidescErrorMessage}
                    onChange={onOshidescChangeHandler}
                  />
                </div>
                <div className='oshi-add-bottom-inputbox-bottom'>
                  <button className='oshi-add-bottom-inputbox-bottom-imagebutton'>
                    이미지 업로드
                  </button>
                  <button
                    className='oshi-add-bottom-inputbox-bottom-writebutton'
                    onClick={onAddButtonClickHandler}
                  >
                    작성
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
