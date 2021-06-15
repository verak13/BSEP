import { newError, newSuccess } from "../actions/notificationActions";
import { call, put } from 'redux-saga/effects';
import hospitalConfigurationService from "../../services/HospitalConfigurationService";
import { setHospitals } from '../actions/hospitalConfigurationActions';
import { HOSPITALS } from "../../assets/routes";
import { push } from "connected-react-router";

export function* getHospitals({ payload }){
    try {
        const response = yield call(hospitalConfigurationService.getHospitals, payload);
        yield put(setHospitals(response));

    } catch (error) {
        console.log(error);
        yield put(newError(error.status));
        yield put(push(HOSPITALS));
    }
}

export function* addConfiguration({payload}){
    try{
        console.log("DOBIO DA SALJEM " , payload);
        const response = yield call(hospitalConfigurationService.addConfig, payload);    
        console.log(response);
        yield put(newSuccess('Success!'));        

    } catch (error) {
        console.log(error);
        yield put(newError('Failed to add.'));

    }
}