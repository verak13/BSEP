import axios from 'axios';
import https from 'https';

const CERT = `-----BEGIN CERTIFICATE-----
MIIC0DCCAbigAwIBAgIEYKfsqDANBgkqhkiG9w0BAQsFADAaMRgwFgYDVQQDDA9o
b3NwaXRhbC1zZXJ2ZXIwHhcNMjEwNTIxMTcyMzUyWhcNMjIwNTIxMTcyMzUyWjAa
MRgwFgYDVQQDDA9ob3NwaXRhbC1jbGllbnQwggEiMA0GCSqGSIb3DQEBAQUAA4IB
DwAwggEKAoIBAQCD2VjpFG9xT4o5HcnGPRvScohrH8BDKeezadYuckUbw80MQTxA
RsFcoXgbU4VlKL4W7+EOEeA+hctnOQnB/ep3KfjZdvWSE8NcfV/2Ji7FA/gcYfgS
KyzY0gj/gYTPMuUHIRXSD2n3VIcc/1ODDOcw0Mjj1aRF0STVlJsT4uA1UWUW+eTu
T8KL07flQ+mw+H+9TzuW908Z0Sp6xWitfDF9fbsnHUd7uu9jxjMV9+V/i0eveGVp
6uZ5g+YWi57Y9ixUqRlIaN5IxyVadVPWx2EQKGflWT52BSsfmraouZ+Cp6sKlBzl
0YS7TY2qsgOE4JitnY3MhmMc31KuY8ukhUW1AgMBAAGjHjAcMBoGA1UdEQQTMBGC
CWxvY2FsaG9zdIcEfwAAATANBgkqhkiG9w0BAQsFAAOCAQEAgtQFxZmQDXtgiBFc
qRNrJD43/i22TgC8sEbglkmVAmObVDJQt8L+YwAMf5GiGaTGOSjMdqbP31LjnMmq
Oe19BTB4Px9Mr+2uQFhj9XAu5sMIYnc1Yk8p0m4FjP0ZgbCYTk4NQjulv00DFony
oZCZPUpYArinkSShMPNhObvi+UPYZV0nKLJOSbGu/zNiDkBIex8W/3JTRFDU2y0k
7R/UlRBU8sr0dmYyTf7ZFdDpBOhbWb1hwbahalIZerDuCNxNr+D83uKXGIK/x2RL
kHzWd9KYdK/fmBm4c6pbr+0rOMvGNEOEwOIhaZJX80yeclbCWkvw6GemC7II55G8
YR7OWQ==
-----END CERTIFICATE-----`



class HttpService {
    constructor(options = {}) {
        const opts = {                    
                    rejectUnauthorized: false,                
                    };


        const newAgent = new https.Agent(opts);
        options.httpsAgent = {};

        Object.assign(options.httpsAgent, newAgent);
        
        options.cert = CERT;

        this.client = axios.create(options);
    }

    attachHeaders = headers => {
        Object.assign(this.client.defaults.headers, headers);
    }

    removeHeaders = headers => {
        headers.forEach(head => delete this.client.defaults.headers[head]);
    }
}

const options = { baseURL: 'https://localhost:8442' };
const httpService = new HttpService(options);

export default httpService;