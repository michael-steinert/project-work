import React from 'react';
import {registerUser} from "../client/client";
import Button from '@material-ui/core/Button';

const emptyForm = {
    formSubmitted: false,
    username: '',
    password: '',
    registeredUserFeedback: null
};

class RegisterForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = emptyForm;

        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleReset = this.handleReset.bind(this);
    }

    handleSubmit() {
        console.log(`Sending Registration to User-Registration API: Username: ${this.state.username}, Password: ${this.state.password}`);

        this.setState({
            formSubmitted: true
        });

        const userEntity = {
            username: this.state.username,
            password: this.state.password
        }

        registerUser(userEntity).then(data => this.setState({registeredUserFeedback: data}));
    }

    handleChange(event) {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    handleReset() {
        this.setState(emptyForm);
    }

    render() {
        const { classes } = this.props;

        if (this.state.formSubmitted) {
            return (
                <div>
                    <p>Formular erfolgreich versandt!</p>
                    <Button variant="outlined" color="inherit" onClick={this.handleReset}>Neues Formular ausfüllen</Button>
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
                    <Button variant="outlined" color="inherit" onClick={this.handleSubmit}>Registrieren</Button>
                </form>
            </div>
        );
    }
}

export default RegisterForm;
