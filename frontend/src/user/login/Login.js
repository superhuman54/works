import React, { Component } from 'react';
import { Redirect } from 'react-router-dom'
import {GOOGLE_AUTH_URL} from "../../constants";
import googleLogo from '../../img/google-logo.png';


export default class Login extends Component {

    render() {
        if (this.props.authenticated) {
            return <Redirect
                to={{
                    pathname: "/home",
                    state: { from: this.props.location }
                }} />;
        }

        return (
            <div className="login-container">
                <div className="login-content">
                    <h1 className="login-title">WORKS</h1>
                    <SocialLogin />
                </div>
            </div>
        )
    }
}

class SocialLogin extends Component {
    render() {
        return (
            <div className="social-login">
                <a className="btn btn-block social-btn google" href={GOOGLE_AUTH_URL}>
                    <img src={googleLogo} alt="Google" /> 구글 로그인 </a>
            </div>
        )
    }
}



