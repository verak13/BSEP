import { SET_MESSAGES } from "../actions/actionTypes";

import { formatTimestamp } from '../../utils/index';

const initialState = {
    all: [],
    total : 0,
    page: 0
}

export default function messageReducer(state = initialState, action) {
    switch(action.type){
        case SET_MESSAGES:
            let messages = action.payload.content
            messages = messages.map (message => {
                message.timestamp = formatTimestamp(message.timestamp)
                return message;
            })
            return {...state, all: messages, total: action.payload.totalElements, page: action.payload.pageable.pageNumber }
        default:
            return state
    }

}