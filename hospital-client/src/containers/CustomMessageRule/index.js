import React, { useState, useEffect } from 'react'

import { connect } from 'react-redux';
import Typography from '@material-ui/core/Typography';
import NavBar from '../../components/NavBar';
import { Paper, Grid, Button, TextField, Divider, Container } from '@material-ui/core';
import { Formik, Form, Field } from 'formik';
import * as Yup from 'yup';
import { withFormikField } from '../../utils';
import Footer from '../../components/Footer';
import { addCustomMessageRule } from '../../store/actions/rulesActions';
import { makeStyles } from '@material-ui/core/styles';
import CircularProgress from '@material-ui/core/CircularProgress';

const FormikTextField = withFormikField(TextField);

function CustomMessageRule(props) {
    const [loader, setLoader] = React.useState(false);

    const handleSubmit = values => {
       console.log(values);
       props.addCustomMessageRule(values);
       setLoader(true)
    }

    useEffect(() => {
        setLoader(false);
    }, [props.success])
  
    return (
        <div>
        <NavBar />
        <Grid
            container
            component={Paper}
            direction="column"
            spacing={2}
            alignItems="center"
            style={{ margin: '0 auto', padding: '20px',marginTop: 100, minHeight: '100vh' , width: '80%'}}
        >
            <h1>Add Custom Message Rule</h1>
        <Grid>
        <Formik
                initialValues={{ 
                    description: '',
                    minTemperature: '',
                    maxTemperature: '',
                    minPulse: '',
                    maxPulse: '',
                    minRespiration: '',
                    maxRespiration: '',
                    minSystolic: '',
                    maxSystolic: '',
                    minDiastolic: '',
                    maxDiastolic: ''
                 }}
                validationSchema={Yup.object().shape({
                    description: Yup.string().required('Required')
                    .matches(
                        /^[a-zA-Z0-9 ]+$/,
                        "Must Be Text"
                    ),
                    minTemperature: Yup.string()
                    .matches(
                        /^$|^[0-9]+.[0-9]+$/,
                        "Must Be Double"
                    ),
                    maxTemperature: Yup.string()
                    .matches(
                        /^$|^[0-9]+.[0-9]+$/,
                        "Must Be Double"
                    ),
                    minPulse: Yup.string()
                    .matches(
                        /^$|^[0-9]+.[0-9]+$/,
                        "Must Be Double"
                    ),
                    maxPulse: Yup.string()
                    .matches(
                        /^$|^[0-9]+.[0-9]+$/,
                        "Must Be Double"
                    ),
                    minRespiration: Yup.string()
                    .matches(
                        /^$|^[0-9]+.[0-9]+$/,
                        "Must Be Double"
                    ),
                    maxRespiration: Yup.string()
                    .matches(
                        /^$|^[0-9]+.[0-9]+$/,
                        "Must Be Double"
                    ),
                    minSystolic: Yup.string()
                    .matches(
                        /^$|^[0-9]+.[0-9]+$/,
                        "Must Be Double"
                    ),
                    maxSystolic: Yup.string()
                    .matches(
                        /^$|^[0-9]+.[0-9]+$/,
                        "Must Be Double"
                    ),
                    minDiastolic: Yup.string()
                    .matches(
                        /^$|^[0-9]+.[0-9]+$/,
                        "Must Be Double"
                    ),
                    maxDiastolic: Yup.string()
                    .matches(
                        /^$|^[0-9]+.[0-9]+$/,
                        "Must Be Double"
                    ),
                })
                }
                onSubmit={handleSubmit}
            >
                <Form >
                    <Grid container spacing={2}>
                        <Grid item xs={12}>
                        </Grid>
                        <Grid item xs={12}>
                            <Field
                                component={FormikTextField}
                                type="text"
                                name="description"
                                variant="outlined"
                                required
                                fullWidth
                                label="Description"
                            />
                        </Grid>
                        <Grid item xs={6}>
                            <Field
                                component={FormikTextField}
                                type="text"
                                name="minTemperature"
                                variant="outlined"
                                fullWidth
                                label="Minimal Normal Temperature"
                            />
                        </Grid>
                        <Grid item xs={6}>
                            <Field
                                component={FormikTextField}
                                type="text"
                                name="systolic"
                                variant="outlined"
                                fullWidth
                                label="Maximal Normal Temperature"
                            />
                        </Grid>
                        <Grid item xs={6}>
                            <Field
                                component={FormikTextField}
                                type="text"
                                name="minPulse"
                                variant="outlined"
                                fullWidth
                                label="Minimal Normal Pulse Rate"
                            />
                        </Grid>
                        <Grid item xs={6}>
                            <Field
                                component={FormikTextField}
                                type="text"
                                name="maxPulse"
                                variant="outlined"
                                fullWidth
                                label="Maximal Normal Pulse Rate"
                            />
                        </Grid>
                        <Grid item xs={6}>
                            <Field
                                component={FormikTextField}
                                type="text"
                                name="minRespiration"
                                variant="outlined"
                                fullWidth
                                label="Minimal Normal Respiration Rate"
                            />
                        </Grid>
                        <Grid item xs={6}>
                            <Field
                                component={FormikTextField}
                                type="text"
                                name="maxRespiration"
                                variant="outlined"
                                fullWidth
                                label="Maximal Normal Respiration Rate"
                            />
                        </Grid>
                        <Grid item xs={6}>
                            <Field
                                component={FormikTextField}
                                type="text"
                                name="minSystolic"
                                variant="outlined"
                                fullWidth
                                label="Minimal Normal Systolic Pressure"
                            />
                        </Grid>
                        <Grid item xs={6}>
                            <Field
                                component={FormikTextField}
                                type="text"
                                name="maxSystolic"
                                variant="outlined"
                                fullWidth
                                label="Maximal Normal Systolic Pressure"
                            />
                        </Grid>
                        <Grid item xs={6}>
                            <Field
                                component={FormikTextField}
                                type="text"
                                name="minDiastolic"
                                variant="outlined"
                                fullWidth
                                label="Minimal Normal Diastolic Pressure"
                            />
                        </Grid>
                        <Grid item xs={6}>
                            <Field
                                component={FormikTextField}
                                type="text"
                                name="maxDiastolic"
                                variant="outlined"
                                fullWidth
                                label="Maximal Normal Diastolic Pressure"
                            />
                        </Grid>
                        <Grid container justify='center' style={{ margin: '10px 0px' }}>
                            <Button variant='contained' color='primary' type="submit">Add Custom Message Alarm</Button>
                            {loader &&    <CircularProgress style={{marginTop:'5px'}}/>}
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
   success : state.notification.type
});

const mapDispatchToProps = {
  addCustomMessageRule
}

export default connect(mapStateToProps, mapDispatchToProps)(CustomMessageRule);