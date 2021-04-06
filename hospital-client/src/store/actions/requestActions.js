import {GET_REQUESTS, SET_REQUESTS, ADD_REQUEST} from './actionTypes';


export const getRequests = payload => ({
    type: GET_REQUESTS,
})

export const setRequests = payload => ({
    type:SET_REQUESTS,
    payload
})

export const addRequest = payload => ({
    type: ADD_REQUEST,
    payload
})