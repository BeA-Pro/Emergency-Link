import React from 'react'

// scss
import styles from '@stylesPages/Main.module.scss';
import LoginModal from '@/components/LoginModal';


const Main = () => {

  return (
    <div className={styles.wrapper}>
      <LoginModal />
    </div>
  )
}

export default Main;