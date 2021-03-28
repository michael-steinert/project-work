import {createSlice} from "@reduxjs/toolkit";

const authenticationSlice = createSlice({
    name: "authentication",
    initialState: {
        authentication: false
    },
    reducers: {
        success: (state) => ({...state, authentication: true}),
        fail: (state) => ({...state, authentication: false})
    }
});

export const {success, fail} = authenticationSlice.actions;

export default authenticationSlice.reducer;