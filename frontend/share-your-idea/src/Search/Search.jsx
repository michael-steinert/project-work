import React, {useState} from 'react';
import {makeStyles} from '@material-ui/core/styles';
import {searchUserWithSearchQuery} from '../Forms/client/client';
import {useSelector} from 'react-redux';
import Button from '@material-ui/core/Button';
import Grid from '@material-ui/core/Grid';
import Typography from '@material-ui/core/Typography';
import Paper from '@material-ui/core/Paper';
import UserMeetingCardList from '../Cards/UserMeeting/UserMeetingCardList';
import UserCardList from '../Cards/User/UserCardList';

const useStyles = makeStyles((theme) => ({
    root: {
        flexGrow: 1,
    },
    paper: {
        padding: theme.spacing(2),
        textAlign: 'center',
        color: theme.palette.text.secondary,
    }
}));

const Search = () => {
    const classes = useStyles();
    const [userSearchQueryUser, setUserSearchQueryUser] = useState('');
    const [userMeetingSearchQueryUser, setUserMeetingSearchQueryUser] = useState('');
    const [userList, setUserList] = useState([]);
    const [userMeetingList, setUserMeetingList] = useState([]);
    const {authentication} = useSelector((state) => state.authentication);

    function handleSubmitUserSearchQuery() {
        searchUserWithSearchQuery(userSearchQueryUser).then(data => {
            setUserList(data);
            console.log('The Search-API returns for User: ');
            console.log(data);
        });
    }

    function handleSubmitUserMeetingSearch() {
        searchUserWithSearchQuery(userMeetingSearchQueryUser).then(data => {
            setUserMeetingList(data);
            console.log('The Search-API returns for UserMeetings: ');
            console.log(data);
        });
    }

    function handleChangeUserSearchQuery(event) {
        setUserSearchQueryUser(event.target.value);
    }

    function handleChangeUserMeetingSearchQuery(event) {
        setUserMeetingSearchQueryUser(event.target.value);
    }

    return (
        <div className={classes.root}>
            <Grid container spacing={3}>
                <Grid item xs={12}>
                    <Paper className={classes.paper}>
                        <Typography variant="body1">Search</Typography>
                        {authentication && <Typography variant="body2">Sie sind angemeldet, daher können Sie diesen Inhalt sehen!</Typography>}
                        {!authentication && <Typography variant="body2">Sie sind nicht angemeldet, daher können Sie keinen Inhalt sehen!</Typography>}
                    </Paper>
                </Grid>
                <Grid item xs={6}>
                    <Paper className={classes.paper}>
                        <h5>Suche nach User</h5>
                        <label htmlFor='userSearchQueryUser'>Suchanfrage: </label>
                        <input id='userSearchQueryUser' name='userSearchQueryUser' type='text' value={userSearchQueryUser} onChange={handleChangeUserSearchQuery}/>
                        <Button variant="outlined" color="inherit" onClick={handleSubmitUserSearchQuery}>Suchen</Button>
                    </Paper>
                    <br/>
                    <Paper className={classes.paper}>
                        {userList.length > 0 && <UserCardList userList = {userList} />}
                        {userList.length === 0 && <Typography variant="body2">Es wurden keine User gefunden oder Suche gestartet!</Typography>}
                    </Paper>
                </Grid>
                <Grid item xs={6}>
                    <Paper className={classes.paper}>
                    <h5>Suche nach UserMeetings</h5>
                    <label htmlFor='userMeetingSearchQueryUser'>Suchanfrage: </label>
                    <input id='userMeetingSearchQueryUser' name='userMeetingSearchQueryUser' type='text' value={userMeetingSearchQueryUser} onChange={handleChangeUserMeetingSearchQuery}/>
                    <Button variant="outlined" color="inherit" onClick={handleSubmitUserMeetingSearch}>Suchen</Button>
                    </Paper>
                    <br/>
                    <Paper className={classes.paper}>
                        {userMeetingList.length > 0 && <UserMeetingCardList userMeetingList = {userMeetingList} />}
                        {userMeetingList.length === 0 && <Typography variant="body2">Es wurden keine UserMeetings gefunden oder Suche gestartet!</Typography>}
                    </Paper>
                </Grid>
            </Grid>
        </div>
    );
}

export default Search;
