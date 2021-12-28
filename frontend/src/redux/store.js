import {createSlice, configureStore} from "@reduxjs/toolkit";

const userSlice = createSlice({
        name: 'userSlice',
        initialState: {
            user: []
        },
        reducers: {
            addUserS: (state, action) =>{
                console.log('add User');
                console.log(action.payload);
                state.user = [action.payload];
                console.log(state);
            },
            removeUserS: (state)=>{
                console.log('remove User');
                state.user = [];
                console.log(state);
            }
        }
    }
)

export const {addUserS, removeUserS} = userSlice.actions;

export const store = configureStore({
    reducer: userSlice.reducer
})

export default userSlice.reducer;

store.subscribe(()=> console.log(store.getState()));