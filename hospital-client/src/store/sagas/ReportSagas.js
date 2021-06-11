import { newError, newSuccess } from "../actions/notificationActions";
import { call, put } from 'redux-saga/effects';
import reportService from "../../services/ReportService";
import { setReport, setReports } from '../actions/reportActions';

export function* generateReport(action){
    try {
        const response = yield call(reportService.generateReport);
        yield put(setReport(response));
        yield put(newSuccess("Generated report successfully"));
    } catch (error) {
        console.log(error);
        yield put(newError("Can't generate report."));
    }
}

export function* getReports(action){
    try {
        const response = yield call(reportService.getReports);
        yield put(setReports(response));

    } catch (error) {
        console.log(error);
        yield put(newError("Can't get reports."));
    }
}