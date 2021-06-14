import { SET_MESSAGE_ALARMS } from "../actions/actionTypes";

import { formatTimestampWithTime } from '../../utils/index';

const initialState = {
    all: [],
    total : 0,
    page: 0
}

export default function messageAlarmReducer(state = initialState, action) {
    switch(action.type){
        case SET_MESSAGE_ALARMS:
            let messageAlarms = action.payload.content
            messageAlarms = messageAlarms.map (messageAlarm => {
                messageAlarm.date = formatTimestampWithTime(messageAlarm.date)
                return messageAlarm;
            })
            return {...state, all: messageAlarms, total: action.payload.totalElements, page: action.payload.pageable.pageNumber }
        default:
            return state
    }

}