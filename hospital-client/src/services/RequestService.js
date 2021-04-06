import ApiService from './ApiService';

const ENDPOINTS = {
    GET: '/certificate-request',
}

class RequestService extends ApiService {
    
    getRequests = async () => {
        const {data} = await this.apiClient.get(ENDPOINTS.GET);
        console.log(data);
        return data;
    }

    addRequest = async (payload) => {
        const {data} = await this.apiClient.post(ENDPOINTS.GET, payload);
        console.log(data);
        return data;
    }
}

const requestService = new RequestService();
export default requestService;