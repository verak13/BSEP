import ApiService from './ApiService';

const ENDPOINTS = {
    GET: '/logs/by-page?page=:page&size=:size',
}

class LogService extends ApiService {

    getLogs = async ({ pageSize, page, search }) => {
        let uri = ENDPOINTS.GET.replace(":size", pageSize)
        uri = uri.replace(":page", page)
        const { data } = await this.apiClient.post(uri, search);

        return data;
    }
}

const logService = new LogService();
export default logService;