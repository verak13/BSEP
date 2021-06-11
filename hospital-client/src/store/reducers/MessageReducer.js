import { SET_MESSAGES } from "../actions/actionTypes";


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
                message.timestamp = formatDate(message.timestamp)
                return message;
            })
            return {...state, all: messages, total: action.payload.totalElements, page: action.payload.pageable.pageNumber }
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