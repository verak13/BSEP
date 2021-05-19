import React from 'react'
import { Route, Redirect } from 'react-router-dom';
import { useKeycloak } from '@react-keycloak/web';

import PublicRoute from './PublicRoute';

function PrivateRoute({ component: Component, ...rest }) {
    const { keycloak } = useKeycloak()
    return (
        <Route {...rest}
            render={props => {
                return  keycloak?.authenticated ? <Component {...props} /> : keycloak.login()
            }}
        />
    )
}
export default PrivateRoute;