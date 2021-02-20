import React, {useEffect, useState} from 'react';
import {makeStyles} from "@material-ui/styles";
import {fetchAllMeetings} from "../Forms/client/client";
import UserMeetingCardList from "../Cards/UserMeeting/UserMeetingCardList";
import Button from "@material-ui/core/Button";
import Modal from "react-modal";
import UserMeetingForm from "../Forms/UserMeeting/UserMeetingForm";
import {useSelector} from "react-redux";


const useStyles = makeStyles((theme) => ({
    root: {
        margin: 5,
    }
}));

const Meeting = () => {
    const classes = useStyles();
    const [modalIsOpen, setModalIsOpen] = useState(false);
    const [userMeetingList, setUserMeetingList] = useState([]);
    const {authentication} = useSelector((state) => state.authentication);

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
            {authentication && <h4>Sie sind angemeldet, daher können Sie diesen Inhalt sehen!</h4>}
            {!authentication && <h4>Sie sind nicht angemeldet, daher können Sie keinen Inhalt sehen!</h4>}

            {userMeetingList && <UserMeetingCardList userMeetingList = {userMeetingList} />}
            {!userMeetingList && <h5>Es wurden keine UserMeetings gefunden!</h5>}

            <Button variant="outlined" color="inherit" onClick={() => setModalIsOpen(true)}>UserMeeting erstellen</Button>
            <Modal isOpen={modalIsOpen} onRequestClose={() => setModalIsOpen(false)} ariaHideApp={false}>
                <h2>Erstellung eines UserMeetings</h2>
                <p>Bitte tätigen Sie folgende Einagben, um ein UserMeeting zu erstellen!</p>
                <UserMeetingForm setModalIsOpen={setModalIsOpen}/>
                <br/>
                <Button variant="outlined" color="inherit" onClick={() => setModalIsOpen(false)}>Erstellung abschließen</Button>
            </Modal>
        </div>
    );
}

export default Meeting;
