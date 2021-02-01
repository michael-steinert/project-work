import {createSlice} from "@reduxjs/toolkit";

const authenticationSlice = createSlice({
    name: "authentication",
    initialState: {
        auth: false
    },
    reducers: {
        success: (state) => ({...state, auth: true}),
        fail: (state) => ({...state, auth: false})
    }
});

export const {success, fail} = authenticationSlice.actions;

export default authenticationSlice.reducer;