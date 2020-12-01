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
    baseURL: 'http://localhost:8080/api/v1/bike',
    headers: {
        'Content-Type': 'application/json'
    }
});

class BikeList extends Component {
    state = {bikes: []}

    constructor() {
        super();
        this.getBikes();
    }

    getBikes = async () => {
        await api.get('/bikes').then(res => this.setState({bikes: res.data})).catch(e => console.log(e))
    }

    render() {
        return (
            <TableContainer component={Paper}>
                <Table className={useStyles.table} aria-label="simple table">
                    <TableHead>
                        <TableRow>
                            <TableCell>Bike&nbsp;Name</TableCell>
                            <TableCell>Description</TableCell>
                            <TableCell>Short&nbsp;Description</TableCell>
                            <TableCell>Bike&nbsp;Type</TableCell>
                            <TableCell>Price</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {this.state.bikes.map((bike) => (
                            <TableRow key={bike.bikeUid}>
                                <TableCell>{bike.bikeName}</TableCell>
                                <TableCell>{bike.description}</TableCell>
                                <TableCell>{bike.shortDescription}</TableCell>
                                <TableCell>{bike.bikeType}</TableCell>
                                <TableCell>{bike.price}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        )
    }
}

export default BikeList;