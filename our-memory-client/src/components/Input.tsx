interface InputProps {
  id: string;
  label: string;
  type?: string;
  disabled?: boolean;
  required?: boolean;
  // handleChange: React.ChangeEventHandler<HTMLInputElement>;
  setValue: (value: string) => void;
  setErrors?: (value: boolean) => void;
  errors?: boolean;
}

const Input: React.FC<InputProps> = ({ id, label, type = 'text', disabled, setValue, setErrors, errors }) => {
  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    if (setErrors != null) setErrors(false);
    setValue(event.target.value);
  };

  return (
    <div className='w-full relative'>
      <input id={id} disabled={disabled} type={type} onChange={handleChange} className={`peer w-full p-4 pt-6 font-light bg-white rounded-md outline-none border-[2px] ${errors ? 'border-rose-500' : 'border-neutral-300'} `} />
      <label
        className={`absolute text-md duration-150 transform -translate-y-3 top-5 z-10 origin-[0] text-neutral-500 left-4
      peer-placeholder-shown:scale-100
      peer-placeholder-shown:translate-y-0
      peer-focus:scale-75
      peer-focus:-translate-y-4
      focus:border-black
      `}
      >
        {label}
      </label>
    </div>
  );
};

export default Input;
