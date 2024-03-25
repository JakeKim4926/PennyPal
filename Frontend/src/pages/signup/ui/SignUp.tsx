import { SignUpForm } from './SignUpForm';
import { Agreement } from './Agreement';
import { SignUpDone } from './SignUpDone';
import { useSelector } from 'react-redux';
import { RootState } from 'app/appProvider';

export function SignUp() {
    const currentStep = useSelector((state: RootState) => state.signUpStep.currentStep);
    const renderCurrentStep = () => {
        switch (currentStep) {
            case 1:
                return <Agreement />;
            case 2:
                return <SignUpDone />;
            default:
                return <SignUpForm />;
        }
    };

    return (
        <div className="signup container">
            <div className="welcomebox">
                <p className="welcomebox__text">WELCOME TO PENNYPAL !</p>
                <div className="welcomebox__logo">
                    <img src="assets/image/main-logo-colored.svg" />
                </div>
                <p className="welcomebox__singup">SIGN UP</p>
            </div>
            <div className="signup-process container">{renderCurrentStep()}</div>
        </div>
    );
}
