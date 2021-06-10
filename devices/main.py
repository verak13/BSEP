import requests
import json
from cryptography import x509
from cryptography.hazmat.primitives.asymmetric import padding
from cryptography.hazmat.primitives import hashes

HOSPITAL_ENDPOINT = "https://localhost:8442/devices/send"
SERVER_CERT = './certs/root_ca.cer'
VALID_CERT = ('./certs/hospital_device3.cer', './certs/hospital_device3.pkcs8')
INVALID_CERT = ('./certs/revoke_me.cer', './certs/revoke_me.pkcs8')


def create_message(id, temp, pulse, respiration, pressure, heartRate):
    return json.dumps({ "id": id, "temperature": temp, "pulse": pulse, "respirationRate": respiration,
            "bloodPressure": pressure, "heartRate": heartRate })

def read_key(cer_path = VALID_CERT[0]):
    with open(cer_path, 'r') as f:
        cert = x509.load_pem_x509_certificate(f.read().encode('utf-8'))
        return cert.public_key()

def send_data_valid():
    message = create_message(1, 37, 120, 200, 160, 70)
    pub_key = read_key()
    encrypted = pub_key.encrypt(
        message.encode('utf-8'),
        padding.OAEP(
            mgf=padding.MGF1(algorithm=hashes.SHA256()),
            algorithm=hashes.SHA256(),
            label=None
        ))

    print(encrypted)

    try:
        r = requests.post(HOSPITAL_ENDPOINT, data=encrypted, verify=SERVER_CERT, cert=VALID_CERT)
        print(r.status_code)
        
    except Exception as error:
        print(error)


def send_data_invalid():
    message = "SHOULD NOT PASS"
    try:
        r = requests.post(HOSPITAL_ENDPOINT, data=message, verify=SERVER_CERT, cert=INVALID_CERT)
        print(r.status_code)
    except Exception as error:
        print(error)


def send_data_XSS():
    message = "<script>alert('hahah')</script>"
    try:
        r = requests.post(HOSPITAL_ENDPOINT, data=message, verify=SERVER_CERT, cert=VALID_CERT)
        print(r.status_code)
    except Exception as error:
        print(error)


if __name__ == '__main__':
    send_data_valid()
    send_data_invalid()
    send_data_XSS()
