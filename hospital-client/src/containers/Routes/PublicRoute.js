import React from 'react';
import { Route, Redirect } from 'react-router-dom';
import { useKeycloak } from '@react-keycloak/web';
import { REQUESTS } from '../../assets/routes';

function PublicRoute({ component: Component, ...rest }) {
    const { keycloak } = useKeycloak()
    return <Route   {...rest}
        render={renderProp => {
            return <Component {...renderProp} />
        }}


    />

}

export default PublicRoute;