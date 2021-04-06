import { all, takeLatest } from 'redux-saga/effects';
import { LOGIN, LOGOUT, CHANGE_PASSWORD, GET_REQUESTS} from '../actions/actionTypes';
import { login, logout, changePasswordSaga } from './AuthSagas';
import { getRequests } from './RequestSagas';



export default function* rootSaga() {
    yield all([
        takeLatest(LOGIN, login),
        takeLatest(LOGOUT, logout),
        takeLatest(CHANGE_PASSWORD, changePasswordSaga),
        takeLatest(GET_REQUESTS, getRequests),

    ])
}