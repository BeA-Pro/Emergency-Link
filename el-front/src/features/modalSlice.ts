import { createSlice, PayloadAction } from '@reduxjs/toolkit';

// 초기 상태 정의
interface ModalState {
    show: boolean;
}

const initialState: ModalState = {
    show: false,
};

// 슬라이스 생성
const modalSlice = createSlice({
    name: 'modal',
    initialState,
    reducers: {
        showModal(state) {
            state.show = true;
        },
        hideModal(state) {
            state.show = false;
        },
    },
});

// 액션과 리듀서를 내보냄
export const { showModal, hideModal } = modalSlice.actions;
export default modalSlice.reducer;
