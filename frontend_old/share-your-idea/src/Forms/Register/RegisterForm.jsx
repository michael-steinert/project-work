import React, {useState} from 'react';
import {registerUser} from '../client/client';
import Button from '@material-ui/core/Button';
import {makeStyles} from '@material-ui/core/styles';
import {Typography} from '@material-ui/core';
import Grid from '@material-ui/core/Grid';
import Paper from '@material-ui/core/Paper';

const useStyles = makeStyles((theme) => ({
    root: {
        flexGrow: 1,
    },
    paper: {
        padding: theme.spacing(2),
        textAlign: 'center',
        color: theme.palette.text.secondary,
    }
}));

const RegisterForm = (props) => {
    const classes = useStyles();
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [formSubmitted, setFormSubmitted] = useState(false);

    function handleSubmit() {
        console.log(`Sending Registration to User-Registration API: Username: ${username}, Password: ${password}`);

        setFormSubmitted(true);

        let userEntity = {
            username: username,
            password: password
        }

        registerUser(userEntity).then(data => {
            localStorage.setItem('newUserEntity', JSON.stringify(data));
        });
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
            <Grid container spacing={3}>
                <Grid item xs={12}>
                    <Paper className={classes.paper}>
                        <Typography variant="body2">Registrierung</Typography>
                        <Typography variant="subtitle1">Bitte registrieren Sie sich an, um die Plattform zu nutzen!</Typography>
                        {formSubmitted &&
                        <div>
                            <Typography variant="subtitle1">Registrierung erfolgreich versandt!</Typography>
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
                                <Button variant="outlined" color="inherit" onClick={handleSubmit}>Registrieren</Button>
                            </form>
                        </div>
                        }
                    </Paper>
                </Grid>
            </Grid>
        </div>
    );
}

export default RegisterForm;
