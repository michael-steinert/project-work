import axios from "axios";

const api = axios.create({
    baseURL: 'http://localhost:8080/api/v1/order',
    headers: {
        'Content-Type': 'application/json'
    }
});

export const getAllOrders = async () => {
    return await api.get('/orders').then(response => response.data).catch((error) => {
        console.log("Error:", error);
    });
};

export const getOrderById = async (orderUid) => {
    return await api.get('/' + orderUid).then(response => response.data).catch((error) => {
        console.log("Error:", error);
    });
};

export const createNewOrder = async (order) => {
    return await api.post( '/', order).then(response => response.data).catch((error) => {
        console.log("Error:", error);
    });
}

export const deleteOrderById = async (orderUid) => {
    return await api.delete('/' + orderUid).then(response => response.data).catch((error) => {
        console.log("Error:", error);
    });
};