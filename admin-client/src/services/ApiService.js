import httpService from './HttpService';

class ApiService {
    constructor() {
        this.api = httpService;
        this.apiClient = this.api.client;
        this.keycloakClient = this.api.keycloakClient;
    }
}

export default ApiService;
