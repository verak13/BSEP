import { newError, newSuccess } from "../actions/notificationActions";
import { call, put } from 'redux-saga/effects';
import messageAlarmService from "../../services/MessageAlarmService";
import { setMessageAlarms } from '../actions/messageAlarmActions';
import { MESSAGE_ALARMS } from "../../assets/routes";
import { push } from "connected-react-router";

export function* getMessageAlarms(action){
    try {
        const response = yield call(messageAlarmService.getMessageAlarms, action.payload);
        console.log('ooo', response);
        yield put(setMessageAlarms(response));

    } catch (error) {
        console.log(error);
        yield put(newError("Can't get message alarms."));
        yield put(push(MESSAGE_ALARMS));
    }
}