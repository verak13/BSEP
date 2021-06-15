import ApiService from './ApiService';

const ENDPOINT_BLACKLISTED = {
    GET: '/alarms/blacklisted-ip/by-page?page=:page&size=:size',
}

const ENDPOINT_BRUTEFORCE = {
    GET: '/alarms/bruteforce-login/by-page?page=:page&size=:size',
}

const ENDPOINT_ERROR = {
    GET: '/alarms/error-log/by-page?page=:page&size=:size',
}

const ENDPOINT_INACTIVE = {
    GET: '/alarms/inactive-user/by-page?page=:page&size=:size',
}

const ENDPOINT_CUSTOM = {
    GET: '/alarms/custom-log/by-page?page=:page&size=:size',
}

class AlarmService extends ApiService {

    getAlarmsBlacklisted = async ({ pageSize, page }) => {
        console.log('u servisu', pageSize)
        let uri = ENDPOINT_BLACKLISTED.GET.replace(":size", pageSize)
        uri = uri.replace(":page", page)
        const { data } = await this.apiClient.get(uri);
        return data;
    }

    getAlarmsBruteforce = async ({ pageSize, page }) => {
        console.log('u servisu', pageSize)
        let uri = ENDPOINT_BRUTEFORCE.GET.replace(":size", pageSize)
        uri = uri.replace(":page", page)
        const { data } = await this.apiClient.get(uri);
        return data;
    }

    getAlarmsError = async ({ pageSize, page }) => {
        console.log('u servisu', pageSize)
        let uri = ENDPOINT_ERROR.GET.replace(":size", pageSize)
        uri = uri.replace(":page", page)
        const { data } = await this.apiClient.get(uri);
        return data;
    }

    getAlarmsInactive = async ({ pageSize, page }) => {
        console.log('u servisu', pageSize)
        let uri = ENDPOINT_INACTIVE.GET.replace(":size", pageSize)
        uri = uri.replace(":page", page)
        const { data } = await this.apiClient.get(uri);
        return data;
    }

    getAlarmsCustom = async ({ pageSize, page }) => {
        console.log('u servisu', pageSize)
        let uri = ENDPOINT_CUSTOM.GET.replace(":size", pageSize)
        uri = uri.replace(":page", page)
        const { data } = await this.apiClient.get(uri);
        return data;
    }
}

const alarmService = new AlarmService();
export default alarmService;