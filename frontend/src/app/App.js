import React, { Component } from 'react';
import {
    Route,
    Switch
} from 'react-router-dom';
import './App.css';
import {getCurrentUser} from "../util/Api";
import {ACCESS_TOKEN} from "../constants";
import Alert from 'react-s-alert';
import LoadingIndicator from "../common/LoadingIndicator";
import AppHeader from "../common/AppHeader";
import RedirectHandler from "../user/oauth2/RedirectHandler";
import Login from "../user/login/Login";

class App extends Component {

    constructor(props) {
        super(props)
        this.state = {
            authenticated: false,
            currentUser: null,
            loading: false
        }
        this.loadLoggedInUser = this.loadLoggedInUser.bind(this);
        this.handleLogout = this.handleLogout.bind(this);
    }

    loadLoggedInUser() {
        this.setState({
            loading: true
        });

        getCurrentUser()
            .then(response => {
                this.setState({
                    currentUser: response,
                    authenticated: true,
                    loading: false
                })
            })
            .catch(error => {
                this.setState({
                    loading: false
                })
            })
    }

    handleLogout() {
        localStorage.removeItem(ACCESS_TOKEN);
        this.setState({
            authenticated: false,
            currentUser: null
        });
        Alert.success("You are safely logged out");
    }

    state = {}

    componentDidMount() {
        this.loadLoggedInUser();
    }

    hello = () => {
        fetch('/api/hello')
            .then(response => response.text())
            .then(message => {
                this.setState({message: message})
            })
    }


    render() {
        if (this.state.loading) {
            return <LoadingIndicator />
        }

        return (
            <div className="app">
                <div className="app-top-box">
                    <AppHeader authenticated={this.state.authenticated} onLogout={this.handleLogout}/>
                </div>
                <div className="app-body">
                    <Switch>
                        <Route path="/" component={Login}></Route>

                        <Route path="/oauth2/redirect" component={RedirectHandler}></Route>
                    </Switch>
                </div>
            </div>
        );
  }
}

export default App;
