import { newError, newSuccess } from "../actions/notificationActions";
import { call, put } from 'redux-saga/effects';
import doctorService from "../../services/DoctorService";
import { ADD_DOCTOR } from "../../assets/routes";
import { push } from "connected-react-router";


export function* addDoctor({payload}){
    try{

        console.log("payload")
        console.log(payload)
        const response = yield call(doctorService.addDoctor, payload);
        yield put(newSuccess("Doctor added."));

    }catch(error){
        console.log(error);
        yield put(newError("Can't add doctor."));

    }
}