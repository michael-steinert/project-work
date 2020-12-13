import React, {Component} from 'react';
import {makeStyles} from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import {getAllCustomers, createNewCustomer, deleteCustomerById} from "./Client";
import EditIcon from '@material-ui/icons/Edit';
import DeleteIcon from '@material-ui/icons/Delete';
import AddIcon from '@material-ui/icons/Add';
import SearchIcon from '@material-ui/icons/Search';
import {Button, TableFooter} from "@material-ui/core";

const useStyles = makeStyles({
    table: {
        minWidth: 650,
    },
});

class CustomerList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            customers: [],
            createCustomerFeedback: 0
        };
        this.readAllCustomersFromApi();
    }

    readAllCustomersFromApi() {
        getAllCustomers().then(data => this.setState({customers:data}));
    }

    readCustomerByIdFromApi(customerUid, customer) {

    }

    createCustomerFromApi() {
        const data = {
            firstName: "Marie",
            lastName: "Schmdit",
            gender: "FEMALE",
            email: "marie-schmidt@gmx.de",
            street: "Schmidtstraße",
            houseNumber: "4a",
            zipCode: 58720,
            city: "Menden"
        }
        createNewCustomer(data).then(data => this.setState({createCustomerFeedback:data}));
    }

    updateCustomerByIdFromApi(customerUid) {

    }

    deleteCustomerByIdFromApi(customerUid) {
        deleteCustomerById(customerUid.customerUid)
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
                            <TableCell>Edit</TableCell>
                            <TableCell>Remove</TableCell>
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
                                <TableCell>
                                    <Button aria-label="Edit Customer" onClick="">
                                        <EditIcon/>
                                    </Button>
                                </TableCell>
                                <TableCell>
                                    <Button aria-label="Delete Customer" onClick={() => this.deleteCustomerByIdFromApi({customerUid : customer.customerUid})}>
                                        <DeleteIcon/>
                                    </Button>
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                    <TableFooter>
                        <TableRow>
                            <TableCell>
                                <Button aria-label="Delete Customer" onClick={() => this.createCustomerByIdFromApi()}>
                                    <AddIcon/>
                                </Button>
                                <Button aria-label="Delete Customer" onClick={() => this.readCustomerByIdFromApi()}>
                                    <SearchIcon/>
                                </Button>
                                <div>
                                    Der State: {this.state.createCustomerFeedback}
                                </div>
                            </TableCell>
                        </TableRow>
                    </TableFooter>
                </Table>
            </TableContainer>
        )
    }
}

export default CustomerList;