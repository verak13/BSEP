import { SET_PATIENTS } from "../actions/actionTypes";

import { formatTimestamp } from '../../utils/index';

const initialState = {
    all: [],
    total : 0,
    page: 0
}

export default function patientReducer(state = initialState, action) {
    switch(action.type){
        case SET_PATIENTS:
            let patients = action.payload.content
            patients = patients.map (patient => {
                patient.birthDate = formatTimestamp(patient.birthDate)
                return patient;
            })
            return {...state, all: patients, total: action.payload.totalElements, page: action.payload.pageable.pageNumber }
        default:
            return state
    }

}