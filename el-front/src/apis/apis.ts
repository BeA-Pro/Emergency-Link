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

export const fetchJoinData = async (joinData: UserInfoDto): Promise<String> => {
  try {
    const response = await axios.post(`${API_BASE_URL}/api/join`, joinData, {
      headers: {
        'Content-Type': 'application/json',
      }
    });
    return response.data.status;
  } catch (error) {
  
    return "fail";
  }

};


export const fetchLoginData = async (loginData: LoginData): Promise<string> => {
  try {
    const response = await axios.post(`${API_BASE_URL}/api/login`, loginData, {
      headers: {
        'Content-Type': 'application/json',
      }
    });

    if (response.status === 200) {
      const token = response.data.token;
      if (token) {
        localStorage.setItem('token', token);
      }

      return response.data.status;
    } else if (response.status === 401) {
      return response.data.status;
    }
  } catch (error) {

    return "fail";
  }

  return "fail";
}