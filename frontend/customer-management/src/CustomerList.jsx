import React, {Component} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import axios from "axios";

const useStyles = makeStyles({
    table: {
        minWidth: 650,
    },
});

const api = axios.create({
    baseURL: 'http://localhost:8080/api/v1/customer',
    headers: {
        'Content-Type': 'application/json'
    }
});

class CustomerList extends Component {
    state = {customers: []}

    constructor() {
        super();
        this.getCustomers();
    }

    getCustomers = async () => {
        await api.get('/customers').then(res => this.setState({customers: res.data})).catch(e => console.log(e))
    }

    render() {
        return (
            <TableContainer component={Paper}>
                <Table className={useStyles.table} aria-label="simple table">
                    <TableHead>
                        <TableRow>
                            <TableCell>First&nbsp;Name</TableCell>
                            <TableCell>Last&nbsp;Name</TableCell>
                            <TableCell>Gender</TableCell>
                            <TableCell>Email</TableCell>
                            <TableCell>Street</TableCell>
                            <TableCell>House&nbsp;Number</TableCell>
                            <TableCell>Zip&nbsp;Code</TableCell>
                            <TableCell>City</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {this.state.customers.map((customer) => (
                            <TableRow key={customer.customerUid}>
                                <TableCell>{customer.firstName}</TableCell>
                                <TableCell>{customer.lastName}</TableCell>
                                <TableCell>{customer.gender}</TableCell>
                                <TableCell>{customer.email}</TableCell>
                                <TableCell>{customer.street}</TableCell>
                                <TableCell>{customer.houseNumber}</TableCell>
                                <TableCell>{customer.zipCode}</TableCell>
                                <TableCell>{customer.city}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        )
    }
}

export default CustomerList;