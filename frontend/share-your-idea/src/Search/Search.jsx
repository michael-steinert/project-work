import React, {useState} from 'react';
import {makeStyles} from "@material-ui/styles";
//import {without} from 'lodash';

const useStyles = makeStyles((theme) => ({
    root: {
        margin: 5,
    }
}));

/*
removeUser(user) {
    const users = without(this.state.users, user);
    this.setState({users: users})
}

*/

const Search = () => {
    //this.state = { users:[] };
    const classes = useStyles();
    let userEntity = JSON.parse(localStorage.getItem('userEntity'));

    return (
        <div className={classes.root}>
            <h1>Search</h1>
            {userEntity &&
            <div>Sie sind angemeldet, daher können Sie diesen Inhalt sehen!

                {/*<UserCardList users = {currentUsers} handleRemoveUser = {this.removeUser.bind(this)}/> */}


            </div>
            }
            {!userEntity && <div>Sie sind nicht angemeldet, daher können Sie keinen Inhalt sehen!</div>}
        </div>
    );
}

export default Search;
