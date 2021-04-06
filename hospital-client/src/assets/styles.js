import Background from '../assets/background-image.jpg'
import { PRIMARY_COLOR } from './constants';

export const homePageStyle = {
    body: {
        backgroundImage: `url(${Background})`, backgroundPosition: 'center',
        backgroundSize: 'cover',
        backgroundRepeat: 'no-repeat',        
        height: '1200px',
        width:'100%',
        padding:0,
        
    },
    button: {
        "&:hover": {
            background: PRIMARY_COLOR,
        }, fontSize: '18px', padding: '20px 40px', marginTop: 10, backgroundColor: '#000C66', opacity: '90%'
    }
};  