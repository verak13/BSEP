import { newError, newSuccess } from "../actions/notificationActions";
import { call, put } from 'redux-saga/effects';
import logService from "../../services/LogService";
import { setLogs } from '../actions/logActions';
import { LOGS } from "../../assets/routes";
import { push } from "connected-react-router";

export function* getLogs(action){
    try {
        const response = yield call(logService.getLogs, action.payload);
        yield put(setLogs(response));

    } catch (error) {
        console.log(error);
        yield put(newError("Can't get logs."));
        yield put(push(LOGS));
    }
}