import { SET_HOSPITALS } from "../actions/actionTypes";


const initialState = {
    all: [],
    total : 0,
    page: 0
}

export default function hospitalConfigurationReducer(state = initialState, action) {
    switch(action.type){
        case SET_HOSPITALS:
            let hospitals = action.payload.content
            return {...state, all: hospitals, total: action.payload.totalElements, page: action.payload.pageable.pageNumber }
        default:
            return state
    }

}

