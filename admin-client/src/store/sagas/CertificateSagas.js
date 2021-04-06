
import certificateService from '../../services/CertificateService';
import { call, put } from 'redux-saga/effects';
import authService from '../../services/AuthService';
import { authUser } from '../actions/authActions';
import { newError, newSuccess } from '../actions/notificationActions';
import {removeRequest} from '../actions/requestActions';
import { setCertificates } from '../actions/certificateActions';

export function* addCertificate({payload}){
    try{
        console.log("DOBIO DA SALJEM " , payload);
        const response = yield call(certificateService.addCertificate, payload);    
        console.log(response);
        yield put(newSuccess('Success!'));
        yield put(removeRequest(payload.requestId));

    } catch (error) {
        console.log(error);
        yield put(newError('Failed to add.'));

    }
}   

export function* getCertificates() {
    try{
        const response = yield call(certificateService.getCertificates);
        yield put(setCertificates(response));
        
    }catch(errro){
        yield put(newError("Can't get."))
    }
}

export function* revokeCertificate({payload}) {
    try{
        const response = yield call(certificateService.revokeCertificate, payload);
        yield put(newSuccess('Successfully revoked.'));
    }catch(error){
        yield put(newError("Can't revoke."))

    }
}