import React, { useEffect, useState } from 'react';
import styles from '@stylesPages/Join.module.scss';
import { Form, Button } from 'react-bootstrap';
import { useInput } from '@/hooks/customhooks';
import { fetchJoinData } from '@/apis/apis';
import { UserInfoDto } from '@/types/Types';
import { useNavigate } from 'react-router-dom';

const Join: React.FC = () => {
  const [name, handleName, setName] = useInput<HTMLInputElement>('');
  const [email, handleEmail, setEmail] = useInput<HTMLInputElement>('');
  const [pwd, handlePwd, setPwd] = useInput<HTMLInputElement>('');
  const [category, handleCategory, setCategory] = useInput<HTMLSelectElement>('');
  const [message, setMessage] = useState('');
  const navigate = useNavigate();

  useEffect(()=>{
    setName('');
    setEmail('');
    setPwd('');
    setCategory('');
    setMessage('');
  },[setName,setEmail,setPwd,setCategory,setMessage])

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    let response;
    if(category === "2") response = await fetchJoinData(new UserInfoDto(email,pwd,name));
    if(response === 'success'){
      alert('회원가입 성공!');
      navigate('/');
    }
    else setMessage('중복되는 아이디가 존재합니다.');
  };

  return (
    <div className={styles.wrapper}>
      <Form className={`${styles.formWrapper} w-75 h-75`} onSubmit={handleSubmit}>
        <h1 className={`${styles.title} mb-3`}>회원가입</h1>
        <Form.Group className="mb-3 w-50 w-sm-75" controlId="formBasicEmail">
          <Form.Label>이름</Form.Label>
          <Form.Control type="name" placeholder="Enter name" value={name} onChange={handleName} />
        </Form.Group>
        <Form.Group className="mb-3 w-50 w-sm-75" controlId="formBasicEmail">
          <Form.Label>이메일</Form.Label>
          <Form.Control type="email" placeholder="Enter email" value={email} onChange={handleEmail} />
          <div>{message}</div>
        </Form.Group>
        <Form.Group className="mb-3 w-50" controlId="formBasicPassword">
          <Form.Label>비밀번호</Form.Label>
          <Form.Control type="password" placeholder="Password" value={pwd} onChange={handlePwd} />
        </Form.Group>
        <Form.Group className="mb-5 w-50" controlId="formBasicCategory">
          <Form.Label>가입 유형</Form.Label>
          <Form.Select aria-label="Default select example" value={category} onChange={handleCategory}>
            <option value="">가입 유형을 선택해주세요</option>
            <option value="1">병원</option>
            <option value="2">구급 대원</option>
          </Form.Select>
          
        </Form.Group>
        
        <Button variant="primary" type="submit" className="w-50">
          가입하기
        </Button>
      </Form>
    </div>
  );
};

export default Join;
