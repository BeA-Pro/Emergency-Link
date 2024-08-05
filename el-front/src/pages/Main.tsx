import React, { useEffect, useState } from 'react'

// scss
import styles from '@stylesPages/Main.module.scss';

import { checkUser } from '@/apis/apis';
import Basic from './Basic';
import Hospital from './Hospital';
import User from './User';


const Main = () => {
  const [category, setCategory] = useState(0);
  useEffect(() => {
    const fetchCategory = async () => {
      try {
        const response = await checkUser(localStorage.getItem('token'));
        console.log(response);
        setCategory(response); // 응답에서 필요한 값을 설정합니다
      } catch (error) {
        console.error('Error checking user:', error);
      }
    };

    fetchCategory();
  }, []);
  return (
    <div className={styles.wrapper}>
      {category === 0 && <Basic />}
      {category === 1 && <Hospital />}
      {(category === 2 || category === 3) && <User />}
    </div>
  )
}

export default Main;