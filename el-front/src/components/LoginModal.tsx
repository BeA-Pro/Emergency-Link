import React from 'react';
import {Link} from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import { Modal, Button, Form } from 'react-bootstrap';
import { RootState } from '../app/store';
import { hideModal } from '../features/modalSlice';

// custom hooks
import { useInput } from '@/hooks/customhooks';

const LoginModal: React.FC = () => {

    // 모달 창 가시성 유무
    const show = useSelector((state: RootState) => state.modal.show);
    const dispatch = useDispatch();

    const handleClose = () => {
        dispatch(hideModal());
    };

    // input 받기
    const [email, handleEmail] = useInput('');
    const [pwd, handlePwd] = useInput('');

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
                <Form>
                    <Form.Group className="mb-3" controlId="formBasicEmail">
                        <Form.Label>이메일</Form.Label>
                        <Form.Control value={email} onChange={handleEmail} type="email" placeholder="이메일을 입력해주세요." />
                    </Form.Group>

                    <Form.Group className="mb-4" controlId="formBasicPassword">
                        <Form.Label>비밀번호</Form.Label>
                        <Form.Control type="password" value={pwd} onChange={handlePwd} placeholder="비밀번호를 입력해주세요." />
                    </Form.Group>
                    <Button className="mb-2 w-100" variant="primary" onClick={() => {/* 로그인 로직 */}}>
                        로그인
                    </Button>
                </Form>
            </Modal.Body>
            <Modal.Footer className="d-flex justify-content-center">
                <Link to="/join" className="d-grid gap-2">회원가입</Link>
            </Modal.Footer>
        </Modal>
    );
};

export default LoginModal;
