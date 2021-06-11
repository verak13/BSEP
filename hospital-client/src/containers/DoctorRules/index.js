import React, { useState, useEffect } from 'react'

import { connect } from 'react-redux';
import Typography from '@material-ui/core/Typography';
import NavBar from '../../components/NavBar';
import { Paper, Grid, Button, TextField, Divider, Container } from '@material-ui/core';
import { Formik, Form, Field } from 'formik';
import * as Yup from 'yup';
import { withFormikField } from '../../utils';
import Footer from '../../components/Footer';
import { addTemperatureRule, addPressureRule } from '../../store/actions/rulesActions';
import { makeStyles } from '@material-ui/core/styles';

const FormikTextField = withFormikField(TextField);

function DoctorRules(props) {

    const handleSubmitTemperature = values => {
       console.log(values);
       props.addTemperatureRule(values);
    }

    const handleSubmitPressure = values => {
        console.log(values);
        props.addPressureRule(values);
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
            style={{ margin: '0 auto', marginTop: 100, minHeight: '100vh' }}
        >
            <h1>Add Pressure Rule</h1>
        <Grid>
        <Formik
                initialValues={{ 
                    patientIdPressure: 0,
                    diastolic: 0.0,
                    systolic: 0.0
                 }}
                validationSchema={Yup.object().shape({
                    patientIdPressure: Yup.string().required('Required')
                    .matches(
                        /^$|[0-9]+$/,
                        "Must Be Integer"
                    ),
                    diastolic: Yup.string().required('Required')
                    .matches(
                        /^[0-9]+.[0-9]+$/,
                        "Must Be Double"
                    ),
                    systolic: Yup.string().required('Required')
                    .matches(
                        /^[0-9]+.[0-9]+$/,
                        "Must Be Double"
                    ),
                })
                }
                onSubmit={handleSubmitPressure}
            >
                <Form >
                    <Grid container spacing={2}>
                        <Grid item xs={12}>
                        </Grid>
                        <Grid item xs={6}>
                            <Field
                                component={FormikTextField}
                                type="text"
                                name="patientIdPressure"
                                variant="outlined"
                                required
                                fullWidth
                                label="Patient ID"
                            />
                        </Grid>
                        <Grid item xs={6}>
                            <Field
                                component={FormikTextField}
                                type="text"
                                name="diastolic"
                                variant="outlined"
                                required
                                fullWidth
                                label="Diastolic Blood Pressure Value"
                            />
                        </Grid>
                        <Grid item xs={6}>
                            <Field
                                component={FormikTextField}
                                type="text"
                                name="systolic"
                                variant="outlined"
                                required
                                fullWidth
                                label="Syastolic Blood Pressure Value"
                            />
                        </Grid>
                        <Grid container justify='center' style={{ margin: '10px 0px' }}>
                            <Button variant='contained' color='primary' type="submit">Add Pressure Alarm</Button>
                        </Grid>
                    </Grid>
                </Form>
            </Formik>
            
            </Grid>

            <h1>Add Temperature Rule</h1>
            <Grid>
            <Formik
                    initialValues={{ 
                        patientIdTemperature: 0,
                        value: 0.0,
                    }}
                    validationSchema={Yup.object().shape({
                        patientIdTemperature: Yup.string().required('Required')
                        .matches(
                            /^$|[0-9]+$/,
                            "Must Be Integer"
                        ),
                        value: Yup.string().required('Required')
                        .matches(
                            /^[0-9]+.[0-9]+$/,
                            "Must Be Double"
                        ),
                    })
                    }
                    onSubmit={handleSubmitTemperature}
                >
                    <Form >
                        <Grid container spacing={2}>
                            <Grid item xs={12}>
                            </Grid>
                            <Grid item xs={6}>
                                <Field
                                    component={FormikTextField}
                                    type="text"
                                    name="patientIdTemperature"
                                    variant="outlined"
                                    required
                                    fullWidth
                                    label="Patient ID"
                                />
                            </Grid>
                            <Grid item xs={6}>
                                <Field
                                    component={FormikTextField}
                                    type="text"
                                    name="value"
                                    variant="outlined"
                                    required
                                    fullWidth
                                    label="Value"
                                />
                            </Grid>
                            <Grid container justify='center' style={{ margin: '10px 0px' }}>
                                <Button variant='contained' color='primary' type="submit">Add High Temperature Alarm</Button>
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
    //requests: state.requests.requests
});

const mapDispatchToProps = {
  addTemperatureRule,
  addPressureRule
}

export default connect(mapStateToProps, mapDispatchToProps)(DoctorRules);

