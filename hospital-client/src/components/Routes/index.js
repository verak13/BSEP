import React from 'react';
import { Switch } from 'react-router-dom';
import PrivateRoute from '../../containers/Routes/PrivateRoute';
import PublicRoute from '../../containers/Routes/PublicRoute';
import HomePage from '../../Pages/HomePage';
import LoginPage from '../../Pages/LoginPage';
import { REQUESTS, HOME, LOGIN, ADD_DOCTOR, LOGS } from '../../assets/routes';
import Requests from '../../containers/Requests';
import AddDoctor from '../../containers/Doctors';
import Logs from '../../containers/Logs';

export default function Routes() {
    return (
        <Switch>
            <PublicRoute path={HOME} component={HomePage} exact />
            <PrivateRoute path={REQUESTS} role='HOSPITAL_ADMIN' component={Requests} exact />
            <PrivateRoute path={ADD_DOCTOR} component={AddDoctor} exact />
            <PrivateRoute path={LOGS} role="HOSPITAL_ADMIN" component={Logs} exact />
        </Switch>
    )
}

