import { LoginData, JoinData } from "@/models/Models";

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

export const fetchLoginData = async (loginData: LoginData): Promise<Response> =>{
  const response = await fetch(`${API_BASE_URL}/api/login`,{
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(loginData)
  })

  return response;
}