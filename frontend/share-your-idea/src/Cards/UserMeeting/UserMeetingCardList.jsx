import React from 'react';
import UserMeetingCard from "./UserMeetingCard";

const UserMeetingCardList = (props) => {
    return (
        <div>
            {props.userMeetingList.length > 0 && props.userMeetingList.map((userMeeting, index) => <UserMeetingCard key={index} userMeeting={userMeeting}/>)}
        </div>
    );
}

export default UserMeetingCardList;
