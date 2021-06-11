import { newError, newSuccess } from "../actions/notificationActions";
import { call, put } from 'redux-saga/effects';
import rulesService from "../../services/RulesService";
import { addTemperatureRule, addPressureRule } from '../actions/rulesActions';
import { ADD_PRESSURE_RULE, ADD_TEMPERATURE_RULE } from "../../assets/routes";
import { push } from "connected-react-router";


export function* pressureRuleAdd({payload}){
    try{
        const response = yield call(rulesService.addPressureRule, payload);
        yield put(newSuccess("Rule made."));

    }catch(error){
        console.log(error);
        yield put(newError("Can't make rule."));
    }
}

export function* temperatureRuleAdd({payload}){
    try{
        const response = yield call(rulesService.addTemperatureRule, payload);
        yield put(newSuccess("Rule made."));

    }catch(error){
        console.log(error);
        yield put(newError("Can't make rule."));
    }
}