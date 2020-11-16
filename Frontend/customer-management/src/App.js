import React, {Component} from 'react';
import './App.css';
import axios from 'axios';
import {Alert, AlertTitle} from '@material-ui/lab';

class App extends Component {

    state = {
        customers: [],
        isFetching: false
    }

    constructor() {
        super();
        this.getCustomers();
    }

    getCustomers = async () => {
        await api.get('/').then(res => this.setState({customers: res.data})).catch(e => console.log(e))
    }

    setCustomer = async () => {
        await api.post('/', {
            firstName: "Marie",
            lastName: "Schmdit",
            gender: "FEMALE",
            email: "marie-schmidt@gmx.de",
            street: "Schmidtstraße",
            houseNumber: "4a",
            zipCode: 58720,
            city: "Menden"
        }).catch(e => console.log(e))
        this.getCustomers()
    }

    deleteCustomer = async (customerUid) => {
        await api.delete(`/${customerUid}`).catch(e => console.log(e))
    }

    updateCustomer = async (customerUid, val) => {
        await api.patch(`/$customerUid`, {firstName: val}).catch(e => console.log(e))
    }
    render() {
        return (
            <div className="App">
                <h1>Customer Management</h1>
                {this.state.customers.map(customer =>
                    <div key={customer.userUid}>
                        {customer.customerUid}, {customer.city}
                        <br/>
                        <button onClick={()=>this.deleteCustomer(customer.customerUid)}>Delete Customer</button>
                    </div>
                )}
                <br/>
                <button onClick={()=>this.setCustomer()}>Set Customer</button>
            </div>
        )
    }

}

const api = axios.create({
    baseURL: `http://localhost:8080/api/v1/customers`,
    headers: {
        'Content-Type': 'application/json'
    }
});

export default App;
