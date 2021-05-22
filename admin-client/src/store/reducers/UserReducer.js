import { SET_USERS } from "../actions/actionTypes";


const initialState = {
    certificates: [],
}

export default function requestReducer(state = initialState, action) {
    switch(action.type){
        
        case SET_USERS:
            return {...state, all: action.payload}
        default:
            return state
    }

}