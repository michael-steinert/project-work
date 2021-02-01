import React, {useState} from 'react';
import {AppBar, Toolbar, Typography} from '@material-ui/core';
import {makeStyles} from '@material-ui/styles';
import Button from '@material-ui/core/Button';
import Modal from 'react-modal';
import LoginForm from '../Forms/Login/LoginForm';
import RegisterForm from '../Forms/Register/RegisterForm';

const useStyles = makeStyles((theme) => ({
    root: {
        flexGrow: 1,
    },
    typographyStyles: {
        flex: 1
    }
}));

const HeaderBar = () => {
    const classes = useStyles();
    const [auth, setAuth] = useState(true);
    const [modalIsOpen, setModalIsOpen] = useState(false);

    return (
        <div className={classes.root}>
            <AppBar position="static">
                <Toolbar>
                    <Typography className={classes.typographyStyles}>Share your Idea!</Typography>
                    <Button variant="outlined" color="inherit" onClick={() => setModalIsOpen(true)}>Login</Button>
                    <Modal isOpen={modalIsOpen} onRequestClose={() => setModalIsOpen(false)}>
                        <h2>Anmeldung</h2>
                        <p>Bitte melden Sie sich an, um die Plattform zu nutzen!</p>
                        <LoginForm setModalIsOpen={setModalIsOpen}/>
                        <br/>
                        <h2>Registrierung</h2>
                        <p>Bitte registrieren Sie sich an, um die Plattform zu nutzen!</p>
                        <RegisterForm setModalIsOpen={setModalIsOpen}/>
                        <br/>
                        <Button variant="outlined" color="inherit" onClick={() => setModalIsOpen(false)}>Anmeldung-/Registrierung schließen</Button>
                    </Modal>
                </Toolbar>
            </AppBar>
        </div>
    );
}

export default HeaderBar;