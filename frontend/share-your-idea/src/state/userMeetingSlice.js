import {createSlice} from "@reduxjs/toolkit";

const userMeetingSlice = createSlice({
    name: "joinUserMeeting",
    initialState: {
        joinedUserMeeting: false,
        userMeetingName: ''
    },
    reducers: {
        join: (state, action) => {
                state.joinedUserMeeting = true;
                state.userMeetingName = action.payload;
                console.log("Redux Join-Reducer");
                console.log("JoinedUserMeeting " + state.joinedUserMeeting);
                console.log("UserMeetingName " + state.userMeetingName);
        },
        leave: (state) => {
            state.joinedUserMeeting = false;
            state.userMeetingSlice = '';
            console.log("Redux Leave-Reducer");
            console.log("JoinedUserMeeting " + state.joinedUserMeeting);
            console.log("UserMeetingName " + state.userMeetingName);
        }
    }
});

export const {join, leave} = userMeetingSlice.actions;

export default userMeetingSlice.reducer;