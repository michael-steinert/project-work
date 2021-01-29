import React from 'react';
import {AppBar, Toolbar, Typography} from '@material-ui/core';
import {makeStyles} from '@material-ui/styles';

const useStyles = makeStyles((theme) => ({
    typographyStyles: {
        flex: 1
    }
}));

const HeaderBar = () => {
    const cssClasses = useStyles()
    return (
        <AppBar position="static">
            <Toolbar>
                <Typography className={cssClasses.typographyStyles}>
                    Share your Idea!
                </Typography>
            </Toolbar>
        </AppBar>
    );
}

export default HeaderBar;