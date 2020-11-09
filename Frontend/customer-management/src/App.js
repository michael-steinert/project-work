import React, {Component} from 'react';
import './App.css';
import axios from 'axios';
import {Alert, AlertTitle} from '@material-ui/lab';

class App extends Component {

    state = {
        customers: [],
        isFetching: false
    }

    componentDidMount() {
        this.setState({
            isFetching: true
        });
        axios.get(`http://localhost:8080/api/v1/customers`)
            .then(res => res.json()
                .then(customersRes => {
                    this.setState({
                        customers: customersRes,
                        isFetching: false
                    });
                })).catch((error) => {
            const message = error.error.message;
            const status = error.error.httpStatus;
            <Alert severity="error">
                <AlertTitle>{status}</AlertTitle>
                {message}
            </Alert>
            this.setState({
                isFetching: false
            });
        });
    }

    render() {
        return (
            <ul>
                { this.state.customers.map(customer => <li>{customer.city}</li>)}
            </ul>
        );
    }
}

export default App;
