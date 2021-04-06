import { SET_REQUESTS } from "../actions/actionTypes";


const initialState = {
    requests: [],
}

export default function requestReducer(state = initialState, action) {
    switch(action.type){
        case SET_REQUESTS:
            return {...state, requests: action.payload}
        default:
            return state
    }

}