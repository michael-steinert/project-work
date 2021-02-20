import React from 'react';
import UserCard from "./UserCard";

const UserCardList = (props) => {
    return (
        <div>
            {props.userList.length > 0 && props.userList.map((user, index) => <UserCard key={index} user={user}/>)}
        </div>
    );
}

export default UserCardList;
