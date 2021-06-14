import { newError, newSuccess } from "../actions/notificationActions";
import { call, put } from 'redux-saga/effects';
import alarmService from "../../services/AlarmService";
import { setAlarmsBlacklisted, setAlarmsBruteforce, setAlarmsError, setAlarmsInactive } from '../actions/alarmActions';
import { ALARMS_BLACKLISTED, ALARMS_BRUTEFORCE, ALARMS_ERROR, ALARMS_INACTIVE } from "../../assets/routes";
import { push } from "connected-react-router";

export function* getAlarmsBlacklisted(action){
    try {
        const response = yield call(alarmService.getAlarmsBlacklisted, action.payload);
        console.log('ooo', response);
        yield put(setAlarmsBlacklisted(response));

    } catch (error) {
        console.log(error);
        yield put(newError("Can't get this."));
        yield put(push(ALARMS_BLACKLISTED));
    }
}

export function* getAlarmsBruteforce(action){
    try {
        const response = yield call(alarmService.getAlarmsBruteforce, action.payload);
        console.log('ooo', response);
        yield put(setAlarmsBruteforce(response));

    } catch (error) {
        console.log(error);
        yield put(newError("Can't get this."));
        yield put(push(ALARMS_BRUTEFORCE));
    }
}

export function* getAlarmsError(action){
    console.log('ovdeee');
    try {
        const response = yield call(alarmService.getAlarmsError, action.payload);
        console.log('response za error', response);
        yield put(setAlarmsError(response));

    } catch (error) {
        console.log(error);
        yield put(newError("Can't get this."));
        yield put(push(ALARMS_ERROR));
    }
}

export function* getAlarmsInactive(action){
    try {
        const response = yield call(alarmService.getAlarmsInactive, action.payload);
        console.log('ooo', response);
        yield put(setAlarmsInactive(response));

    } catch (error) {
        console.log(error);
        yield put(newError("Can't get this."));
        yield put(push(ALARMS_INACTIVE));
    }
}