import requests

ENDPOINT = "https://localhost:8444/devices/send"
SERVER_CERT = ('./certs/hospital_server.cer', )


def send_data():
    message = "HELLO"
    try:
        r = requests.post(ENDPOINT, message, verify='./certs/', cert=('./certs/hospital_device1.cer', './certs/hospital_device1.pkcs8'))
        print(r.status_code)
    except Exception as error:
        print(error)

if __name__ == '__main__':
    send_data()