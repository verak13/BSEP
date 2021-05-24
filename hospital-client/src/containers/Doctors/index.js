import React, { useState, useEffect } from 'react'

import { connect } from 'react-redux';
import Typography from '@material-ui/core/Typography';
import NavBar from '../../components/NavBar';
import { Paper, Grid, Button, TextField, Divider, Container } from '@material-ui/core';
import { Formik, Form, Field } from 'formik';
import * as Yup from 'yup';
import { withFormikField } from '../../utils';
import Footer from '../../components/Footer';
import { addDoctor } from '../../store/actions/doctorActions';
import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles({
    table: {
      minWidth: 650,
    },
  });

  const FormikTextField = withFormikField(TextField);

function AddDoctor(props) {

    const classes = useStyles();
    const initialState = {
        id: 0,
        email: null,
        firstName: null,
        lastName: null,
        password: null,
      };

    const handleSubmit = values => {
       console.log({...values, id:999});
       props.addDoctor(values);
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
            xs={10}
            md={10}
            alignItems="center"
            style={{ margin: '0 auto', marginTop: 100, minHeight: '100vh' }}
        >
            <h1>Add Doctor</h1>
        <Grid md={8}>
        <Formik
                initialValues={{ email:'', firstName:'', lastName:'', password:''  }}
                validationSchema={Yup.object().shape({
                    email: Yup.string().email('Invalid email').required('Required'),
                    firstName: Yup.string().min(2, 'Too Short!').max(20, 'Too Long!').required('Required')
                    .matches(
                        /^$|[a-zA-Z ]+$/,
                        "Must Not Contain Special Characters"
                    ),
                    lastName: Yup.string().min(2, 'Too Short!').max(20, 'Too Long!').required('Required')
                    .matches(
                        /^$|[a-zA-Z ]+$/,
                        "Must Not Contain Special Characters"
                    ),
                    password: Yup.string().min(8, 'Too Short!').max(20, 'Too Long!').required('Required')
                    .matches(
                        /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/,
                        "Must Contain 8 Characters, One Uppercase, One Lowercase, One Number and one special case Character"
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
                                name="email"
                                variant="outlined"
                                required
                                fullWidth
                                label="Email"
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <Field
                                component={FormikTextField}
                                type="text"
                                name="firstName"
                                variant="outlined"
                                required
                                fullWidth
                                label="First Name"

                            />
                        </Grid>
                      
                        <Grid item xs={12}>

                            <Field
                                component={FormikTextField}
                                type="text"
                                name="lastName"
                                variant="outlined"
                                required
                                fullWidth
                                label="Last Name"
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <Field
                                component={FormikTextField}
                                type="text"
                                name="password"
                                variant="outlined"
                                required
                                fullWidth
                                label="Password"

                            />
                        </Grid>


                        
                        <Grid container justify='center' style={{ margin: '10px 0px' }}>
                            <Button variant='contained' color='primary' type="submit">Add</Button>
                               

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
  addDoctor
}

export default connect(mapStateToProps, mapDispatchToProps)(AddDoctor);