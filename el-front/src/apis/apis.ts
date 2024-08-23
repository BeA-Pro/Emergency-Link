import { LoginData, UserInfoDto } from "@/types/Types";
import axios from 'axios';

const API_BASE_URL = process.env.REACT_APP_API_BASE_URL;

export const checkUser = async (token:String|null): Promise<number> =>{
  try{
    const response = await axios.get(`${API_BASE_URL}/api/main`,{
      headers:{
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      }
    })

    return Number(response.data.category);
  }catch{
    return 0;
  }
}

export const fetchJoinUserInfo = async (joinData: UserInfoDto): Promise<number> => {
  try {
    const response = await axios.post(`${API_BASE_URL}/api/join/userInfo`, joinData, {
      headers: {
        'Content-Type': 'application/json',
      }
    });
    return response.status;
  } catch (error) {
  
    return 401;
  }

};


export const fetchLoginData = async (loginData: LoginData): Promise<number> => {
  const formData = new FormData();
  formData.append('username', loginData.userId);
  formData.append('password', loginData.pwd);
  let response;
  try {
    response = await axios.post(`${API_BASE_URL}/login`, formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      }
    });

    if (response.status === 200) {
      console.log(response);
      const token = response.headers.authorization.split(' ')[1];
      if (token) {
        localStorage.setItem('token', token);
      }

      return response.status;
    } else if (response.status === 401) {
      return response.status;
    }
  } catch (error) {

    return 401;
  }
  return 401;
}