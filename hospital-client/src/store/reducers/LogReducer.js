import { SET_LOGS } from "../actions/actionTypes";


const initialState = {
    all: [],
    total : 0,
    page: 0
}

export default function logReducer(state = initialState, action) {
    switch(action.type){
        case SET_LOGS:
            let logs = action.payload.content
            logs = logs.map (log => {
                log.timestamp = formatDate(log.timestamp)
                return log;
            })
            return {...state, all: logs, total: action.payload.totalElements, page: action.payload.pageable.pageNumber }
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