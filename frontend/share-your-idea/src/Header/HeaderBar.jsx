import React, {useState} from 'react';
import {AppBar, Toolbar, Typography} from '@material-ui/core';
import {makeStyles} from '@material-ui/core/styles';
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
    const [authentication, setAuthentication] = useState(false);
    const [modalIsOpen, setModalIsOpen] = useState(false);
    let userEntity = JSON.parse(localStorage.getItem('userEntity'));

    function handleLogout() {
        localStorage.clear();
        window.location.reload();
    }

    return (
        <div className={classes.root}>
            <AppBar position="static">
                <Toolbar>
                    <Typography className={classes.typographyStyles}>Share your Idea!</Typography>
                    {userEntity && <Typography className={classes.typographyStyles}>Sie sind angemeldet als {userEntity.username}</Typography>}
                    {!userEntity && <Button variant="outlined" color="inherit" onClick={() => setModalIsOpen(true)}>Anmelden</Button>}
                    <Modal isOpen={modalIsOpen} onRequestClose={() => setModalIsOpen(false)} ariaHideApp={false}>
                        <Typography variant="body2">Anmeldung</Typography>
                        <Typography variant="subtitle1">Bitte melden Sie sich an, um die Plattform zu nutzen!</Typography>
                        <LoginForm setModalIsOpen={setModalIsOpen}/>
                        <br/>
                        <Typography variant="body2">Registrierung</Typography>
                        <Typography variant="subtitle1">Bitte registrieren Sie sich an, um die Plattform zu nutzen!</Typography>
                        <RegisterForm setModalIsOpen={setModalIsOpen}/>
                        <br/>
                        <Button variant="outlined" color="inherit" onClick={() => setModalIsOpen(false)}>Anmeldung-/Registrierung abschließen</Button>
                    </Modal>
                    {userEntity && <Button variant="outlined" color="inherit" onClick={handleLogout}>Abmelden</Button>}
                </Toolbar>
            </AppBar>
        </div>
    );
}

export default HeaderBar;