import { newError, newSuccess } from "../actions/notificationActions";
import { call, put } from 'redux-saga/effects';
import userService from "../../services/UserService";
import { setUsers } from '../actions/userActions';
import { USERS } from "../../assets/routes";
import { push } from "connected-react-router";

export function* getUsers({ payload }){
    try {
        console.log('safa', payload)
        const response = yield call(userService.getUsers(payload));
        console.log('aaalalala', response)
        yield put(setUsers({role: payload, all: response }));

    } catch (error) {
        console.log(error);
        yield put(newError(error.status));
        yield put(push(USERS));
    }
}