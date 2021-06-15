import {GET_HOSPITALS, SET_HOSPITALS, ADD_CONFIG} from './actionTypes';


export const getHospitals = payload => ({
    type: GET_HOSPITALS,
    payload
})

export const setHospitals = payload => ({
    type: SET_HOSPITALS,
    payload
})

export const addConfig = payload => ({
    type: ADD_CONFIG,
    payload
})