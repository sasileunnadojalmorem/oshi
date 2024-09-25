// src/App.tsx
import { useEffect } from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import Main from 'views/Main';  
import Authentication from 'views/Authentification';
import Oshilistcategory from 'views/Oshi/Oshishow/Category';
import Oshilistgoods from 'views/Oshi/Oshishow/Goods';
import User from 'views/User';
import Goodsadd from 'views/Goods/add';
import Goodsupdate from 'views/Goods/update';
import Goodsdetail from 'views/Goods/detail';
import Salesadd from 'views/sale/add';
import Salesdetail from 'views/sale/detail';
import Salesupdate from 'views/sale/update';
import Container from 'layout/container';
import { 
  ADD_PATH, 
  AUTH_PATH, 
  CATEGORY_PATH, 
  GOODS_DETAIL_PATH, 
  GOODS_PATH, 
  GOODS_UPDATE_PATH, 
  MAIN_PATH, 
  OSHI_PATH, 
  OSHI_SHOW_PATH, 
  SALE_PATH, 
  SALES_DETAIL_PATH, 
  SALES_UPDATE_PATH, 
  USER_PATH 
} from 'constant';
import { getSignInUserRequest } from 'apis/controller/User';
import { useCookies } from 'react-cookie';
import { useLoginUserStore } from 'stores';
import { GetSigninUserResponseDto } from 'apis/response/user';
import { ResponseDto } from 'apis/response';
import { UserItem } from 'types/interface';
import useAuthStore from 'stores/useAuthStore';

function App() {
    // Zustand 스토어
    const { setUser,setAccessToken,resetAuth } = useAuthStore();
    // Cookies
    const [cookies] = useCookies(['accessToken']);

    // function: get sign-in user response 
    const getSignInUserResponse = (responseBody: GetSigninUserResponseDto | ResponseDto | null) => {
      if (!responseBody) return;

      if ('code' in responseBody) {
        const { code } = responseBody;
        if (code === 'DBE' || code === 'NU' || code === 'VF') {
          // 오류 코드에 따른 처리 (예: 로그아웃, 에러 메시지 표시 등)
          resetAuth();
          return;
        }
      }
      if('accessToken' in responseBody && 'expirationTime' in responseBody) {
        const {accessToken, expirationTime } = responseBody;
        setAccessToken(accessToken as string, expirationTime as number);
      }

      // responseBody가 GetSigninUserResponseDto인지 확인
      if ('email' in responseBody && 'username' in responseBody && 'userId' in responseBody) {
        const loginUser: UserItem = {
            userId: responseBody.userId,
            userEmail: responseBody.email,
            userName: responseBody.username,
            userImage: responseBody.profileImageUrl || null,
        };
        setUser(loginUser);
      }
    }

    // effect: access token cookie 값이 변경될 때 이펙트
    useEffect(() => {
      if (!cookies.accessToken) {
        resetAuth();
        return;
      }
      getSignInUserRequest(cookies.accessToken)
        .then(getSignInUserResponse)
        .catch((error) => {
          console.error("Error fetching sign-in user:", error);
          resetAuth();
        });
      
    }, [cookies.accessToken, resetAuth]);

    // Zustand 스토어에서 로그인 상태 확인
    const loginUser = useLoginUserStore(state => state.loginUser);

    return (
      <Router>
        <Routes>
          <Route element={<Container />}>
            <Route path={MAIN_PATH()} element={<Main />} />
            <Route path={AUTH_PATH()} element={<Authentication />} />
            <Route path={OSHI_PATH()}>
              <Route path={ADD_PATH()} element={<Navigate to="/" replace />} />
              <Route path={OSHI_SHOW_PATH(':oshid')}>
                <Route path={CATEGORY_PATH()} element={<Navigate to="/" replace />} />
                <Route path={GOODS_PATH()} element={<Navigate to="/" replace />} />
              </Route>
            </Route>
            <Route path={`/goods/:oshiid`}>
              <Route path={ADD_PATH()} element={<Navigate to="/" replace />} />
              <Route path={GOODS_UPDATE_PATH(':goodsid')} element={<Navigate to="/" replace />} />
              <Route path={GOODS_DETAIL_PATH(':goodsid')} element={<Navigate to="/" replace />} />
            </Route>
            <Route path={SALE_PATH()}>
              <Route path={ADD_PATH()} element={<Navigate to="/" replace />} />
              <Route path={SALES_DETAIL_PATH(':salesid')} element={<Navigate to="/" replace />} />
              <Route path={SALES_UPDATE_PATH(':salesid')} element={<Navigate to="/" replace />} />
            </Route>
            <Route path={USER_PATH(':userEmail')} element={<Navigate to="/" replace />} />
          </Route>
          <Route path='*' element={<h1>404 Not Found</h1>} />
        </Routes>
      </Router>
    );
}

export default App;