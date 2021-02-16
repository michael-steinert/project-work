import React, {useEffect, useState} from 'react';
import {makeStyles} from "@material-ui/styles";
import {fetchAllMeetings} from "../Forms/client/client";
import UserMeetingCardList from "../Cards/UserMeeting/UserMeetingCardList";

const useStyles = makeStyles((theme) => ({
    root: {
        margin: 5,
    }
}));

const Meeting = () => {
    const classes = useStyles();
    const [userMeetingList, setUserMeetingList] = useState([]);

    let userEntity = JSON.parse(localStorage.getItem('userEntity'));

    useEffect(() => {
        fetchAllMeetings().then(data => {
            setUserMeetingList(data);
            console.log(data);
        });
    }, []);

    return (
        <div className={classes.root}>
            <h1>Meeting</h1>
            {userEntity && <h4>Sie sind angemeldet, daher können Sie diesen Inhalt sehen!</h4>}
            {!userEntity && <h4>Sie sind nicht angemeldet, daher können Sie keinen Inhalt sehen!</h4>}
            
            {userMeetingList && <UserMeetingCardList userMeetingList = {userMeetingList} />}

            {!userMeetingList && <h5>Es wurden keine UserMeetings gefunden!</h5>}
        </div>
    );
}

export default Meeting;
