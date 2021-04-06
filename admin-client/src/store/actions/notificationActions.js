import React from 'react';
import { ERROR, SUCCESS, RESET_MESSAGE } from "./actionTypes"

export const newError = payload => {
    return {
        type: ERROR,
        payload
    }
}
export const newSuccess = payload => ({
    type: SUCCESS,
    payload
})

export const resetMessage = () => ({
    type: RESET_MESSAGE
})
