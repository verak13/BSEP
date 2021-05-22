import axios from 'axios';

class HttpService {
    constructor(options = {}) {
        axios.defaults.headers.common['Content-Type'] = 'application/json' // for all requests
        this.client = axios.create(options);

    }

    attachHeaders = headers => {
        Object.assign(this.client.defaults.headers, headers);
    }

    removeHeaders = headers => {
        headers.forEach(head => delete this.client.defaults.headers[head]);
    }
}

const options = { baseURL: 'https://localhost:8443' };
const httpService = new HttpService(options);

export default httpService;