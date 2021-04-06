import React from 'react';
import { Route, Redirect } from 'react-router-dom';
import { connect } from 'react-redux';
import { compose } from 'redux'
import { REQUESTS } from '../../assets/routes';

function PublicRoute({ isAuthenticated, component: Component, ...rest }) {
    return <Route   {...rest}
        render={renderProp => {
            return isAuthenticated ? <Redirect to={REQUESTS} /> : <Component {...renderProp} />
        }}


    />

}

const mapStateToProps = state => {
    return {
        isAuthenticated: state.auth.isAuthenticated,
    }
}

export default connect(mapStateToProps)(PublicRoute);