import { useState } from 'react';

export default function useToken() {
  const getToken = () => {
    const tokenString = sessionStorage.getItem('token');
    const userToken = JSON.parse(tokenString);
    return userToken?.token
  };

  const [token, setToken] = useState(getToken());
  const [email, setEmail] = useState('')
  const saveToken = userToken => {
    console.log('userToken=',userToken);
    if (userToken!== null){
    sessionStorage.setItem('token', JSON.stringify(userToken));
    setToken(userToken.token);
    setEmail(userToken.email);

    }
  };

  return {
    setToken: saveToken,
    token,
    email
  }
}