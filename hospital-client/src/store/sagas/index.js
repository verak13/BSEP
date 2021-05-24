import { all, takeLatest } from 'redux-saga/effects';
import { LOGIN, LOGOUT, CHANGE_PASSWORD, GET_REQUESTS, ADD_REQUEST, ADD_DOCTOR} from '../actions/actionTypes';
import { login, logout, changePasswordSaga } from './AuthSagas';
import { addRequest, getRequests } from './RequestSagas';
import { addDoctor } from './DoctorSagas';



export default function* rootSaga() {
    yield all([
        takeLatest(LOGIN, login),
        takeLatest(LOGOUT, logout),
        takeLatest(CHANGE_PASSWORD, changePasswordSaga),
        takeLatest(GET_REQUESTS, getRequests),
        takeLatest(ADD_REQUEST, addRequest),
        takeLatest(ADD_DOCTOR, addDoctor),
    ])
}