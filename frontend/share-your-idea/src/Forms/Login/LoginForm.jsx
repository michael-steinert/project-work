import React from 'react';
import {authenticateUser} from "../client/client";
import Button from '@material-ui/core/Button';
import {useDispatch, useSelector} from "react-redux";
import {success, fail} from "./redux/authenticationSlice";

const emptyForm = {
    formSubmitted: false,
    username: '',
    password: '',
    token: ''
};

const {count} = useSelector((state) => state.counter);
const dispatch = useDispatch();

class LoginForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = emptyForm;

        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleReset = this.handleReset.bind(this);
    }

    handleSubmit() {
        console.log(`Sending Login to User-Registration API: Username: ${this.state.username}, Password: ${this.state.password}`);

        this.setState({
            formSubmitted: true
        });

        const userEntity = {
            username: this.state.username,
            password: this.state.password
        }

        authenticateUser(userEntity).then(data => {
            this.setState({token: data});
        });
    }

    handleChange(event) {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    handleReset() {
        this.props.setModalIsOpen(false);
    }

    render() {
        if (this.state.formSubmitted) {
            return (
                <div>
                    <p>Anmeldung erfolgreich versandt!</p>
                    <Button variant="outlined" color="inherit" onClick={this.handleReset}>Anmeldung schließen</Button>
                </div>
            );
        }

        return (
            <div>
                <form onSubmit={this.handleSubmit}>
                    <label htmlFor='username'>Nutzername:</label>
                    <br/>
                    <input id='username' name='username' type='text' value={this.state.username} onChange={this.handleChange}/>
                    <br/>
                    <label htmlFor='password'>Kennwort: </label>
                    <br/>
                    <input id='password' name='password' type='password' value={this.state.password} onChange={this.handleChange}/>
                    <br/>
                    <Button variant="outlined" color="inherit" onClick={this.handleSubmit}>Anmelden</Button>
                </form>
            </div>
        );
    }
}

export default LoginForm;
