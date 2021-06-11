import ApiService from './ApiService';

const ENDPOINTS = {
    GET: '/patients/by-page?page=:page&size=:size',
}

class PatientService extends ApiService {

    getPatients = async ({ pageSize, page }) => {
        console.log('u servisu', pageSize)
        let uri = ENDPOINTS.GET.replace(":size", pageSize)
        uri = uri.replace(":page", page)
        const { data } = await this.apiClient.get(uri);
        return data;
    }
}

const patientService = new PatientService();
export default patientService;