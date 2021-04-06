import {GET_REQUESTS, SET_REQUESTS, REMOVE_REQUEST} from './actionTypes';


export const getRequests = payload => ({
    type: GET_REQUESTS,
})

export const setRequests = payload => ({
    type:SET_REQUESTS,
    payload
})

export const removeRequest = payload =>({
    type: REMOVE_REQUEST,
    payload
})
