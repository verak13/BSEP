import { GET_USERS, SET_USERS } from './actionTypes';


export const getUsers = payload => ({
    type: GET_USERS,
    payload
})

export const setUsers = payload => ({
    type: SET_USERS,
    payload
})