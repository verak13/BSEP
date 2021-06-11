import { newError, newSuccess } from "../actions/notificationActions";
import { call, put } from 'redux-saga/effects';
import patientService from "../../services/PatientService";
import { setPatients } from '../actions/patientActions';
import { PATIENTS } from "../../assets/routes";
import { push } from "connected-react-router";

export function* getPatients(action){
    try {
        const response = yield call(patientService.getPatients, action.payload);
        console.log('ooo', response);
        yield put(setPatients(response));

    } catch (error) {
        console.log(error);
        yield put(newError("Can't get patients."));
        yield put(push(PATIENTS));
    }
}