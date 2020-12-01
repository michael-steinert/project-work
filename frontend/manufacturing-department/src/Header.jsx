import React from "react";
import {AppBar, Toolbar, Typography} from "@material-ui/core";
import {makeStyles} from "@material-ui/styles";
import AllInclusiveIcon from '@material-ui/icons/AllInclusive';

const useStyles = makeStyles(() => ({
    typographyStyles: {
        flex: 1
    }
}));

const Header = () => {
    const cssClasses = useStyles()
    return (
        <AppBar position="static">
            <Toolbar>
                <Typography className={cssClasses.typographyStyles}>
                    Manufacturing Department
                </Typography>
                <AllInclusiveIcon/>
            </Toolbar>
        </AppBar>
    );
}

export default Header;