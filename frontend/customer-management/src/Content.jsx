import React from "react";
import {Grid} from "@material-ui/core";
import CustomerList from "./CustomerList";


const Content = () => {
    return (
        <Grid container spacing={4}>
            <Grid item xs={12} sm={4}>
                <CustomerList/>
            </Grid>
        </Grid>
    );
}

export default Content;