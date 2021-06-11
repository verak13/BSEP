import { SET_PATIENTS } from "../actions/actionTypes";


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
                patient.birthDate = formatDate(patient.birthDate)
                return patient;
            })
            return {...state, all: patients, total: action.payload.totalElements, page: action.payload.pageable.pageNumber }
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