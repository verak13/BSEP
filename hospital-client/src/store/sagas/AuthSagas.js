import { call, put } from 'redux-saga/effects';
import authService from '../../services/AuthService';
import { authUser } from '../actions/authActions';
import { newError, newSuccess } from '../actions/notificationActions';

export function* login({ payload }) {
    try {
        const response = yield call(authService.login, payload);

        // ako budem dodavao podatke u jwt
        // const decoded = jwt_decode(response);
        yield put(newSuccess('Logged in successfuly'));
        yield put(authUser(true));
    } catch (error) {
        console.log(error.response.status)
        if (error.response.status == 401) {
            yield put(newError("Can't login with those credentials."));
        }
        else {
            yield put(newError('Couldn\'t connect to the server'));
        }

    }
}


export function* logout() {
    authService.destroySession();
    yield put(authUser(false));
    yield put(newSuccess('Logged out successfuly'));
}


export function* changePasswordSaga({payload}){
    try{
        const res = yield call(authService.changePassword, payload);
        yield put(newSuccess('Password updated successfuly'));

    }catch(error){
        yield put(newError(error.response.data.detail));
    }
}