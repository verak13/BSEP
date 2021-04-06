import { ADD_CERTIFICATE, GET_CERTIFICATES, SET_CERTIFICATES, REVOKE_CERTIFICATE } from "./actionTypes";

export const addCertificate = payload => ({
    type: ADD_CERTIFICATE,
    payload
})


export const revokeCertificate = payload => ({
    type: REVOKE_CERTIFICATE,
    payload
})

export const getCertificates = () => ({
    type: GET_CERTIFICATES,
    
})

export const setCertificates = (payload) => ({
    type: SET_CERTIFICATES,
    payload
})