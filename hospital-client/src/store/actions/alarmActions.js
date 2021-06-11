import { GET_ALARMS_BLACKLISTED, SET_ALARMS_BLACKLISTED, GET_ALARMS_BRUTEFORCE, SET_ALARMS_BRUTEFORCE, 
    GET_ALARMS_ERROR, SET_ALARMS_ERROR, GET_ALARMS_INACTIVE, SET_ALARMS_INACTIVE } from './actionTypes';


export const getAlarmsBlacklisted = payload => ({
    type: GET_ALARMS_BLACKLISTED,
    payload
})

export const setAlarmsBlacklisted = payload => ({
    type: SET_ALARMS_BLACKLISTED,
    payload
})

export const getAlarmsBruteforce = payload => ({
    type: GET_ALARMS_BRUTEFORCE,
    payload
})

export const setAlarmsBruteforce = payload => ({
    type: SET_ALARMS_BRUTEFORCE,
    payload
})

export const getAlarmsError = payload => ({
    type: GET_ALARMS_ERROR,
    payload
})

export const setAlarmsError = payload => ({
    type: SET_ALARMS_ERROR,
    payload
})

export const getAlarmsInactive = payload => ({
    type: GET_ALARMS_INACTIVE,
    payload
})

export const setAlarmsInactive = payload => ({
    type: SET_ALARMS_INACTIVE,
    payload
})