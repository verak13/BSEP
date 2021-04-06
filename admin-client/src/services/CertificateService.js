import ApiService from './ApiService';

const ENDPOINTS = {
    POST: '/certificate',
}

class CertificateService extends ApiService {
    
    addCertificate = async (payload) => {
        const {data} = await this.apiClient.post(ENDPOINTS.POST, payload);
        console.log(data);
        return data;
    }
}

const requestService = new CertificateService();
export default requestService;