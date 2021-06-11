import React from 'react';
import { Switch } from 'react-router-dom';
import PrivateRoute from '../../containers/Routes/PrivateRoute';
import PublicRoute from '../../containers/Routes/PublicRoute';
import HomePage from '../../Pages/HomePage';
import LoginPage from '../../Pages/LoginPage';
import { REQUESTS, HOME, REPORT, ADD_DOCTOR, LOGS, ALARMS_BLACKLISTED, ALARMS_BRUTEFORCE, ALARMS_ERROR, ALARMS_INACTIVE, MESSAGES, MESSAGE_ALARMS, PATIENTS, DOCTOR_RULES, REPORT_LIST } from '../../assets/routes';
import Requests from '../../containers/Requests';
import AddDoctor from '../../containers/Doctors';
import Logs from '../../containers/Logs';
import AlarmsBlacklisted from '../../containers/AlarmsBlacklisted';
import AlarmsBruteforce from '../../containers/AlarmsBruteforce';
import AlarmsError from '../../containers/AlarmsError';
import AlarmsInactive from '../../containers/AlarmsInactive';
import Messages from '../../containers/Messages';
import MessageAlarms from '../../containers/MessageAlarms';
import Patients from '../../containers/Patients';
import DoctorRules from '../../containers/DoctorRules';
import Report from '../../containers/Report';
import ReportList from '../../containers/Report/ReportList';

export default function Routes() {
    return (
        <Switch>
            <PublicRoute path={HOME} component={HomePage} exact />
            <PrivateRoute path={REQUESTS} role='HOSPITAL_ADMIN' component={Requests} exact />
            <PrivateRoute path={ADD_DOCTOR} component={AddDoctor} exact />
            <PrivateRoute path={LOGS} role="HOSPITAL_ADMIN" component={Logs} exact />
            <PrivateRoute path={ALARMS_BLACKLISTED} role="HOSPITAL_ADMIN" component={AlarmsBlacklisted} exact />
            <PrivateRoute path={ALARMS_BRUTEFORCE} role="HOSPITAL_ADMIN" component={AlarmsBruteforce} exact />
            <PrivateRoute path={ALARMS_ERROR} role="HOSPITAL_ADMIN" component={AlarmsError} exact />
            <PrivateRoute path={ALARMS_INACTIVE} role="HOSPITAL_ADMIN" component={AlarmsInactive} exact />
            <PrivateRoute path={MESSAGES} role="DOCTOR" component={Messages} exact />
            <PrivateRoute path={MESSAGE_ALARMS} role="DOCTOR" component={MessageAlarms} exact />
            <PrivateRoute path={PATIENTS} role="DOCTOR" component={Patients} exact />
            <PrivateRoute path={DOCTOR_RULES} role="DOCTOR" component={DoctorRules} exact />
            <PrivateRoute path={REPORT} role="HOSPITAL_ADMIN" component={Report} exact />
            <PrivateRoute path={REPORT_LIST} role="HOSPITAL_ADMIN" component={ReportList} exact />
        </Switch>
    )
}

