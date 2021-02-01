import {configureStore, combineReducers} from "@reduxjs/toolkit";
import authenticationSlice from "./authenticationSlice";

const reducers = combineReducers({
    authentication: authenticationSlice
});

const store = configureStore({
    reducer: reducers
});

export default store;