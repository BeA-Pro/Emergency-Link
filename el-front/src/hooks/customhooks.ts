import { useState, ChangeEvent } from 'react';

const useInput = <T extends HTMLInputElement | HTMLSelectElement>(initialValue: string) => {
  const [inputValue, setInputValue] = useState(initialValue);

  const handleChange = (e: ChangeEvent<T>) => {
    setInputValue(e.target.value);
  };

  return [inputValue, handleChange, setInputValue] as const;
};

export { useInput };
