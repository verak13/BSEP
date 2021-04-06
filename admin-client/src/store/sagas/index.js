import { all, takeLatest } from 'redux-saga/effects';
import { LOGIN, LOGOUT, CHANGE_PASSWORD, GET_REQUESTS, ADD_CERTIFICATE, GET_CERTIFICATES, REVOKE_CERTIFICATE} from '../actions/actionTypes';
import { login, logout, changePasswordSaga } from './AuthSagas';
import { addCertificate, getCertificates, revokeCertificate } from './CertificateSagas';
import { getRequests } from './RequestSagas';



export default function* rootSaga() {
    yield all([
        takeLatest(LOGIN, login),
        takeLatest(LOGOUT, logout),
        takeLatest(CHANGE_PASSWORD, changePasswordSaga),
        takeLatest(GET_REQUESTS, getRequests),
        takeLatest(ADD_CERTIFICATE, addCertificate),
        takeLatest(GET_CERTIFICATES, getCertificates),
        takeLatest(REVOKE_CERTIFICATE, revokeCertificate),
        
    ])
}