import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

// scss
import "../node_modules/bootstrap/scss/bootstrap.scss";
import styles from '@/App.module.scss';

// components
import Header from '@/layouts/Header';
import Footer from '@/layouts/Footer';
import Main from '@/pages/Main';
import Join from './pages/Join';

function App() {
  return (
    <BrowserRouter>
      <div className={styles.wrapper}>
        <Header/>
        <Routes>
          <Route path='/' element={<Main/>}/>
          <Route path='/join' element={<Join/>}/>
        </Routes>
        <Footer/>
      </div>
    </BrowserRouter>
  );
}

export default App;