import React, {useState} from 'react';
import {AppBar, Toolbar, Typography} from '@material-ui/core';
import {makeStyles} from '@material-ui/styles';
import Button from '@material-ui/core/Button';
import Modal from 'react-modal';

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
    const [auth, setAuth] = React.useState(true);
    const [modalIsOpen, setModalIsOpen] = useState(false);

    return (
        <div className={classes.root}>
            <AppBar position="static">
                <Toolbar>
                    <Typography className={classes.typographyStyles}>
                        Share your Idea!
                    </Typography>
                    <Button color="inherit" onClick={() => setModalIsOpen(true)}>Login</Button>
                    <Modal isOpen={modalIsOpen} onRequestClose={() => setModalIsOpen(false)}>
                        <h2>Anmelde-Maske</h2>
                        <p>Bitte melden Sie sich an, um die Plattform zu nutzen!</p>
                        <div>
                            <Button onClick={() => setModalIsOpen(false)}>Anmelden</Button>
                        </div>
                    </Modal>
                </Toolbar>
            </AppBar>
        </div>
    );
}

export default HeaderBar;