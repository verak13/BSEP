import { RotateLeft } from '@material-ui/icons';
import ApiService from './ApiService';

const ENDPOINTS = {
    GET: '/roles/:role/users',
}

class UserService extends ApiService {
    
    getUsers = async (role) => {
        console.log('service', role)
        const { data } = await this.keycloakClient.get(ENDPOINTS.GET.replace(':role', role));
        console.log(data);
        return data;
    }

}

const userService = new UserService();
export default userService;