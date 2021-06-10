import { GET_LOGS, SET_LOGS } from './actionTypes';


export const getLogs = payload => ({
    type: GET_LOGS,
    payload
})

export const setLogs = payload => ({
    type: SET_LOGS,
    payload
})