import jwt_decode from 'jwt-decode';
const { default: ApiService } = require("./ApiService");

const ENDPOINTS = {
    LOGIN: 'auth/login',
    LOGOUT: '',
    PASSWORD_CHANGE: 'api/user/password/',
}

class AuthService extends ApiService {
    constructor() {
        super();
        this.init();
    }

    init = () => {
        const token = this.getToken();
        if (token)
            this.setAuthorizationHeader(token);


    }

    isAuthenticated = () => {

        const jwt = JSON.parse(localStorage.getItem('user'));
         if(jwt && jwt.accessToken){
            this.setAuthorizationHeader(jwt.accessToken);
            return true;
         }
         return false;
    }

    getRole = () => {
        const jwt = JSON.parse(localStorage.getItem('user'));
        let decoded ;
        let role = null;
        try {
            decoded = jwt_decode(jwt);
            role = decoded.realm_access.roles.filter(role => ['SUPER_ADMIN', 'HOSPITAL_ADMIN', 'DOCTOR'].includes(role))[0]
    
        } catch (error) {
            console.log(error)
            return null;
        }

        return role;
    }



    getEmail = () => {
        const jwt = JSON.parse(localStorage.getItem('user'));
        let decoded ;
        try {

            decoded = jwt_decode(jwt.access);

        } catch (error) {
            return null;
        }

        return decoded.email;
    }

    isExpired = ({ access }) => {
        if (!access)
            return null;

        const jwt = JSON.parse(atob(access.split('.')[1]));
        const exp = jwt && jwt.exp && jwt.exp * 1000;


        return Date.now() > exp;
    }

    setAuthorizationHeader = token => {
        this.api.attachHeaders({
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        });
    }
    destroySession = () => {
        localStorage.clear();
        this.api.removeHeaders(['Authorization']);
    }

    createSession = token => {
        localStorage.setItem('user', JSON.stringify(token));
        this.setAuthorizationHeader(token);
    }

   

    login = data => {
        this.createSession(data);
        return data;
    }

    changePassword = async payload => {
        const {data} = await this.apiClient.put(ENDPOINTS.PASSWORD_CHANGE, payload);
        return data;
    }


    logout = () => {
        this.destroySession();
        return { status: 'success' };
    }

    getToken = () => {
        const user = localStorage.getItem('user');
        return user ? JSON.parse(user).access : undefined;
    }
}

const authService = new AuthService();
export default authService; 