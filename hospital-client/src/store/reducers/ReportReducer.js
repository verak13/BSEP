import { SET_REPORT, SET_REPORTS } from "../actions/actionTypes";


const initialState = {
   report: {},
   all: []
}

export default function reportReducer(state = initialState, action) {
    switch(action.type){
        case SET_REPORT:
          
            return {...state, report: action.payload }
        case SET_REPORTS:
        
            return {...state, all: action.payload }
        default:
            return state
    }

}