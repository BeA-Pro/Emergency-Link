import React from 'react';

// scss
import styles from '@stylesLayouts/Headers.module.scss';

// assets
import logo from '@logos/horizontal-logo.svg';
import { LoginButton } from '@/components/Buttons';


const Header = () => {
  
  return (
    <header className={styles.wrapper}>
      <img src={logo} alt="logo" className={styles.logo}/>
      
      <LoginButton />
    </header>
  )
}

export default Header;