import axios from "axios";

const api = axios.create({
    baseURL: 'http://localhost:8080/api/v1/bike',
    headers: {
        'Content-Type': 'application/json'
    }
});

export const getAllBikes = async () => {
    return await api.get('/bikes').then(res => res.data).catch((err) => {
        console.log("Error:", err);
    });
};

export const getBikeById = async (bikeUid) => {
    return await api.get('/' + bikeUid).then(res => res.data).catch((err) => {
        console.log("Error:", err);
    });
};

export const createNewBike = async (bike) => {
    return await api.post( '/', bike).then(res => res.data).catch((err) => {
        console.log("Error:", err);
    });
}

export const deleteBikeById = async (bikeUid) => {
    return await api.delete('/' + bikeUid).then(res => res.data).catch((err) => {
        console.log("Error:", err);
    });
};