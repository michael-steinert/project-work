import React from 'react';
import {Link} from 'react-router-dom';
import {makeStyles} from '@material-ui/core/styles';
import Breadcrumbs from '@material-ui/core/Breadcrumbs';


const useStyles = makeStyles((theme) => ({
    typographyStyles: {
        flex: 1
    }
}));

const NavigationBar = () => {
    const classes = useStyles();
    return (
        <div className={classes.root}>
            <Breadcrumbs aria-label="breadcrumb">
                <Link to="/">Share your Idea</Link>
                <Link to="/meeting/">Meeting</Link>
                <Link to="/search/">Search</Link>
            </Breadcrumbs>
        </div>
    );
}

export default NavigationBar;