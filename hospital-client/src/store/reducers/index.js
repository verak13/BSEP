import { combineReducers } from 'redux';
import { connectRouter } from 'connected-react-router';
import AuthReducer from './AuthReducer';
import NotificationReducer from './NotificationReducer';
import RequestReducer from './RequestReducer';
import LogReducer from './LogReducer';
import AlarmBlacklistedReducer from './AlarmBlacklistedReducer';
import AlarmBruteforceReducer from './AlarmBruteforceReducer';
import AlarmErrorReducer from './AlarmErrorReducer';
import AlarmInactiveReducer from './AlarmInactiveReducer';
import MessageReducer from './MessageReducer';
import MessageAlarmReducer from './MessageAlarmReducer';
import PatientReducer from './PatientReducer';
import RulesReducer from './RulesReducer';
import AlarmCustomReducer from './AlarmCustomReducer';
import ReportReducer from './ReportReducer';

export default history =>
    combineReducers({
        auth: AuthReducer,
        notification: NotificationReducer,
        requests: RequestReducer,
        logs: LogReducer,
        alarmBlackListed: AlarmBlacklistedReducer, 
        alarmBruteforce: AlarmBruteforceReducer, 
        alarmError: AlarmErrorReducer, 
        alarmInactive: AlarmInactiveReducer,
        alarmCustom: AlarmCustomReducer,
        messages: MessageReducer,
        messageAlarms: MessageAlarmReducer,
        patients: PatientReducer,
        rules: RulesReducer,
        report: ReportReducer,
        router: connectRouter(history),
    });
