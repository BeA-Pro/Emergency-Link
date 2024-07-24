import {useState, ChangeEvent} from 'react'

const useInput = (initialValue : string) => {
  const [inputValue, setInputValue] = useState(initialValue);

  const handleChange = (e: ChangeEvent<HTMLInputElement>) =>{
    setInputValue(e.target.value);
    console.log(inputValue);
  }

  return [inputValue, handleChange, setInputValue] as const;
}

export {useInput}