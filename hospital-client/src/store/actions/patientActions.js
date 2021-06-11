import { GET_PATIENTS, SET_PATIENTS } from './actionTypes';


export const getPatients = payload => ({
    type: GET_PATIENTS,
    payload
})

export const setPatients = payload => ({
    type: SET_PATIENTS,
    payload
})