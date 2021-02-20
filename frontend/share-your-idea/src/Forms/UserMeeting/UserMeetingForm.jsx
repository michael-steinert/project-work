import React, {useState} from 'react';
import {registerUserMeeting} from "../client/client";
import Button from '@material-ui/core/Button';
import {useDispatch, useSelector} from "react-redux";
import {success, fail} from "../../state/authenticationSlice";
import {makeStyles} from "@material-ui/styles";

const useStyles = makeStyles((theme) => ({
    root: {
        margin: 5,
    }
}));

const UserMeetingForm = (props) => {
    const classes = useStyles();
    const [meetingName, setMeetingName] = useState('');
    const [communicationLink, setCommunicationLink] = useState('');
    const [formSubmitted, setFormSubmitted] = useState(false);

    const {authentication} = useSelector((state) => state.authentication);
    const dispatch = useDispatch();

    function handleSubmit() {
        console.log(`Sending UserMeeting to UserMeeting-Creation API: MeetingName: ${meetingName}, CommunicationLink: ${communicationLink}`);

        setFormSubmitted(true);

        let userMeetingEntity = {
            meetingName: meetingName,
            communicationLink: communicationLink
        }

        registerUserMeeting(userMeetingEntity).then(data => {
            console.log('Successful created the following UserMeeting');
            console.log(data);
        });
    }

    function handleChangeMeetingName(event) {
        setMeetingName(event.target.value);
    }

    function handleChangeCommunicationLink(event) {
        setCommunicationLink(event.target.value);
    }

    function handleReset() {
        props.setModalIsOpen(false);
    }

    return (
        <div className={classes.root}>
            {formSubmitted &&
            <div>
                <p>UserMeeting erfolgreich erstellt!</p>
                <Button variant="outlined" color="inherit" onClick={handleReset}>Erstellung abschließen</Button>
            </div>
            }
            {!formSubmitted &&
            <div>
                <form onSubmit={handleSubmit}>
                    <label htmlFor='meetingName'>Meeting-Name:</label>
                    <br/>
                    <input id='meetingName' name='meetingName' type='text' value={meetingName} onChange={handleChangeMeetingName}/>
                    <br/>
                    <label htmlFor='communicationLink'>Kommunikations-Link: </label>
                    <br/>
                    <input id='communicationLink' name='communicationLink' type='text' value={communicationLink} onChange={handleChangeCommunicationLink}/>
                    <br/>
                    <Button variant="outlined" color="inherit" onClick={handleSubmit}>Erstellen</Button>
                </form>
            </div>
            }
        </div>
    );
}

export default UserMeetingForm;
