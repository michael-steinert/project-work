import React from 'react';
import {makeStyles} from "@material-ui/styles";
import UserMeetingCard from "./UserMeetingCard";

const useStyles = makeStyles((theme) => ({
    root: {
        margin: 5,
    }
}));


const UserMeetingCardList = (props) => {
    const classes = useStyles();

    return (
        <div className={classes.root}>
            {props.userMeetingList &&
                props.userMeetingList.map((userMeeting, index) => <UserMeetingCard key={index} userMeeting={userMeeting}/>)}
        </div>
    );
}

export default UserMeetingCardList;
