import { SET_MESSAGE_ALARMS } from "../actions/actionTypes";


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
                messageAlarm.date = formatDate(messageAlarm.date)
                return messageAlarm;
            })
            return {...state, all: messageAlarms, total: action.payload.totalElements, page: action.payload.pageable.pageNumber }
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