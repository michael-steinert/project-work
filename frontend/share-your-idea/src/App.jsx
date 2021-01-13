import React from 'react';
import {Router, Route, Link} from 'react-router-dom';
import RegisterForm from "./Forms/Register/RegisterForm";
import LoginForm from "./Forms/Login/LoginForm";
import HomePage from "./HomePage/HomePage";
import {createBrowserHistory} from "history";

const history = createBrowserHistory();

class App extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            currentUser: null
        };
    }

    render() {
        const {currentUser} = this.state;
        return (
            <Router history={history}>
                <div>
                    {currentUser &&
                    <nav className="navbar navbar-expand navbar-dark bg-dark">
                        <div className="navbar-nav">
                            <Link to="/" className="nav-item nav-link">Home</Link>
                            <a className="nav-item nav-link">Logout</a>
                        </div>
                    </nav>
                    }
                    <div className="jumbotron">
                        <div className="container">
                            <div className="row">
                                <div className="col-md-6 offset-md-3">
                                    <Route path={["/homepage"]} excat component={HomePage}/>
                                    <Route path="/login" excat component={LoginForm}/>
                                    <Route path="/register" excat component={RegisterForm}/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </Router>
        );
    }

}

export default App;
