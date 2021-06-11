import { newError, newSuccess } from "../actions/notificationActions";
import { call, put } from 'redux-saga/effects';
import messageService from "../../services/MessageService";
import { setMessages } from '../actions/messageActions';
import { MESSAGES } from "../../assets/routes";
import { push } from "connected-react-router";

export function* getMessages(action){
    try {
        const response = yield call(messageService.getMessages, action.payload);
        console.log('ooo', response);
        yield put(setMessages(response));

    } catch (error) {
        console.log(error);
        yield put(newError("Can't get messages."));
        yield put(push(MESSAGES));
    }
}