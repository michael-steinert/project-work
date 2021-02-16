import React, {useState} from 'react';
import {makeStyles} from "@material-ui/styles";

const useStyles = makeStyles((theme) => ({
    root: {
        margin: 5,
    }
}));

/*
onRemoveUser() {
    this.props.onRemoveUser(this.props.currentUser);
}

*/

const UserCard = () => {
    //this.state = { users:[] };
    const classes = useStyles();

    return (
        <div className={classes.root}>
            {/*
            <div className="card">
                <Button onClick = {this.onRemoveUser.this(bind)}>Delete</Button>
            </div>
            */}

        </div>
    );
}

export default UserCard;
