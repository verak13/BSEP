import { SET_LOGS } from "../actions/actionTypes";

import { formatTimestamp } from '../../utils/index';

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
                log.timestamp = formatTimestamp(log.timestamp)
                return log;
            })
            return {...state, all: logs, total: action.payload.totalElements, page: action.payload.pageable.pageNumber }
        default:
            return state
    }

}