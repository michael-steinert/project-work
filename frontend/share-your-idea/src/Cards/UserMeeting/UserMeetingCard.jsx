import React, {useState} from 'react';
import {makeStyles} from "@material-ui/styles";
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import {registerUserToUserMeeting} from "../../Forms/client/client";

const useStyles = makeStyles((theme) => ({
    root: {
        margin: 5,
    }
}));



const UserMeetingCard = (props) => {
    const classes = useStyles();
    const [userMeetingSubmitted, setUserMeetingSubmitted] = useState(false);

    function handleSubmit() {
        console.log('Setting User to the following UserMeeting');
        console.log(props.userMeeting);

        setUserMeetingSubmitted(true);

        let userEntity = JSON.parse(localStorage.getItem('userEntity'));

        registerUserToUserMeeting(props.userMeeting.meetingName, userEntity).then(data => {
            console.log('The following User was set to the UserMeeting:');
            console.log(data);
        });
    }

    return (
        <Card className={classes.root}>
            <CardContent>
                <Typography variant="h5" component="h2">
                    {props.userMeeting.meetingName}
                </Typography>
                <Typography variant="body2" component="p">
                    {props.userMeeting.communicationLink}
                </Typography>
            </CardContent>
            <CardActions>
                <Button variant="outlined" color="inherit" onClick={handleSubmit} disabled={userMeetingSubmitted}>Teilnehmen</Button>
            </CardActions>
        </Card>
    );
}

export default UserMeetingCard;
