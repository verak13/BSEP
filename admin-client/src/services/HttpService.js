import axios from 'axios';

class HttpService {
    constructor(options = {}) {
        this.client = axios.create(options);

    }

    attachHeaders = headers => {
        Object.assign(this.client.defaults.headers, headers);
    }

    removeHeaders = headers => {
        headers.forEach(head => delete this.client.defaults.headers[head]);
    }
}

const options = { baseURL: 'http://localhost:8080' };
const httpService = new HttpService(options);

export default httpService;