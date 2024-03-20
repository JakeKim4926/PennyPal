import { useRef, useState } from "react";
import { Button } from "../../../shared";

export function SignUp() {
    return (
        <div className="signup container">
            <div className="welcomebox">
                <p className="welcomebox__text">WELCOME TO PENNYPAL !</p>
                <div className="welcomebox__logo">
                    <img src="assets/image/main-logo-colored.svg"/>
                </div>
                <p className="welcomebox__singup">SIGN UP</p>
            </div>
            <div className="signupForm">
                <SignUpForm/>
            </div>
        </div>
        )
}
interface UserData {
  email: string;
  password: string;
  confirmPassword: string;
  name: string;
  birthday: string;
  nickname: string;
}

const SignUpForm: React.FC = () => {
  const emailRef = useRef<HTMLInputElement>(null);
  const passwordRef = useRef<HTMLInputElement>(null);
  const confirmPasswordRef = useRef<HTMLInputElement>(null);
  const nameRef = useRef<HTMLInputElement>(null);
  const birthdayRef = useRef<HTMLInputElement>(null);
  const nicknameRef = useRef<HTMLInputElement>(null);

  const [userData, setUserData] = useState<UserData>({
    email: '',
    password: '',
    confirmPassword: '',
    name: '',
    birthday: '',
    nickname: ''
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>, field: keyof UserData) => {
    setUserData(prevData => ({
      ...prevData,
      [field]: e.target.value
    }));
  };

  const handleNext = () => {
    // 입력값 확인 및 다음 단계로 이동하는 로직 구현
    console.log(userData); // 입력값 확인
    // 다음 단계로 이동하는 로직 추가
  };

  return (
    <div>
      <input type="text" placeholder="이메일" ref={emailRef} onChange={(e) => handleChange(e, 'email')} />
      <input type="password" placeholder="패스워드" ref={passwordRef} onChange={(e) => handleChange(e, 'password')} />
      <input type="password" placeholder="패스워드 확인" ref={confirmPasswordRef} onChange={(e) => handleChange(e, 'confirmPassword')} />
      <input type="text" placeholder="이름" ref={nameRef} onChange={(e) => handleChange(e, 'name')} />
      <input type="text" placeholder="생일" ref={birthdayRef} onChange={(e) => handleChange(e, 'birthday')} />
      <input type="text" placeholder="닉네임" ref={nicknameRef} onChange={(e) => handleChange(e, 'nickname')} />
      <div className="nextButton">
                <Button child={"NEXT"} color={"light"} onClick={handleNext}/>
      </div>
    </div>
  );
};



