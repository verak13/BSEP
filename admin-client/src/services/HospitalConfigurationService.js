import ApiService from './ApiService';

const ENDPOINT_HOSPITALS = {
    GET: '/configuration/hospitals/by-page?page=:page&size=:size',
}

const ENDPOINT_ADD_CONFIG = {
    GET: '/configuration',
}

class HospitalConfigurationService extends ApiService {
    
    getHospitals = async ({ pageSize, page }) => {
        console.log('u servisu', pageSize)
        let uri = ENDPOINT_HOSPITALS.GET.replace(":size", pageSize)
        uri = uri.replace(":page", page)
        const { data } = await this.apiClient.get(uri);
        return data;
    }

    addConfig = async (payload) => {
        const {data} = await this.apiClient.post(ENDPOINT_ADD_CONFIG.GET, payload);
        return data;
    }
}

const hospitalConfigurationService = new HospitalConfigurationService();
export default hospitalConfigurationService;


