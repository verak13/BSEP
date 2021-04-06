import React from 'react';
import { Switch } from 'react-router-dom';
import PrivateRoute from '../../containers/Routes/PrivateRoute';
import PublicRoute from '../../containers/Routes/PublicRoute';
import HomePage from '../../Pages/HomePage';
import LoginPage from '../../Pages/LoginPage';
import { REQUESTS, HOME, LOGIN } from '../../assets/routes';
import Requests from '../../containers/Requests';

export default function Routes() {
    return (
        <Switch>
            <PublicRoute path={HOME} component={HomePage} exact />
            <PublicRoute path={LOGIN} component={LoginPage} exact />            
            <PrivateRoute path={REQUESTS} component={Requests} exact />
        </Switch>
    )
}

