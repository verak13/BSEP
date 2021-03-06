import React, { useState, useEffect } from 'react'

import { connect } from 'react-redux';
import Typography from '@material-ui/core/Typography';
import NavBar from '../../components/NavBar';
import { Paper, Grid, Button, TextField, Divider, Container } from '@material-ui/core';
import { Formik, Form, Field } from 'formik';
import * as Yup from 'yup';
import { withFormikField } from '../../utils';
import Footer from '../../components/Footer';
import { addRequest } from '../../store/actions/requestActions';
import { makeStyles } from '@material-ui/core/styles';
import authService from '../../services/AuthService';

const useStyles = makeStyles({
    table: {
      minWidth: 650,
    },
  });

  const FormikTextField = withFormikField(TextField);

function Requests(props) {

    const classes = useStyles();
    const initialState = {
        requestId: 0,
        cRLSign: false,
        dataEncipherment: false,
        decipherOnly: false,
        digitalSignature: false,
        encipherOnly: false,
        keyAgreement: false,
        keyCertSign: false,
        keyEncipherment: false,
        nonRepudiation: false,
        email: authService.getRole() == 'SUPER_ADMIN'? 'root-ca' : 'hospital-server'
      };

    const handleSubmit = values => {
        console.log({...values, id:999, userId:999});
        props.addRequest(values);
        console.log(values);
    }


    useEffect(() => {
    }, []);
  
    return (
        <div>
        <NavBar />
        <Grid
            container
            component={Paper}
            direction="column"
            spacing={2}
            alignItems="center"
            style={{ margin: 'auto', padding:'30px', width: '80%', height: '70%',marginTop: 100, minHeight: '100vh' }}
        >
            <h1>Make Request</h1>
        <Grid>
        <Formik
                initialValues={{ commonName: '', countryName: '', organization:'', organizationUnitName:'', stateName:'', localityName:'',   email: authService.getRole() == 'SUPER_ADMIN'? 'root-ca' : 'hospital-server'  }}
                validationSchema={Yup.object().shape({
                    commonName: Yup.string().min(2, 'Too Short!').max(20, 'Too Long!').required('Required')
                    .matches(
                        /^$|[a-zA-Z ]+$/,
                        "Must Not Contain Special Characters"
                    ),
                    countryName: Yup.string().min(2, 'Too Short!').max(20, 'Too Long!').required('Required')
                    .matches(
                        /^$|[a-zA-Z ]+$/,
                        "Must Not Contain Special Characters"
                    ),
                    organization: Yup.string().min(2, 'Too Short!').max(20, 'Too Long!').required('Required')
                    .matches(
                        /^$|[a-zA-Z ]+$/,
                        "Must Not Contain Special Characters"
                    ),
                    organizationUnitName: Yup.string().min(2, 'Too Short!').max(20, 'Too Long!').required('Required')
                    .matches(
                        /^$|[a-zA-Z ]+$/,
                        "Must Not Contain Special Characters"
                    ),
                    stateName: Yup.string().min(2, 'Too Short!').max(20, 'Too Long!').required('Required')
                    .matches(
                        /^$|[a-zA-Z ]+$/,
                        "Must Not Contain Special Characters"
                    ),
                    localityName: Yup.string().min(2, 'Too Short!').max(20, 'Too Long!').required('Required')
                    .matches(
                        /^$|[a-zA-Z ]+$/,
                        "Must Not Contain Special Characters"
                    ),
                })
                }
                onSubmit={handleSubmit}
            >
                <Form >
                    <Grid container spacing={2}>
                        <Grid item xs={12}>
                            
                                


                        </Grid>
                        <Grid item xs={6}>

                            <Field
                                component={FormikTextField}
                                type="text"
                                name="commonName"
                                variant="outlined"
                                required
                                fullWidth
                                label="Common Name"
                            />
                        </Grid>
                        <Grid item xs={6}>
                            <Field
                                component={FormikTextField}
                                type="text"
                                name="countryName"
                                variant="outlined"
                                required
                                fullWidth
                                label="Country Name"

                            />
                        </Grid>
                      
                        <Grid item xs={6}>

                            <Field
                                component={FormikTextField}
                                type="text"
                                name="organization"
                                variant="outlined"
                                required
                                fullWidth
                                label="Organization"
                            />
                        </Grid>
                        <Grid item xs={6}>
                            <Field
                                component={FormikTextField}
                                type="text"
                                name="organizationUnitName"
                                variant="outlined"
                                required
                                fullWidth
                                label="Organization Unit"

                            />
                        </Grid>
                      
                        <Grid item xs={6}>

                            <Field
                                component={FormikTextField}
                                type="text"
                                name="stateName"
                                variant="outlined"
                                required
                                fullWidth
                                label="State Name"
                            />
                        </Grid>
                        <Grid item xs={6}>
                            <Field
                                component={FormikTextField}
                                type="text"
                                name="localityName"
                                variant="outlined"
                                required
                                fullWidth
                                label="Locality Name"

                            />
                        </Grid>

    
                      


                        
                        <Grid container justify='center' style={{ margin: '10px 0px' }}>
                            <Button variant='contained' color='primary' type="submit">Make Request</Button>
                               

                        </Grid>
                    </Grid>
                </Form>
            </Formik>
            
            </Grid>
        </Grid>



       


        <Footer />
    </div >
    )

}

const mapStateToProps = state => ({
    requests: state.requests.requests
});

const mapDispatchToProps = {
  addRequest
}

export default connect(mapStateToProps, mapDispatchToProps)(Requests);