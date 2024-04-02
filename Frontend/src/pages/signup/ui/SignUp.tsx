import { SignUpForm } from './SignUpForm/SignUpForm';
import { Agreement } from './Agreement/Agreement';
import { SignUpDone } from './SignUpDone/SignUpDone';
import { useSelector } from 'react-redux';
import { RootState } from '@/app/appProvider';
import { useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { setSignUpStep } from '../model/signUpStepReducer';

export function SignUp() {
    const dispatch = useDispatch();
    const currentStep = useSelector((state: RootState) => state.signUpStep.currentStep);
    const renderCurrentStep = () => {
        switch (currentStep) {
            case 1:
                return <SignUpForm />;
            case 2:
                return <SignUpDone />;
            default:
                return <Agreement />;
        }
    };
    useEffect(() => {
        dispatch(setSignUpStep(0));
    }, []);
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
