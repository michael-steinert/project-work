import React, {useState} from 'react';
import {makeStyles} from "@material-ui/styles";

const useStyles = makeStyles((theme) => ({
    root: {
        margin: 5,
    }
}));

/*
onRemoveUser(user) {
    this.props.handleRemoveUser(user)

}

*/

const UserCardList = () => {
    //this.state = { users:[] };
    const classes = useStyles();

    return (
        <div className={classes.root}>
            {/*
            const cards = this.props.users.map((user, index) => {
            return <UserCard key = {index} user = {user} onRemoveUser = {this.onRemoveUser.bind(this)} currentUser = {user} />
            });

            */}

        </div>
    );
}

export default UserCardList;
