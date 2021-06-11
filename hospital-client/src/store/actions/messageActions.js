import { GET_MESSAGES, SET_MESSAGES } from './actionTypes';


export const getMessages = payload => ({
    type: GET_MESSAGES,
    payload
})

export const setMessages = payload => ({
    type: SET_MESSAGES,
    payload
})