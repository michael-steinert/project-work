import React, {useState} from 'react';
import {makeStyles} from '@material-ui/core/styles';
import Typography from '@material-ui/core/Typography';

const useStyles = makeStyles((theme) => ({
    root: {
        margin: 5,
    }
}));

const Homepage = () => {
    const classes = useStyles();
    return (
        <div className={classes.root}>
            <Typography variant="body2">Homepage</Typography>
        </div>
    );
}

export default Homepage;
