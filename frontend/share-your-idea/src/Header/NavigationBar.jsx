import React from 'react';
import {Link} from 'react-router-dom';
import {makeStyles} from '@material-ui/styles';
import Breadcrumbs from '@material-ui/core/Breadcrumbs';


const useStyles = makeStyles((theme) => ({
    typographyStyles: {
        flex: 1
    }
}));

const NavigationBar = () => {
    const cssClasses = useStyles()
    return (
        <Breadcrumbs aria-label="breadcrumb">
            <Link to="/">Share your Idea</Link>
            <Link to="/meeting/">Meeting</Link>
            <Link to="/search/">Search</Link>
        </Breadcrumbs>
    );
}

export default NavigationBar;