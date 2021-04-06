import { SET_REQUESTS, REMOVE_REQUEST } from "../actions/actionTypes";


const initialState = {
    requests: [],
}

export default function requestReducer(state = initialState, action) {
    switch(action.type){
        case SET_REQUESTS:
            return {...state, requests: action.payload}
        case REMOVE_REQUEST:
            return {requests: state.requests.filter(req => req.id !== action.payload)}
        default:
            return state
    }

}