import ApiService from './ApiService';

const ENDPOINTS = {
    GET: '/certificate',
}

class CertificateService extends ApiService {
    
    addCertificate = async (payload) => {
        const {data} = await this.apiClient.post(ENDPOINTS.GET, payload);
        console.log(data);
        return data;
    }

    getCertificates = async () => {
        const {data} = await this.apiClient.get(ENDPOINTS.GET);
        console.log(data);
        return data;
        
    }

    revokeCertificate = async (payload) => {
        const {data} = await this.apiClient.put(ENDPOINTS.GET, payload);
        return data;
    }
}

const requestService = new CertificateService();
export default requestService;