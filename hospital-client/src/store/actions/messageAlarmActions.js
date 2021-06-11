import { GET_MESSAGE_ALARMS, SET_MESSAGE_ALARMS } from './actionTypes';


export const getMessageAlarms = payload => ({
    type: GET_MESSAGE_ALARMS,
    payload
})

export const setMessageAlarms = payload => ({
    type: SET_MESSAGE_ALARMS,
    payload
})