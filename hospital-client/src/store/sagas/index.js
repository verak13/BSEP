import { all, takeLatest } from 'redux-saga/effects';
import { LOGIN, LOGOUT, CHANGE_PASSWORD, GET_REQUESTS, ADD_REQUEST, ADD_DOCTOR, GET_LOGS, GENERATE_REPORT, GET_ALARMS_BLACKLISTED, 
    GET_ALARMS_BRUTEFORCE, GET_ALARMS_ERROR, GET_ALARMS_INACTIVE, GET_MESSAGE_ALARMS, GET_PATIENTS, GET_MESSAGES, ADD_TEMPERATURE_RULE, ADD_PRESSURE_RULE, GET_REPORTS } from '../actions/actionTypes';
import { login, logout, changePasswordSaga } from './AuthSagas';
import { addRequest, getRequests } from './RequestSagas';
import { addDoctor } from './DoctorSagas';
import { getLogs } from './LogSagas';
import { getAlarmsBlacklisted, getAlarmsBruteforce, getAlarmsError, getAlarmsInactive } from './AlarmSagas';
import { getMessages } from './MessageSagas';
import { getMessageAlarms } from './MessageAlarmSagas';
import { getPatients } from './PatientSagas';
import { pressureRuleAdd, temperatureRuleAdd } from './RulesSagas';
import { generateReport, getReports } from './ReportSagas';


export default function* rootSaga() {
    yield all([
        takeLatest(LOGIN, login),
        takeLatest(LOGOUT, logout),
        takeLatest(CHANGE_PASSWORD, changePasswordSaga),
        takeLatest(GET_REQUESTS, getRequests),
        takeLatest(ADD_REQUEST, addRequest),
        takeLatest(ADD_DOCTOR, addDoctor),
        takeLatest(GET_LOGS, getLogs),
        takeLatest(GET_ALARMS_BLACKLISTED, getAlarmsBlacklisted),
        takeLatest(GET_ALARMS_BRUTEFORCE, getAlarmsBruteforce),
        takeLatest(GET_ALARMS_ERROR, getAlarmsError),
        takeLatest(GET_ALARMS_INACTIVE, getAlarmsInactive),
        takeLatest(GET_MESSAGES, getMessages),
        takeLatest(GET_MESSAGE_ALARMS, getMessageAlarms),
        takeLatest(GET_PATIENTS, getPatients),
        takeLatest(ADD_TEMPERATURE_RULE, temperatureRuleAdd),
        takeLatest(ADD_PRESSURE_RULE, pressureRuleAdd),
        takeLatest(GENERATE_REPORT, generateReport),
        takeLatest(GET_REPORTS, getReports),
    ])
}