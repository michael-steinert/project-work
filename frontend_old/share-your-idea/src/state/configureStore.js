import {configureStore, combineReducers} from "@reduxjs/toolkit";
import authenticationSlice from "./authenticationSlice";
import userMeetingSlice from "./userMeetingSlice";

const reducers = combineReducers({
    authentication: authenticationSlice,
    joinUserMeeting: userMeetingSlice
});

const store = configureStore({
    reducer: reducers
});

export default store;