import React, {Component} from 'react';
import {makeStyles} from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import EditIcon from '@material-ui/icons/Edit';
import DeleteIcon from '@material-ui/icons/Delete';
import AddIcon from '@material-ui/icons/Add';
import SearchIcon from '@material-ui/icons/Search';
import {createNewOrder, deleteOrderById, getAllOrders} from "./Client";
import {Button, TableFooter} from "@material-ui/core";

const useStyles = makeStyles({
    table: {
        minWidth: 650,
    },
});

class CustomerList extends Component {
    state = {orders: []}

    constructor(props) {
        super(props);
        this.state = {
            orders: [],
            createOderFeedback: 0
        };
        this.readAllOrdersFromApi();
    }

    readAllOrdersFromApi() {
        getAllOrders().then(data => this.setState({orders:data}));
    }

    readOrderByIdFromApi(orderUid, order) {

    }

    createOrderFromApi() {
        const data = {
            customerUid: "c5d69be4-13cb-493e-902a-9b98c87a05cc",
            bikeUid: "74f2c2de-2841-11eb-adc1-0242ac120082",
            totalPrice: 899.94,
            orderDate: "2020-12-13"
        }
        createNewOrder(data).then(data => this.setState({createOrderFeedback:data}));
    }

    updateOrderByIdFromApi(orderUid) {

    }

    deleteOrderByIdFromApi(orderUid) {
        deleteOrderById(orderUid.orderUid)
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
                                <TableCell>
                                    <Button aria-label="Edit Order" onClick="">
                                        <EditIcon/>
                                    </Button>
                                </TableCell>
                                <TableCell>
                                    <Button aria-label="Delete Order" onClick={() => this.deleteOrderByIdFromApi({orderUid : order.orderUid})}>
                                        <DeleteIcon/>
                                    </Button>
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                    <TableFooter>
                        <TableRow>
                            <TableCell>
                                <Button aria-label="Delete Order" onClick={() => this.createOrderFromApi()}>
                                    <AddIcon/>
                                </Button>
                                <Button aria-label="Delete Order" onClick={() => this.readOrderByIdFromApi()}>
                                    <SearchIcon/>
                                </Button>
                                <div>
                                    Der State: {this.state.createOrderFeedback}
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