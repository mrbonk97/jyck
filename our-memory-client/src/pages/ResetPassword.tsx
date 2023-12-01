import { useState } from 'react';
import Input from '../components/Input';
import Button from '../components/Button';
import { useNavigate, useSearchParams } from 'react-router-dom';
import axios from 'axios';
import toast from 'react-hot-toast';

const ResetPassword = () => {
  const [searchParams] = useSearchParams();
  // console.log(searchParams.get('code'));

  const [password, setPassword] = useState('');
  const [passwordVal, setPasswordVal] = useState('');
  const [passwordError, setPasswordError] = useState(false);
  const [passwordValError, setpasswordValError] = useState(false);

  const navigate = useNavigate();

  const handleClick = async () => {
    if (password.length < 3) {
      toast.error('패스워드가 너무 짧습니다.');
      setPasswordError(true);
      return;
    }
    if (password != passwordVal) {
      toast.error('패스워드가 일치하지 않습니다.');
      setpasswordValError(true);
      return;
    }

    const code = searchParams.get('code');
    const result = await axios.post('/auth/change-password?code=' + code, { password });

    if (result.data.resultCode === 'SUCCESS') navigate('/reset-password-finish');
  };

  return (
    <main className='w-full h-full flex flex-col justify-center items-center gap-10 bg-neutral-300'>
      <img src='images/logo.png' width={300} />
      <div className='w-4/5 md:4/6 lg:w-3/6 xl:w-1/3 border-[1px] flex flex-col gap-10 rounded-xl shadow-2xl p-10 sm:mb-20 bg-white'>
        <hgroup className=''>
          <h1 className='text-4xl sm:text-5xl sm:mb-5'>비밀번호 재설정</h1>
          <p className='text-sm hidden sm:block'>비밀번호를 변경해 주세요.</p>
          <p className='text-sm hidden sm:block'>다른 아이디나 사이트에서 사용한 적 없는 안전한 비밀번호로 변경해 주세요.</p>
        </hgroup>
        <div className='py-0.5 w-full bg-neutral-200 rounded-full'></div>
        <div className='flex flex-col gap-4'>
          <Input id='password' label='비밀번호' type='password' setValue={setPassword} errors={passwordError} setErrors={setPasswordError} />
          <Input id='password_val' label='비밀번호 확인' type='password' setValue={setPasswordVal} errors={passwordValError} setErrors={setpasswordValError} />
          <Button label='재설정' onClick={handleClick} />
        </div>
      </div>
    </main>
  );
};

export default ResetPassword;
