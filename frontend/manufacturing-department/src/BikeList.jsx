import React, {Component} from 'react';
import {makeStyles} from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import {createNewBike, deleteBikeById, getAllBikes} from "./Client";
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

class BikeList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            bikes: [],
            createBikeFeedback: 0
        };
        this.readAllBikesFromApi();
    }

    readAllBikesFromApi() {
        getAllBikes().then(data => this.setState({bikes:data}));
    }

    readBikeByIdFromApi(bikeUid) {

    }

    createBikeFromApi() {
        const data = {
            bikeName: "Cube GTC Agree",
            description: "Das Cube Agree GTC ist gedacht für Spaß bei großen Strecken",
            shortDescription: "Gedacht für Spaß bei großen Strecken",
            bikeType: "RACINGBIKE",
            price: 755.29
        }
        createNewBike(data).then(data => this.setState({createBikeFeedback:data}));
    }

    updateBikeByIdFromApi(customerUid, bike) {

    }

    deleteBikeByIdFromApi(bikeUid) {
        deleteBikeById(bikeUid.bikeUid)
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
                                <TableCell>
                                    <Button aria-label="Edit Bike" onClick="">
                                        <EditIcon/>
                                    </Button>
                                </TableCell>
                                <TableCell>
                                    <Button aria-label="Delete Bike" onClick={() => this.deleteBikeByIdFromApi({bikeUid : bike.bikeUid})}>
                                        <DeleteIcon/>
                                    </Button>
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                    <TableFooter>
                        <TableRow>
                            <TableCell>
                                <Button aria-label="Delete Bike" onClick={() => this.createBikeFromApi()}>
                                    <AddIcon/>
                                </Button>
                                <Button aria-label="Delete Bike" onClick={() => this.readBikeByIdFromApi()}>
                                    <SearchIcon/>
                                </Button>
                                <div>
                                    Der State: {this.state.createBikeFeedback}
                                </div>
                            </TableCell>
                        </TableRow>
                    </TableFooter>
                </Table>
            </TableContainer>
        )
    }
}

export default BikeList;