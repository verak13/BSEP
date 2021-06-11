import ApiService from './ApiService';

const ENDPOINTS = {
    GENERATE: '/report/generate',
    GET: '/report'
}

class ReportService extends ApiService {

    generateReport = async () => {
        const { data } = await this.apiClient.get(ENDPOINTS.GENERATE);

        return data;
    }

    getReports = async () => {
        const { data } = await this.apiClient.get(ENDPOINTS.GET);

        return data;
    }
}

const reportService = new ReportService();
export default reportService;