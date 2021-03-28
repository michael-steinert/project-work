import React from 'react';
import UserCard from './UserCard';
import Typography from '@material-ui/core/Typography';

const UserCardList = (props) => {
    return (
        <div>
            {props.userList.length > 0 && props.userList.map((user, index) => <UserCard key={index} user={user}/>)}
            {props.userList.length === 0 && <Typography variant="body2">Kein User gefunden</Typography>}
        </div>
    );
}

export default UserCardList;
