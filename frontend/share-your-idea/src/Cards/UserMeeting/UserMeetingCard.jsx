import React, {useState} from 'react';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import {registerUserToUserMeeting, unregisterUserToUserMeeting} from '../../Forms/client/client';
import {useDispatch, useSelector} from 'react-redux';
import {join, leave} from "../../state/userMeetingSlice";

const UserMeetingCard = (props) => {
    const [userMeetingSubmitted, setUserMeetingSubmitted] = useState(false);
    const {joinedUserMeeting, userMeetingName} = useSelector((state) => state.joinUserMeeting);
    const dispatch = useDispatch();

    function handleJoinUserMeeting() {
        console.log('Setting User to the following UserMeeting');
        console.log(props.userMeeting);

        setUserMeetingSubmitted(true);

        let userEntity = JSON.parse(localStorage.getItem('userEntity'));

        registerUserToUserMeeting(props.userMeeting.meetingName, userEntity).then(data => {
            console.log('The following User was set to the UserMeeting:');
            console.log(data);
        });

        dispatch(join(props.userMeeting.meetingName));
    }

    function handleLeaveUserMeeting() {
        console.log('Unsetting User to the following UserMeeting');
        console.log(props.userMeeting);

        setUserMeetingSubmitted(false);

        let userEntity = JSON.parse(localStorage.getItem('userEntity'));

        unregisterUserToUserMeeting(props.userMeeting.meetingName, userEntity).then(data => {
            console.log('The following User was unset to the UserMeeting:');
            console.log(data);
        });

        dispatch(leave());
    }

    return (
        <Card>
            <CardContent>
                <Typography variant="h5" component="h2">
                    {props.userMeeting.meetingName}
                </Typography>
                <Typography variant="body2" component="p">
                    {props.userMeeting.communicationLink}
                </Typography>
            </CardContent>
            <CardActions>
                {!userMeetingSubmitted && <Button variant="outlined" color="inherit" onClick={handleJoinUserMeeting} disabled={joinedUserMeeting}>Teilnehmen</Button>}
                {userMeetingSubmitted || (props.userMeeting.meetingName === userMeetingName) && <Button variant="outlined" color="inherit" onClick={handleLeaveUserMeeting}>Verlassen</Button>}
            </CardActions>
        </Card>
    );
}

export default UserMeetingCard;
