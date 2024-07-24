import React from 'react'

// scss
import styles from '@stylesComponents/Buttons.module.scss';
import logIn from '@icons/login-icons.svg';

// redux
import { useDispatch } from 'react-redux';
import { showModal } from '../features/modalSlice';



const LoginButton :React.FC= () => {
  const dispatch = useDispatch();

  const handleShow = () => {
      dispatch(showModal());
  };
  return (
    <img src={logIn} alt="logIn" className={styles.logIn} onClick={handleShow}/>
  )
}

const JoinMoveButton :React.FC= () => {

  return (
    <img src={logIn} alt="logIn" />
  )
}



export {LoginButton, JoinMoveButton};