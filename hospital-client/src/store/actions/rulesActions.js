import {ADD_TEMPERATURE_RULE, ADD_PRESSURE_RULE} from './actionTypes';


export const addTemperatureRule = payload => ({
    type: ADD_TEMPERATURE_RULE,
    payload
})

export const addPressureRule = payload => ({
    type:ADD_PRESSURE_RULE,
    payload
})
