import React from 'react';
import {registerUser} from "../Client/Client";

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

        registerUser(userEntity).then(data => this.setState({registeredUserFeedback:data}));
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
        if (this.state.formSubmitted) {
            return (
                <div>
                    <p>Formular erfolgreich versandt!</p>
                    <button onClick={this.handleReset}>
                        Neues Formular ausfüllen
                    </button>
                </div>
            );
        }

        return (
            <form onSubmit={this.handleSubmit}>
                <label htmlFor='username'>
                    Nutzername: <input id='username' name='username' type='text' value={this.state.username}
                                       onChange={this.handleChange}/>
                </label>
                <label htmlFor='password'>
                    Passwort: <input id='password' name='password' type='password' value={this.state.password}
                                     onChange={this.handleChange}/>
                </label>
                <button onClick={this.handleSubmit}>Registrieren</button>
            </form>
        );
    }
}

export default RegisterForm;
