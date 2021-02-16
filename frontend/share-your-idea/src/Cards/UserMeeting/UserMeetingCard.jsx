import React, {useState} from 'react';
import {makeStyles} from "@material-ui/styles";
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';

const useStyles = makeStyles((theme) => ({
    root: {
        margin: 5,
    }
}));

const UserMeetingCard = (props) => {
    const classes = useStyles();

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
                <Button size="small">Teilnehmen</Button>
            </CardActions>
        </Card>
    );
}

export default UserMeetingCard;
