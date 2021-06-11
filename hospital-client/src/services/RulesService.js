import ApiService from './ApiService';

const ENDPOINT_TEMPERATURE_RULE = {
    GET: '/rules/high-temperature',
}

const ENDPOINT_PRESSURE_RULE = {
    GET: '/rules/blood-pressure',
}

class RulesService extends ApiService {
    
    addTemperatureRule = async (payload) => {
        const {data} = await this.apiClient.post(ENDPOINT_TEMPERATURE_RULE.GET, payload);
        return data;
    }

    addPressureRule = async (payload) => {
        const {data} = await this.apiClient.post(ENDPOINT_PRESSURE_RULE.GET, payload);
        return data;
    }
}

const rulesService = new RulesService();
export default rulesService;