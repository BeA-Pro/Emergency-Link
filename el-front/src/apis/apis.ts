import { LoginData, JoinData } from "@/types/Types";
import axios from 'axios';

const API_BASE_URL = process.env.REACT_APP_API_BASE_URL;

export const fetchJoinData = async (joinData: JoinData): Promise<Response> => {
  const response = await fetch(`${API_BASE_URL}/api/join`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(joinData),
  });

  return response;
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