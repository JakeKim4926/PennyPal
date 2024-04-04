import { SignUpForm } from './SignUpForm/SignUpForm';
import { Agreement } from './Agreement/Agreement';
import { SignUpDone } from './SignUpDone/SignUpDone';
import { useSelector } from 'react-redux';
import { RootState } from '@/app/appProvider';
import { useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { setSignUpStep } from '../model/signUpStepReducer';
import { setIsInitPage } from '@/shared';

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

        dispatch(setIsInitPage(true));
        return () => {
            dispatch(setIsInitPage(false));
        };
    }, []);
    return (
        <div className="welcomebox__container container" style={{ height: '100%' }}>
            <div className="welcomebox">
                <div className="welcomebox__top">
                    <p className="welcomebox__top-title">WELCOME TO PENNYPAL !</p>
                    <img src="assets/image/main-logo-colored.svg" />
                    <p className="welcomebox__top-sub">SIGN UP</p>
                </div>
                <div className="signupStep">{renderCurrentStep()}</div>
            </div>
        </div>
    );
}
