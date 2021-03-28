import React, {useState} from 'react';
import {AppBar, Toolbar, Typography} from '@material-ui/core';
import {makeStyles} from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import Modal from 'react-modal';
import LoginForm from '../Forms/Login/LoginForm';
import RegisterForm from '../Forms/Register/RegisterForm';
import Grid from '@material-ui/core/Grid';

const useStyles = makeStyles((theme) => ({
    root: {
        flexGrow: 1,
    },
    typographyStyles: {
        flex: 1
    },
    button: {
        padding: theme.spacing(2),
        justifyContent: 'center',
        color: theme.palette.text.secondary,
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
                        <LoginForm setModalIsOpen={setModalIsOpen}/>
                        <br/>
                        <RegisterForm setModalIsOpen={setModalIsOpen}/>
                        <br/>
                        <Grid container className={classes.button} >
                            <Button variant="outlined" color="inherit" onClick={() => setModalIsOpen(false)}>Anmeldung-/Registrierung abschließen</Button>
                        </Grid>
                    </Modal>
                    {userEntity && <Button variant="outlined" color="inherit" onClick={handleLogout}>Abmelden</Button>}
                </Toolbar>
            </AppBar>
        </div>
    );
}

export default HeaderBar;