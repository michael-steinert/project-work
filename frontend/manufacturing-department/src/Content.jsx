import React from "react";
import {Grid} from "@material-ui/core";
import BikeList from "./BikeList";


const Content = () => {
    return (
        <Grid container spacing={4}>
            <Grid item xs={12} sm={4}>
                <BikeList/>
            </Grid>
        </Grid>
    );
}

export default Content;