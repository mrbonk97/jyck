import { BrowserRouter, Routes, Route } from 'react-router-dom';
import ResetPassword from './pages/ResetPassword';
import { Toaster } from 'react-hot-toast';
import ValidWrapper from './components/ValidWrapper';
import ResetPasswordFinish from './pages/ResetPasswordFinish';

function App() {
  return (
    <>
      <Toaster position='top-center' reverseOrder={false} />

      <BrowserRouter>
        <Routes>
          <Route path='/reset-password' element={<ValidWrapper />}>
            <Route path='' element={<ResetPassword />} />
          </Route>
          <Route path='/reset-password-finish' element={<ResetPasswordFinish />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
