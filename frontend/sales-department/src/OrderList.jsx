import React, {Component} from 'react';
import {makeStyles} from '@material-ui/core/styles';
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
    baseURL: 'http://localhost:8080/api/v1/order',
    headers: {
        'Content-Type': 'application/json'
    }
});

class CustomerList extends Component {
    state = {orders: []}

    constructor() {
        super();
        this.getOrders();
    }

    getOrders = async () => {
        await api.get('/orders').then(res => this.setState({orders: res.data})).catch(e => console.log(e))
    }

    render() {
        return (
            <TableContainer component={Paper}>
                <Table className={useStyles.table} aria-label="simple table">
                    <TableHead>
                        <TableRow>
                            <TableCell>Customer&nbsp;UUID</TableCell>
                            <TableCell>Bike&nbsp;UUID</TableCell>
                            <TableCell>Total&nbsp;Price</TableCell>
                            <TableCell>Order&nbsp;Date</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {this.state.orders.map((order) => (
                            <TableRow key={order.orderUid}>
                                <TableCell>{order.customerUid}</TableCell>
                                <TableCell>{order.bikeUid}</TableCell>
                                <TableCell>{order.totalPrice}</TableCell>
                                <TableCell>{order.orderDate}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        )
    }
}

export default CustomerList;