import React from 'react'
import { Route, Redirect } from 'react-router-dom';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import PublicRoute from './PublicRoute';
import { LOGIN } from '../../assets/routes';

function PrivateRoute({ isAuthenticated, component: Component, ...rest }) {
    return (
        <Route {...rest}
            render={props => {
                return isAuthenticated ? <Component {...props} /> : <Redirect to={LOGIN} />
            }}
        />
    )
}

PublicRoute.propTypes = {
    isAuthenticated: PropTypes.bool
}

const mapStateToProps = state => {
    return {
        isAuthenticated: state.auth.isAuthenticated
    }
}

export default connect(mapStateToProps)(PrivateRoute);