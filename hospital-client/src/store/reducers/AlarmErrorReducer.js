import { GET_ALARMS_BLACKLISTED, SET_ALARMS_BLACKLISTED, GET_ALARMS_BRUTEFORCE, SET_ALARMS_BRUTEFORCE, 
    GET_ALARMS_ERROR, SET_ALARMS_ERROR, GET_ALARMS_INACTIVE, SET_ALARMS_INACTIVE } from "../actions/actionTypes";


const initialState = {
    all: [],
    total : 0,
    page: 0
}

export default function alarmErrorReducer(state = initialState, action) {
    switch(action.type){
        case SET_ALARMS_ERROR:
            let alarms = action.payload.content
            alarms = alarms.map (alarm => {
                alarm.date = formatDate(alarm.date)
                return alarm;
            })
            return {...state, all: alarms, total: action.payload.totalElements, page: action.payload.pageable.pageNumber }
        default:
            return state
    }

}

const formatDate = timestamp => {
    const arr = timestamp.split("T");
    const date = arr[0].split("-");
    const datestr = date[2] + "." + date[1] + "." + date[0] + ".";
 
    return datestr
 }

