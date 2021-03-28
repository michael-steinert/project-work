import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:8080/api/v1',
    headers: {
        'Content-Type': 'application/json'
    }
});

/*
    The async and await Keywords makes the Function wait until the Promise returns a Result,
    and the Function returns a Promise
*/

/* Queries for Microservice UserManagement */
export const registerUser = async (userEntity) => {
    try {
        return await api.post('/user-management/register-a-new-user', userEntity)
            .then(response => response.data).catch((error) => console.log(`Error while RegisterUser: ${error}`));
    } catch (error) {
        console.log(`Error while RegisterUser: ${error}`);
    }
}

export const authenticateUser = async (userEntity) => {
    try {
        return await api.post('/user-management/authenticate-an-existing-user', userEntity)
            .then(response => response.data).catch((error) => console.log(`Error while AuthenticateUser: ${error}`));
    } catch (error) {
        console.log(`Error while RegisterUser: ${error}`);
    }
}

/*
export const searchUser = async (userEntity) => {
    let userEntityFromLocalStorage = JSON.parse(sessionStorage.getItem('userEntity'));
    let authorization_token = userEntityFromLocalStorage.authorization_token;
    return await api.post('/user-search', userEntity, { headers: {"Authorization" : `Bearer ${authorization_token}`} })
    .then(response => response.data).catch((error) => console.log("Error:", error));
}
 */

/* Queries for Microservice UserMeeting */
export const registerUserMeeting = async (userMeetingEntity) => {
    try {
        return await api.post('/user-meeting/register-meeting', userMeetingEntity)
            .then(response => response.data).catch((error) => console.log(`Error while RegisterUserMeeting: ${error}`));
    } catch (error) {
        console.log(`Error while RegisterUser: ${error}`);
    }
}

export const fetchAllMeetings = async () => {
    try {
        return await api.get('/user-meeting/find-all-user-meetings')
            .then(response => response.data).catch((error) => console.log(`Error while FetchAllMeetings: ${error}`));
    } catch (error) {
        console.log(`Error while RegisterUser: ${error}`);
    }
}

export const registerUserToUserMeeting = async (meetingName, userEntity) => {
    try {
        return await api.post('/user-meeting/register-to-meeting/' + meetingName, userEntity)
            .then(response => response.data).catch((error) => console.log(`Error while RegisterUserToUserMeeting: ${error}`));
    } catch (error) {
        console.log(`Error while RegisterUser: ${error}`);
    }
}

export const unregisterUserToUserMeeting = async (meetingName, userEntity) => {
    try {
        return await api.post('/user-meeting/unregister-from-meeting/' + meetingName, userEntity)
            .then(response => response.data).catch((error) => console.log(`Error while UnregisterUserToUserMeeting: ${error}`));
    } catch (error) {
        console.log(`Error while RegisterUser: ${error}`);
    }
}

/* Queries for Microservice UserMeetingSearch */
export const searchUserWithSearchQuery = async (searchQuery) => {
    try {
        return await api.get('/user-meeting-search/search-user/' + searchQuery)
            .then(response => response.data).catch((error) => console.log(`Error while SearchUserWithSearchQuery: ${error}`));
    } catch (error) {
        console.log(`Error while RegisterUser: ${error}`);
    }
}

export const searchUserMeetingWithSearchQuery = async (searchQuery) => {
    try {
        return await api.get('/user-meeting-search/search-meeting/' + searchQuery)
            .then(response => response.data).catch((error) => console.log(`Error while SearchUserMeetingWithSearchQuery: ${error}`));
    } catch (error) {
        console.log(`Error while RegisterUser: ${error}`);
    }
}