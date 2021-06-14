import React from 'react'
import { Route, Redirect } from 'react-router-dom';
import { useKeycloak } from '@react-keycloak/web';
import authService from '../../services/AuthService';
import PublicRoute from './PublicRoute';
import { HOME } from '../../assets/routes';

function PrivateRoute({ component: Component, ...rest }) {
    const { keycloak } = useKeycloak()
    const { role } =  rest;
    return (
        <Route {...rest}
            render={props => {
                return  !keycloak?.authenticated ?  keycloak.login():
                role.includes( authService.getRole()) ? <Component {...props} /> : <Redirect to={HOME} />
            }}
        />
    )
}
export default PrivateRoute;