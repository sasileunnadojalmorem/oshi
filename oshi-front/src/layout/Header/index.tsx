import { useEffect, useState } from 'react';
import './style.css';
import { useNavigate } from 'react-router-dom';
import { AUTH_PATH, MAIN_PATH, USER_PATH } from 'constant';
import { useCookies } from 'react-cookie';
import useAuthStore from 'stores/useAuthStore';
import View_Myoshi from 'components/OshiListView';  // Component to show Oshi List when user is logged in
import Dropdown from 'components/View-CategoryPage/dropdown';  // Dropdown for the search category
import SearchComponent from 'components/Search/SearchComponset';

export default function Header() {
  const navigate = useNavigate();
  const [cookie, setCookie] = useCookies();
  const { user, resetAuth } = useAuthStore();  // Access auth store for login state
  const [isLogin, setLogin] = useState<boolean>(false);  // Track login state

  // Update login state based on user info from store
  useEffect(() => {
    setLogin(user !== null);
  }, [user]);

  // Handler to navigate to main page when logo is clicked
  const onLogoClickHandler = () => {
    navigate(MAIN_PATH());
  };

  // Login/Logout & MyPage button component based on login state
  const LoginMypageButton = () => {
    const onMyPageButtonClickHandler = () => {
      if (!user) return;
      navigate(USER_PATH(user.userEmail));  // Navigate to user's MyPage
    };

    const onLogOutButtonClickHandler = () => {
      resetAuth();  // Clear user info and token from store
      setCookie('accessToken', '', { expires: new Date() });  // Clear token cookie
      navigate(MAIN_PATH());  // Redirect to main page after logout
    };

    const onLogInButtonClickHandler = () => {
      navigate(AUTH_PATH());  // Redirect to login page
    };

    // When user is logged in, show MyPage and Logout button
    if (isLogin)
      return (
        <div className='header-user-box'>
          <div className='button-box' onClick={onMyPageButtonClickHandler}>
            <div className='button-text'>{'마이 페이지'}</div>
          </div>
          <div className='log-out-button-box button-box' onClick={onLogOutButtonClickHandler}>
            <div className='button-text'>{'로그 아웃'}</div>
          </div>
        </div>
      );

    // If user is not logged in, show Login button
    return (
      <div className='log-in-button-box' onClick={onLogInButtonClickHandler}>
        <div className='button-text'>{'로그인'}</div>
      </div>
    );
  };

  // Search component (always visible)
  const SearchBar = () => {
    const [searchTerm, setSearchTerm] = useState<string>('');  // Track search input

    const onSearchChangeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
      setSearchTerm(e.target.value);  // Update search term
    };

    const onSearchSubmitHandler = (e: React.KeyboardEvent<HTMLInputElement>) => {
      if (e.key === 'Enter') {
        console.log(`Searching for: ${searchTerm}`);
        // Implement search logic here
      }
    };

    return (
      <div className='header-search'>
          {/* This will allow selecting a search category */}
        <SearchComponent/>
      </div>
    );
  };

  return (
    <div className='header'>
      <div className='header-container'>
        {/* Top section with logo */}
        <div className='header-top'>
          <div className='header-logo-box'>
            <div className='oshi-logo' onClick={onLogoClickHandler}>
              {/* Logo image */}
              <div className='oshi-logo-image'></div>
            </div>
          </div>

          {/* Conditionally show Oshi List only if the user is logged in */}
          {isLogin && (
            <div className='Myoshi-view'>
              <View_Myoshi />  {/* Show Oshi List for logged-in user */}
            </div>
          )}
        </div>

        {/* Search Bar, always visible */}
        <div className='header-middle'>
          <SearchBar />
        </div>

        {/* Bottom section for login/logout buttons */}
        <div className='header-bottom'>
          <LoginMypageButton />
        </div>
      </div>
    </div>
  );
}