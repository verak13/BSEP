import ApiService from './ApiService';

const ENDPOINTS = {
    GET: '/message-alarms/by-page?page=:page&size=:size',
}

class MessageAlarmService extends ApiService {

    getMessageAlarms = async ({ pageSize, page }) => {
        console.log('u servisu', pageSize)
        let uri = ENDPOINTS.GET.replace(":size", pageSize)
        uri = uri.replace(":page", page)
        const { data } = await this.apiClient.get(uri);
        return data;
    }
}

const messageAlarmService = new MessageAlarmService();
export default messageAlarmService;