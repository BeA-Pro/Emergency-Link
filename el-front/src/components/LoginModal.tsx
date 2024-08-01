import React, { useEffect } from 'react';
import {Link, useNavigate} from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import { Modal, Button, Form } from 'react-bootstrap';
import { RootState } from '../app/store';
import { hideModal } from '../features/modalSlice';

// custom hooks
import { useInput } from '@/hooks/customhooks'

;
import { fetchLoginData } from '@/apis/apis';
import { LoginData, JwtPayload } from '@/types/Types';
import {jwtDecode} from 'jwt-decode';


const LoginModal: React.FC = () => {

    // 모달 창 가시성 유무
    const show = useSelector((state: RootState) => state.modal.show);
    const dispatch = useDispatch();

    const navigate = useNavigate();

    const handleClose = () => {
        dispatch(hideModal());
    };

    // input 받기
    const [email, handleEmail, setEmail] = useInput<HTMLInputElement>('');
    const [pwd, handlePwd, setPwd] = useInput<HTMLInputElement>('');

    // modal 창 닫을 경우 변수 초기화
    useEffect(()=>{
        if(show){
            setEmail('');
            setPwd('');
        }
    },[show,setEmail,setPwd]);

    const handleSubmit = async (e:React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const response = await fetchLoginData(new LoginData(email,pwd));
        if(response === "success"){
            const token = localStorage.getItem('token');
            if(token){
                const decoded = jwtDecode<JwtPayload>(token);
                handleClose();
                if(decoded.category === 1) navigate('/hospital');
                else navigate('/user');
            }
        }
        else console.log("로그인 실패");
    }

    return (
        <Modal 
            show={show}
            onHide={handleClose}
            aria-labelledby="contained-modal-title-vcenter"
            centered
        >
            <Modal.Header closeButton>
                <Modal.Title>로그인</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form onSubmit={handleSubmit}>
                    <Form.Group className="mb-3" controlId="formBasicEmail">
                        <Form.Label>이메일</Form.Label>
                        <Form.Control value={email} onChange={handleEmail} type="email" placeholder="이메일을 입력해주세요." />
                    </Form.Group>

                    <Form.Group className="mb-4" controlId="formBasicPassword">
                        <Form.Label>비밀번호</Form.Label>
                        <Form.Control type="password" value={pwd} onChange={handlePwd} placeholder="비밀번호를 입력해주세요." />
                    </Form.Group>
                    <Button className="mb-2 w-100" variant="primary" type="submit">
                        로그인
                    </Button>
                </Form>
            </Modal.Body>
            <Modal.Footer className="d-flex justify-content-center">
                <Link to="/join" className="d-grid gap-2" onClick={handleClose}>회원가입</Link>
            </Modal.Footer>
        </Modal>
    );
};

export default LoginModal;
