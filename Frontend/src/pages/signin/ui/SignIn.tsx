export function SignIn() {
    return (
        <div className="signin container">
            <div className="welcomebox">
                <p className="welcomebox__text">WELCOME TO PENNYPAL !</p>
                <div className="welcomebox__logo">
                    <img src="assets/image/main-logo-colored.svg" />
                </div>
                <p className="welcomebox__singIn">SIGN IN</p>
            </div>
            <div className="signin-progress container">
                <div>
                    <p>E-mail</p>
                    <div className="input-container">
                        <img src="assets/image/icons_mini/Email.svg" />
                        <input type="text" placeholder="이메일" onChange={() => {}} />
                    </div>
                    <p>Password</p>
                    <div className="input-container">
                        <img src="assets/image/icons_mini/password.svg" />
                        <input type="password" placeholder="패스워드" onChange={(e) => {}} />
                    </div>
                </div>
            </div>
        </div>
    );
}
