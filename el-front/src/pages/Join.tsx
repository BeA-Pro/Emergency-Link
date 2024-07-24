import React from 'react';
import styles from '@stylesPages/Join.module.scss';
import { Form, Button } from 'react-bootstrap';
import { useInput } from '@/hooks/customhooks';

const Join: React.FC = () => {
  const [email, handleEmail] = useInput<HTMLInputElement>('');
  const [pwd, handlePwd] = useInput<HTMLInputElement>('');
  const [category, handleCategory] = useInput<HTMLSelectElement>('');

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    // 여기서 email, pwd, category 값을 사용하여 폼 데이터를 처리합니다.
    console.log({ email, pwd, category });
  };

  return (
    <div className={styles.wrapper}>
      <Form className={`${styles.formWrapper} w-75 h-75`} onSubmit={handleSubmit}>
        <h1 className={`${styles.title} mb-3`}>회원가입</h1>
        <Form.Group className="mb-3 w-50 w-sm-75" controlId="formBasicEmail">
          <Form.Label>이메일</Form.Label>
          <Form.Control type="email" placeholder="Enter email" value={email} onChange={handleEmail} />
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
            <option value="3">일반인</option>
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
