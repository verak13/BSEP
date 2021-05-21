import ApiService from './ApiService';

const ENDPOINTS = {
    GET: '/auth/admin-portal-realm/users',
}

class DoctorService extends ApiService {

    addDoctor = async (payload) => {
        console.log(payload)
        const userRepresentation = {email: payload.email}
        const {data} = await this.apiClient.post(ENDPOINTS.GET, userRepresentation);
        //console.log(data);
        return data;
    }
}

const doctorService = new DoctorService();
export default doctorService;