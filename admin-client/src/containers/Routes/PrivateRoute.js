import React from 'react'
import { Route } from 'react-router-dom';
import { useKeycloak } from '@react-keycloak/web';

import authService from '../../services/AuthService';

function PrivateRoute({ component: Component, ...rest }) {
    const { keycloak } = useKeycloak()
    const { role } =  rest;
    return (
        <Route {...rest}
            render={props => {
                return  !keycloak?.authenticated ?  keycloak.login():
                role == authService.getRole()? <Component {...props} /> : window.location.href = "https://localhost:3001"
            }}
        />
    )
}
export default PrivateRoute;