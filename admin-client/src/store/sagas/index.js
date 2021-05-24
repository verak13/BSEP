import { all, takeLatest } from 'redux-saga/effects';
import { LOGIN, LOGOUT, CHANGE_PASSWORD, GET_REQUESTS, ADD_CERTIFICATE, GET_CERTIFICATES, REVOKE_CERTIFICATE, GET_USERS} from '../actions/actionTypes';
import { login, logout, changePasswordSaga } from './AuthSagas';
import { addCertificate, getCertificates, revokeCertificate } from './CertificateSagas';
import { getRequests } from './RequestSagas';
import { getUsers } from './UserSaga';



export default function* rootSaga() {
    yield all([
        takeLatest(LOGIN, login),
        takeLatest(LOGOUT, logout),
        takeLatest(CHANGE_PASSWORD, changePasswordSaga),
        takeLatest(GET_REQUESTS, getRequests),
        takeLatest(ADD_CERTIFICATE, addCertificate),
        takeLatest(GET_CERTIFICATES, getCertificates),
        takeLatest(REVOKE_CERTIFICATE, revokeCertificate),
        takeLatest(GET_USERS, getUsers)
    ])
}