import { SignUpForm } from './SignUpForm';
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


