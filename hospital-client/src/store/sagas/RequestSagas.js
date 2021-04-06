import { newError, newSuccess } from "../actions/notificationActions";
import { call, put } from 'redux-saga/effects';
import requestService from "../../services/RequestService";
import { setRequests } from '../actions/requestActions';
import { REQUESTS } from "../../assets/routes";
import { push } from "connected-react-router";

export function* getRequests(){
    try {
        const response = yield call(requestService.getRequests);
        yield put(setRequests(response));

    } catch (error) {
        console.log(error);
        yield put(newError(error.status));
        yield put(push(REQUESTS));
    }
}

export function* addRequest({payload}){
    try{

        const response = yield call(requestService.addRequest, payload);
        yield put(newSuccess("Request made."));

    }catch(error){
        console.log(error);
        yield put(newError("Can't make request."));

    }
}