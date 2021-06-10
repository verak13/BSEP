import { combineReducers } from 'redux';
import { connectRouter } from 'connected-react-router';
import AuthReducer from './AuthReducer';
import NotificationReducer from './NotificationReducer';
import RequestReducer from './RequestReducer';
import LogReducer from './LogReducer';

export default history =>
    combineReducers({
        auth: AuthReducer,
        notification: NotificationReducer,
        requests: RequestReducer,
        logs: LogReducer,
        router: connectRouter(history),
    });
