import React from 'react';
import { Switch } from 'react-router-dom';
import PrivateRoute from '../../containers/Routes/PrivateRoute';
import PublicRoute from '../../containers/Routes/PublicRoute';
import HomePage from '../../Pages/HomePage';
import Certificates from '../../containers/Certificates';
import { CERTIFICATES, REQUESTS, HOME, USERS, ADD_CONFIG, HOSPITALS } from '../../assets/routes';
import Requests from '../../containers/Requests';
import Users from '../../containers/Users';
import AddConfig from '../../containers/AddConfig';
import Hospitals from '../../containers/Hospitals';

export default function Routes() {
    return (
        <Switch>
            <PublicRoute path={HOME} role={'SUPER_ADMIN'} component={HomePage} exact />
            <PrivateRoute path={REQUESTS} role={'SUPER_ADMIN'} component={Requests} exact />
            <PrivateRoute path={CERTIFICATES} role={'SUPER_ADMIN'}component={Certificates} exact />
            <PrivateRoute path={USERS} role={'SUPER_ADMIN'} component={Users} exact />
            <PrivateRoute path={ADD_CONFIG} role={'SUPER_ADMIN'}component={AddConfig} exact />
            <PrivateRoute path={HOSPITALS} role={'SUPER_ADMIN'}component={Hospitals} exact />
        </Switch>
    )
}

