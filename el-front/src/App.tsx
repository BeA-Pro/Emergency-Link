import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import LoginModal from '@/components/LoginModal';
// scss
import "../node_modules/bootstrap/scss/bootstrap.scss";
import styles from '@/App.module.scss';

// components
import Header from '@/layouts/Header';
import Footer from '@/layouts/Footer';
import Main from '@/pages/Main';
import Join from './pages/Join';
import User from './pages/User';
import Hospital from './pages/Hospital';

function App() {
  return (
    <BrowserRouter>
      <div className={styles.wrapper}>
        <Header/>
        <Routes>
          <Route path='/' element={<Main/>}/>
          <Route path='/join' element={<Join/>}/>
          <Route path='/user' element={<User/>}/>
          <Route path='/hospital' element={<Hospital />}/>
        </Routes>
        <Footer/>
        {/* Modal */}
        <LoginModal />
      </div>
    </BrowserRouter>
  );
}

export default App;