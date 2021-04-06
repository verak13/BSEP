import React from 'react';
import { ERROR, SUCCESS, RESET_MESSAGE } from '../actions/actionTypes';


const initialState = {
    message: null,
    type: null
}

export default function NotificationReducer(state = initialState, action) {
    switch (action.type) {
        case ERROR:
            return { message: action.payload, type: ERROR }
        case SUCCESS:
            return { message: action.payload, type: SUCCESS }
        case RESET_MESSAGE:
            return { message: null, type: null }
        default:
            return state;
    }
}