import ApiService from './ApiService';

const ENDPOINTS = {
    GET: '/message/by-page?page=:page&size=:size',
}

class MessageService extends ApiService {

    getMessages = async ({ pageSize, page, patient }) => {
        console.log('u servisu', pageSize)
        let uri = ENDPOINTS.POST.replace(":size", pageSize)
        uri = uri.replace(":page", page)
        const { data } = await this.apiClient.post(uri, {
            patientId: patient
        });

        return data;
    }
}

const messageService = new MessageService();
export default messageService;