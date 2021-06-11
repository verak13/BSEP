import { newError, newSuccess } from "../actions/notificationActions";
import { call, put } from 'redux-saga/effects';
import hospitalConfigurationService from "../../services/HospitalConfigurationService";
import { setHospitals } from '../actions/hospitalConfigurationActions';
import { HOSPITALS } from "../../assets/routes";
import { push } from "connected-react-router";

export function* getHospitals(){
    try {
        const response = yield call(hospitalConfigurationService.getHospitals);
        yield put(setHospitals(response));

    } catch (error) {
        console.log(error);
        yield put(newError(error.status));
        yield put(push(HOSPITALS));
    }
}