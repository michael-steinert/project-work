import axios from "axios";

const api = axios.create({
    baseURL: 'http://localhost:8080/api/v1/customer',
    headers: {
        'Content-Type': 'application/json'
    }
});

export const getAllCustomers = async () => {
    return await api.get('/customers').then(response => response.data).catch((error) => {
        console.log("Error:", error);
    });
};

export const getCustomerById = async (customerUid) => {
    return await api.get('/' + customerUid).then(response => response.data).catch((error) => {
        console.log("Error:", error);
    });
};

export const createNewCustomer = async (customer) => {
    return await api.post( '/', customer).then(response => response.data).catch((error) => {
        console.log("Error:", error);
    });
}

export const deleteCustomerById = async (customerUid) => {
    return await api.delete('/' + customerUid).then(response => response.data).catch((error) => {
        console.log("Error:", error);
    });
};