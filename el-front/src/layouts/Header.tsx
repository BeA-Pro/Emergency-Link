import React from 'react';

// scss
import styles from '@stylesLayouts/Headers.module.scss';

// assets
import logo from '@logos/horizontal-logo.svg';
import { LoginButton } from '@/components/Buttons';
import { useNavigate } from 'react-router-dom';


const Header = () => {
  const navigate = useNavigate();
  return (
    <header className={styles.wrapper}>
      <img src={logo} alt="logo" className={styles.logo} onClick={()=>navigate('/')}/>
      
      <LoginButton />
    </header>
  )
}

export default Header;