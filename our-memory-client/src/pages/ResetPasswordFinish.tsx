const ResetPasswordFinish = () => {
  return (
    <main className='w-full h-full flex flex-col justify-center items-center gap-10 bg-neutral-300'>
      <div className='flex flex-col justify-center items-center bg-white w-4/5 md:4/6 lg:w-3/6 xl:w-1/3 border-[1px] gap-10 rounded-xl shadow-2xl p-10 sm:mb-20'>
        <img src='images/logo.png' width={300} />
        <h1 className='text-xl text-center'>패스워드 변경완료.</h1>
      </div>
    </main>
  );
};

export default ResetPasswordFinish;
