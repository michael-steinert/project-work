import React, {useState} from 'react';
import {authenticateUser} from "../client/client";
import Button from '@material-ui/core/Button';
import {useDispatch, useSelector} from "react-redux";
import {success, fail} from "../../state/authenticationSlice";
import {makeStyles} from "@material-ui/styles";

const useStyles = makeStyles((theme) => ({
    root: {
        margin: 5,
    }
}));

const LoginForm = (props) => {
    const classes = useStyles();
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [token, setToken] = useState('');
    const [formSubmitted, setFormSubmitted] = useState(false);

    const {authentication} = useSelector((state) => state.authentication);
    const dispatch = useDispatch();

    function handleSubmit() {
        console.log(`Sending Login to User-Registration API: Username: ${username}, Password: ${password}`);

        setFormSubmitted(true);

        let userEntity = {
            username: username,
            password: password
        }

        authenticateUser(userEntity).then(data => {
            setToken(data);
            localStorage.setItem('userEntity', JSON.stringify(data));
            console.log(`Setting Token in Promise: Username: ${username}, Password: ${password}, Token: ${data.authorizationToken}`);
        });

        if (token !== '') {
            console.log(`Token is present: Username: ${username}, Password: ${password}, Token: ${token}`);
            dispatch(success());
        } else {
            console.log(`Token is not present: Username: ${username}, Password: ${password}, Token: ${token}`);
            dispatch(fail());
        }
    }

    function handleChangeUsername(event) {
        setUsername(event.target.value);
    }

    function handleChangePassword(event) {
        setPassword(event.target.value);
    }

    function handleReset() {
        props.setModalIsOpen(false);
    }

    return (
        <div className={classes.root}>
            {formSubmitted &&
            <div>
                <p>Anmeldung erfolgreich versandt!</p>
                <Button variant="outlined" color="inherit" onClick={handleReset}>Anmeldung schließen</Button>
            </div>
            }
            {!formSubmitted &&
            <div>
                <form onSubmit={handleSubmit}>
                    <label htmlFor='username'>Nutzername:</label>
                    <br/>
                    <input id='loginUsername' name='username' type='text' value={username} onChange={handleChangeUsername}/>
                    <br/>
                    <label htmlFor='password'>Kennwort: </label>
                    <br/>
                    <input id='loginPassword' name='password' type='password' value={password} onChange={handleChangePassword}/>
                    <br/>
                    <Button variant="outlined" color="inherit" onClick={handleSubmit}>Anmelden</Button>
                </form>
            </div>
            }
        </div>
    );
}

export default LoginForm;
