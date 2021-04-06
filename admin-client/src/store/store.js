import createSagaMiddleware from 'redux-saga';
import { createStore, applyMiddleware } from 'redux';
import { composeWithDevTools } from 'redux-devtools-extension';
import { routerMiddleware } from 'connected-react-router';
import { createBrowserHistory } from 'history';

import rootSaga from './sagas';
import rootReducer from './reducers';

const sagaMiddleWare = createSagaMiddleware();
const history = createBrowserHistory();
const store = createStore(
    rootReducer(history),
    composeWithDevTools(applyMiddleware(routerMiddleware(history), sagaMiddleWare))
);

sagaMiddleWare.run(rootSaga);

export default store;