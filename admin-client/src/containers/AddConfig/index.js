import React, { useState, useEffect } from 'react'

import { connect } from 'react-redux';
import Typography from '@material-ui/core/Typography';
import NavBar from '../../components/NavBar';
import { Paper, Grid, Button, TextField, Divider, Container } from '@material-ui/core';
import { Formik, Form, Field } from 'formik';
import * as Yup from 'yup';
import { withFormikField } from '../../utils';
import Footer from '../../components/Footer';
import { addConfig } from '../../store/actions/hospitalConfigurationActions';
import { makeStyles } from '@material-ui/core/styles';
import Select from '@material-ui/core/Select';
import MenuItem from '@material-ui/core/MenuItem';

const SIMULATORS = ["SIMULATOR1", "SIMULATOR2", "SIMULATOR3"];

const FormikTextField = withFormikField(TextField);



function AddConfig(props) {

    const [file, setFile] = React.useState('');

    const SelectField = () => (
        <Select
        labelId="demo-simple-select-label"
        id="demo-simple-select"
        value={file}
        variant="outlined"
        style={{width: '100%'}}
        onChange={e => { console.log('ddaaa'); setFile(e.target.value)}}
      >
        <MenuItem value={"SIMULATOR1"}>SIMULATOR1</MenuItem>
        <MenuItem value={"SIMULATOR2"}>SIMULATOR2</MenuItem>
      </Select>
    )

    const handleSubmit= values => {
       values.file = file;
       props.addConfig(values);
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
            <h1>Add Simulator</h1>
        <Grid>
        <Formik
                initialValues={{ 
                    hospitalId: 0,
                    file: file,
                    interval: 0,
                    regexp: ''
                 }}
                validationSchema={Yup.object().shape({
                    hospitalId: Yup.string().required('Required')
                    .matches(
                        /^$|[0-9]+$/,
                        "Must Be Integer"
                    ),
                    interval: Yup.string().required('Required')
                    .matches(
                        /^$|[0-9]+$/,
                        "Must Be Integer"
                    ),
                    regexp: Yup.string().required('Required')
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
                                name="hospitalId"
                                variant="outlined"
                                required
                                fullWidth
                                label="Hospital ID"
                            />
                        </Grid>
                        <Grid item xs={6}>
                        <Field
                                component={SelectField}
                                type="text"
                                name="file"
                                variant="outlined"
                                required
                                fullWidth
                                label="Simulator"
                            />
                        </Grid>
                        <Grid item xs={6}>
                            <Field
                                component={FormikTextField}
                                type="text"
                                name="interval"
                                variant="outlined"
                                required
                                fullWidth
                                label="Interval"
                            />
                        </Grid>
                        <Grid item xs={6}>
                            <Field
                                component={FormikTextField}
                                type="text"
                                name="regexp"
                                variant="outlined"
                                required
                                fullWidth
                                label="Regular Expression"
                            />
                        </Grid>
                        <Grid container justify='center' style={{ margin: '10px 0px' }}>
                            <Button variant='contained' color='primary' type="submit">Add Simulator</Button>
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
  addConfig
}

export default connect(mapStateToProps, mapDispatchToProps)(AddConfig);

