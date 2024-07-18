import React from 'react';

// scss
import styles from '@stylesLayouts/Headers.module.scss';

// assets
import logo from '@logos/horizontal-logo.svg';
import logIn from '@icons/login-icons.svg';

const Header = () => {
  
  return (
    <header className={styles.wrapper}>
      <img src={logo} alt="logo" className={styles.logo}/>
      <img src={logIn} alt="logIn" className={styles.logIn}/>
    </header>
  )
}

export default Header;