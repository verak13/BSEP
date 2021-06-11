import { GET_ALARMS_BLACKLISTED, SET_ALARMS_BLACKLISTED, GET_ALARMS_BRUTEFORCE, SET_ALARMS_BRUTEFORCE, 
    GET_ALARMS_ERROR, SET_ALARMS_ERROR, GET_ALARMS_INACTIVE, SET_ALARMS_INACTIVE } from "../actions/actionTypes";


const initialState = {
    all: [],
    total : 0,
    page: 0
}

export default function alarmInactiveReducer(state = initialState, action) {
    switch(action.type){
        case SET_ALARMS_INACTIVE:
            let alarms = action.payload.content
            return {...state, all: alarms, total: action.payload.totalElements, page: action.payload.pageable.pageNumber }
        default:
            return state
    }

}

