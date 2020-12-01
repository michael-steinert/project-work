import React from "react";
import {Grid} from "@material-ui/core";
import OrderList from "./OrderList";


const Content = () => {
    return (
        <Grid container spacing={4}>
            <Grid item xs={12} sm={4}>
                <OrderList/>
            </Grid>
        </Grid>
    );
}

export default Content;