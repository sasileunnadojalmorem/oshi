import { useState, useRef } from 'react';
import './style.css';
import Inputbox from 'components/inputbox/inputbox';
import { useCookies } from 'react-cookie';
import { ResponseDto } from 'apis/response';
import {  OshiResponseDto } from 'apis/response/oshi';
import { OshiAddRequestDto } from 'apis/request/oshi';
import { UrlResponseDto } from 'apis/response/image';
import { useNavigate } from 'react-router-dom';

export default function Oshiadd() {
  const navigater = useNavigate();
  const [cookies] = useCookies();
  const ohsinameRef = useRef<HTMLInputElement | null>(null);
  const oshidescRef = useRef<HTMLInputElement | null>(null);
  const [oshiname, setOshiname] = useState<string>('');
  const [oshidesc, setOshidesc] = useState<string>('');
  const [image, setImage] = useState<File | null>(null);
  const [imagePreviewUrl, setImagePreviewUrl] = useState<string>('');

  const [oshinameError, setOshinameError] = useState(false);
  const [oshidescError, setOshidescError] = useState(false);
  const [oshinameErrorMessage, setOshinameErrorMessage] = useState('');
  const [oshidescErrorMessage, setOshidescErrorMessage] = useState('');

  const onOshinameChangeHandler = (event: React.ChangeEvent<HTMLInputElement>) => {
    setOshiname(event.target.value);
    setOshinameError(false);
    setOshinameErrorMessage('');
  };

  const onOshidescChangeHandler = (event: React.ChangeEvent<HTMLInputElement>) => {
    setOshidesc(event.target.value);
    setOshidescError(false);
    setOshidescErrorMessage('');
  };

  const onImageChangeHandler = (event: React.ChangeEvent<HTMLInputElement>) => {
    if (event.target.files && event.target.files[0]) {
      const file = event.target.files[0];
      setImage(file);
      const reader = new FileReader();
      reader.onloadend = () => {
        setImagePreviewUrl(reader.result as string);
      };
      reader.readAsDataURL(file);
    }
  };

  const OshiAddResponse = (response: OshiResponseDto | ResponseDto | null) => {
    if (response === null) {
      alert("네트워크 상태 확인");
      return;
    }
    if ('code' in response) {
      const { code } = response as ResponseDto;
      if (code === 'DN') {
        setOshinameError(true);
        setOshinameErrorMessage("중복된 이름입니다.");
      }
    } else {
      navigater('/');
    }
  };

  const UrlResponse = (response: UrlResponseDto | ResponseDto | null) => {
    if (response === null) {
      alert("이미지 업로드 실패");
      return '';
    }
    const { url } = response as UrlResponseDto;
    return url;
  };

  const onAddButtonClickHandler = async () => {
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

    if (image) {
      const formData = new FormData();
      formData.append('file', image);

      const urlResponse = await upload(cookies.accessToken, formData);
      const imageUrl: string = UrlResponse(urlResponse);

      const requestBody: OshiAddRequestDto = {
        name: oshiname,
        description: oshidesc,
        profileImageUrl: imageUrl
      };

      const oshiResponse = await postOshi(cookies.accessToken, requestBody);
      OshiAddResponse(oshiResponse);
    }
  };

  return (
    <div id='oshi-add-wrapper'>
      <div className='oshi-add-container'>
        <div className='oshi-add-box'>
          <div className='oshi-add'>
            <div className='oshi-add-top'>
              <div className='oshi-add-top-text'>{'Osh! 추가'}</div>
            </div>
            <div className='oshi-add-bottom'>
              <div className='oshi-add-bottom-imagebox' style={{ backgroundImage: `url(${imagePreviewUrl})`, backgroundSize: 'cover', backgroundPosition: 'center' }}>
                <input
                  type="file"
                  accept="image/*"
                  style={{ display: 'none' }}
                  onChange={onImageChangeHandler}
                  id="image-upload"
                />
                <label htmlFor="image-upload" className='oshi-add-bottom-inputbox-bottom-imagebutton' style={{ position: 'absolute', zIndex: 1, cursor: 'pointer' }}>
                  이미지 선택
                </label>
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