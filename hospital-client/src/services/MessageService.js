import ApiService from './ApiService';

const ENDPOINTS = {
    POST: '/devices/by-page?page=:page&size=:size',
}

class MessageService extends ApiService {

    getMessages = async ({ pageSize, page, patient }) => {
        console.log('u servisu', pageSize)
        let uri = ENDPOINTS.POST.replace(":size", pageSize)
        uri = uri.replace(":page", page)
        const { data } = await this.apiClient.post(uri, JSON.stringify({
            patientId: patient
        }));

        return data;
    }
}

const messageService = new MessageService();
export default messageService;