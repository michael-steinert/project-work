import React, {useEffect, useState} from 'react';
import {makeStyles} from '@material-ui/styles';
import {fetchAllMeetings} from '../Forms/client/client';
import UserMeetingCardList from '../Cards/UserMeeting/UserMeetingCardList';
import Button from '@material-ui/core/Button';
import Modal from 'react-modal';
import UserMeetingForm from '../Forms/UserMeeting/UserMeetingForm';
import {useSelector} from 'react-redux';
import Typography from '@material-ui/core/Typography';


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
            <Typography variant="body1">Meeting</Typography>
            {authentication && <Typography variant="body2">Sie sind angemeldet, daher können Sie diesen Inhalt sehen!</Typography>}
            {!authentication && <Typography variant="body2">Sie sind nicht angemeldet, daher können Sie keinen Inhalt sehen!</Typography>}

            {userMeetingList && <UserMeetingCardList userMeetingList = {userMeetingList} />}
            {!userMeetingList && <Typography variant="body2">Es wurden keine UserMeetings gefunden!</Typography>}

            <Button variant="outlined" color="inherit" onClick={() => setModalIsOpen(true)}>UserMeeting erstellen</Button>
            <Modal isOpen={modalIsOpen} onRequestClose={() => setModalIsOpen(false)} ariaHideApp={false}>
                <Typography variant="body2">Erstellung eines UserMeetings</Typography>
                <Typography variant="subtitle1">Bitte tätigen Sie folgende Einagben, um ein UserMeeting zu erstellen!</Typography>
                <UserMeetingForm setModalIsOpen={setModalIsOpen}/>
                <br/>
                <Button variant="outlined" color="inherit" onClick={() => setModalIsOpen(false)}>Erstellung abschließen</Button>
            </Modal>
        </div>
    );
}

export default Meeting;
