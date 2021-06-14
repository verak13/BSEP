import requests
import datetime
import random
from time import sleep
from cryptography.hazmat.primitives.asymmetric import padding
from cryptography.hazmat.primitives import hashes, serialization



HOSPITAL_ENDPOINT = "https://localhost:8442/devices/send"
SERVER_CERT = './certs/root_ca.cer'
VALID_CERT = ('./certs/hospital_device2.cer', './certs/hospital_device2.pkcs8')
INVALID_CERT = ('./certs/revoke_me.cer', './certs/revoke_me.pkcs8')

def create_message():
    #1, 37, 120, 200, 160, 70
    id = 1
    temp = random.randint(36,41)
    pulse = random.randint(80,120)
    respiration = random.randint(10,19)
    pressureDiastolic = random.randint(80,150)
    pressureSystolic = random.randint(60,130)

    return f"{datetime.datetime.now()}|{id}|{temp}|{pulse}|{respiration}|{pressureDiastolic}|{pressureSystolic}".encode('utf-8')


def read_key(key_path=VALID_CERT[1]):
    with open(key_path, 'r') as f:
        private_key = serialization.load_pem_private_key(
        f.read().encode('utf-8'),
        password = None,)

        return private_key


def send_data_valid():
    priv_key = read_key()

    while True:

        message = create_message()
        signature = priv_key.sign(
            message,
            padding.PKCS1v15(),
            hashes.SHA256()
            )

        data = signature + message

        try:
            r = requests.post(HOSPITAL_ENDPOINT, data=data, verify=SERVER_CERT, cert=VALID_CERT)

            if r.status_code == 200:
                print(f"{datetime.datetime.now()}  Message sent succesfuly.")
            else:
                print(f"{datetime.datetime.now()}  Message sending failed. Status code: " + r.status_code)

        except Exception as error:
            print(error)

        sleep(4)


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
    #send_data_valid()
    send_data_invalid()
    #send_data_XSS()
