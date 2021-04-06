import { AUTH_USER } from "../actions/actionTypes"
import authService from "../../services/AuthService"

const initialState = {
    isAuthenticated: authService.isAuthenticated(),   
    email: authService.getEmail()
}

export default (state = initialState, action) => {
    switch (action.type) {
        case AUTH_USER:
            return { ...state, isAuthenticated: action.payload, email: authService.getEmail() }
        default:
            return state
    }
}