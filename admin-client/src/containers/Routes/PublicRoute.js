import React from 'react';
import { Route } from 'react-router-dom';
import authService from '../../services/AuthService';

function PublicRoute({ component: Component, ...rest }) {
    const { role } =  rest;
    return <Route   {...rest}
        render={renderProp => {

            return role == authService.getRole()? <Component {...renderProp} /> :  window.location.href = "https://localhost:3001"
        }}


    />

}

export default PublicRoute;