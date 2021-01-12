import React from 'react';
import {Router, Route, Link} from 'react-router-dom';

class App extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            currentUser: null
        };
    }

    render() {
        const {currentUser} = this.state;
        return (
            <h1>Michael Steinert</h1>
        );
    }

}

export default App;
