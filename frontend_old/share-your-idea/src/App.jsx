import React from 'react';
import {BrowserRouter, Switch, Route, useHistory} from 'react-router-dom';
import LoginForm from './Forms/Login/LoginForm';
import HomePage from './HomePage/HomePage';
import Meeting from './Meeting/Meeting';
import Search from './Search/Search';
import HeaderBar from './Header/HeaderBar';
import NavigationBar from "./Header/NavigationBar";
import {Grid} from '@material-ui/core';

const App = () => {
    const history = useHistory();

    return (
        <Grid container direction="column">
            <BrowserRouter>
                <Grid container justify="center">
                    <HeaderBar/>
                </Grid>
                <Grid container justify="center">
                    <NavigationBar/>
                </Grid>
                <Grid container justify="center">
                    <Switch>
                        <Route path='/' exact component={HomePage}/>
                        <Route path='/meeting' component={Meeting}/>
                        <Route path='/search' component={Search}/>
                        <Route path='/sign-up' component={LoginForm}/>
                    </Switch>
                </Grid>
            </BrowserRouter>
        </Grid>
    );
}

export default App;
