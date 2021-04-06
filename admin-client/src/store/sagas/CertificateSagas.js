
import certificateService from '../../services/CertificateService';
import { call, put } from 'redux-saga/effects';
import authService from '../../services/AuthService';
import { authUser } from '../actions/authActions';
import { newError, newSuccess } from '../actions/notificationActions';

export function* addCertificate({payload}){
    try{
        console.log("DOBIO DA SALJEM " , payload);
        const response = yield call(certificateService.addCertificate, payload);    
        console.log(response);
        yield put(newSuccess('Success!'));


    } catch (error) {
        console.log(error);
        yield put(newError('Failed to add.'));

    }
}   