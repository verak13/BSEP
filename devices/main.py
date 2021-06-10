import requests
import os
import random
from time import sleep, strftime
from datetime import datetime

HOSPITAL_ENDPOINT = "https://localhost:8442/devices/send"
SERVER_CERT = './certs/root_ca.cer'
VALID_CERT = ('./certs/hospital_device1.cer', './certs/hospital_device1.pkcs8')
INVALID_CERT = ('./certs/revoke_me.cer', './certs/revoke_me.pkcs8')

def send_data_valid():
    #message format: yyyy-MM-dd HH:mm|patientId|bodyTemperature|pulseRate|respirationRate|bloodPressureDiastolic|bloodPressureSystolic
    while True:
        message = datetime.now().strftime("%Y-%m-%d %H:%M:%S") + "|" + str(random.randint(1, 10)) + "|" + \
                  str(round(random.uniform(35, 43), 1)) + "|" + str(random.randint(50, 120)) + "|" + str(random.randint(10, 20)) + "|" + \
                  str(random.randint(60, 95)) + "|" + str(random.randint(100, 140))
        try:
            r = requests.post(HOSPITAL_ENDPOINT, data=message, verify=SERVER_CERT, cert=VALID_CERT)
            print(r.status_code)
        except Exception as error:
            print(error)
        sleep(5)


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
    #send_data_invalid()
    #send_data_XSS()
