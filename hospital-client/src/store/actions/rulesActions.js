import {ADD_TEMPERATURE_RULE, ADD_PRESSURE_RULE, ADD_CUSTOM_MESSAGE_RULE, ADD_LOG_RULE} from './actionTypes';


export const addTemperatureRule = payload => ({
    type: ADD_TEMPERATURE_RULE,
    payload
})

export const addPressureRule = payload => ({
    type:ADD_PRESSURE_RULE,
    payload
})

export const addCustomMessageRule = payload => ({
    type:ADD_CUSTOM_MESSAGE_RULE,
    payload
})

export const addLogRule = payload => ({
    type:ADD_LOG_RULE,
    payload
})