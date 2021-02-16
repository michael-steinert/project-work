import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:8080',
    headers: {
        'Content-Type': 'application/json'
    }
});

export const registerUser = async (userEntity) => {
    return await api.post('/user-management/register', userEntity).then(response => response.data).catch((error) => {
        console.log("Error:", error);
    });
}

export const authenticateUser = async (userEntity) => {
    return await api.post('/user-management/authenticate', userEntity).then(response => response.data).catch((error) => {
        console.log("Error:", error);
    });
}

/*
export const searchUser = async (userEntity) => {
    let userEntityFromLocalStorage = JSON.parse(sessionStorage.getItem('userEntity'));
    let authorization_token = userEntityFromLocalStorage.authorization_token;
    return await api.post('/user-search', userEntity, { headers: {"Authorization" : `Bearer ${authorization_token}`} }).then(response => response.data).catch((error) => {
        console.log("Error:", error);
    });
}
 */

export const fetchAllMeetings = async () => {
    return await api.get('/user-meeting/fetch-all-user-meetings').then(response => response.data).catch((error) => {
        console.log("Error:", error);
    });
}
