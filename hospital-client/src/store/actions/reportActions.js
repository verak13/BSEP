import { GENERATE_REPORT, SET_REPORT, GET_REPORTS, SET_REPORTS } from './actionTypes';

export const generateReport = () => ({
    type: GENERATE_REPORT
})

export const setReport = payload => ({
    type: SET_REPORT,
    payload
})

export const getReports = () => ({
    type: GET_REPORTS
})

export const setReports = payload => ({
    type: SET_REPORTS,
    payload
})