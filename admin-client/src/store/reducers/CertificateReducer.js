import { SET_CERTIFICATES } from "../actions/actionTypes";
import { ADD_CERTIFICATE } from "../actions/certificateActions";


const initialState = {
    certificates: [],
}

export default function requestReducer(state = initialState, action) {
    switch(action.type){
        
        case SET_CERTIFICATES:
            return {...state, certificates: action.payload}
        default:
            return state
    }

}