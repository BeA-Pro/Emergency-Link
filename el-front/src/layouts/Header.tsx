import React from 'react';
import styles from '@stylesLayouts/Headers.module.scss';

import logo from '@logos/horizontal-logo.svg';
const Header = () => {
  return (
    <header className={styles.wrapper}>
      <img src={logo} alt={logo} className={styles.logo}/>
    </header>
  )
}

export default Header;