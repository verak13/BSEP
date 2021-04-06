import { combineReducers } from 'redux';
import { connectRouter } from 'connected-react-router';
import AuthReducer from './AuthReducer';
import NotificationReducer from './NotificationReducer';
import RequestReducer from './RequestReducer';
import CertificateReducer from './CertificateReducer';

export default history => 
    combineReducers({
        auth: AuthReducer,
        notification: NotificationReducer,
        requests: RequestReducer,   
        certificates: CertificateReducer,
        router: connectRouter(history),
    });
