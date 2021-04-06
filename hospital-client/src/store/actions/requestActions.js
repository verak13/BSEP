import {GET_REQUESTS, SET_REQUESTS} from './actionTypes';


export const getRequests = payload => ({
    type: GET_REQUESTS,
})

export const setRequests = payload => ({
    type:SET_REQUESTS,
    payload
})
