import React, {useState} from 'react';
import {makeStyles} from "@material-ui/styles";

const useStyles = makeStyles((theme) => ({
    root: {
        margin: 5,
    }
}));

const Meeting = () => {
    const classes = useStyles();
    return (
        <div className={classes.root}>
            <h1>Meeting</h1>
        </div>
    );
}

export default Meeting;
