import { SET_ALARMS_BRUTEFORCE } from "../actions/actionTypes";

import { formatTimestampWithTime } from '../../utils/index';

const initialState = {
    all: [],
    total : 0,
    page: 0
}

export default function alarmBruteforceReducer(state = initialState, action) {
    switch(action.type){
        case SET_ALARMS_BRUTEFORCE:
            let alarms = action.payload.content
            alarms = alarms.map (alarm => {
                alarm.date = formatTimestampWithTime(alarm.date)
                return alarm;
            })
            return {...state, all: alarms, total: action.payload.totalElements, page: action.payload.pageable.pageNumber }
        default:
            return state
    }

}

