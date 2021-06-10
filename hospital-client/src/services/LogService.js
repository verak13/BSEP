import ApiService from './ApiService';

const ENDPOINTS = {
    GET: '/logs/by-page?page=:page&size=:size',
}

class LogService extends ApiService {

    getLogs = async ({ pageSize, page }) => {
        console.log('u servisu', pageSize)
        let uri = ENDPOINTS.GET.replace(":size", pageSize)
        uri = uri.replace(":page", page)
        const { data } = await this.apiClient.post(uri, {
            from : null,
            to: null,
            ip : "",
            source: "",
            type: "",
            severity: "",
            username: "",
            message: ""
        });

        return data;
    }
}

const logService = new LogService();
export default logService;