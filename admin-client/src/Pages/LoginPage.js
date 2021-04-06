import React from 'react'
import Typography from '@material-ui/core/Typography';
import NavBar from '../components/NavBar';
import { Paper, Grid, Button, TextField, Divider, Container } from '@material-ui/core';
import { Formik, Form, Field } from 'formik';
import * as Yup from 'yup';
import { withFormikField } from '../utils';
import { connect } from 'react-redux';
import { loginAction } from '../store/actions/authActions';
import Footer from '../components/Footer';


const FormikTextField = withFormikField(TextField);

function LoginPage(props) {

    const handleSubmit = values => {
        props.login({ username: values.email, password: values.password });
    }

    return (
        <div>
            <NavBar />
            <Container style={{ height: 500 }} >
                <Grid
                    container
                    component={Paper}
                    direction="column"
                    justify="center"
                    md={6}
                    alignItems="center"
                    style={{ margin: '0 auto', marginTop: 100, minHeight: '60vh' }}
                >

                    <Grid item md={12} xs={10}>

                        <Typography variant='h5'>Enter your credentials below</Typography>

                    </Grid>
                    <Divider />
                    <Grid item md={12} style={{ marginTop: 10, width: '80%' }}>

                        <Formik
                            initialValues={{ email: '', password: '' }}
                            validationSchema={Yup.object().shape({
                                email: Yup.string()
                                    .email('Emaill address is not valid')
                                    .required('Email address is required')
                                    .max(255),
                                password: Yup.string().required('Password is required').min(3, 'Password must be at least 3 characters long')
                            })
                            }
                            onSubmit={handleSubmit}
                        >
                            <Form >
                                <Grid container spacing={2}>

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
                                            type="password"
                                            name="password"
                                            variant="outlined"
                                            required
                                            fullWidth
                                            label="Password"

                                        />
                                    </Grid>
                                    <Grid container justify='center' style={{ margin: '10px 0px' }}>
                                        <Button variant='contained' color='primary' type="submit">Login</Button>
                                    </Grid>
                                </Grid>
                            </Form>
                        </Formik>
                    </Grid>

                </Grid>
            </Container>
            <Footer />
        </div >
    )

}


const mapDispatchToProps = { login: loginAction };

export default connect(null, mapDispatchToProps)(LoginPage);