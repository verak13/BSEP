import React, { useState } from 'react'

import { connect } from 'react-redux';
import Typography from '@material-ui/core/Typography';
import NavBar from '../../components/NavBar';
import { Paper, Grid, Button, TextField, Divider, Container } from '@material-ui/core';
import { Formik, Form, Field } from 'formik';
import * as Yup from 'yup';
import { withFormikField } from '../../utils';
import Footer from '../../components/Footer';

function Certificates(props) {

  
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
            <h1>SERTIFIKATI</h1>


        </Grid>
        <Footer />
    </div >
    )

}

const mapStateToProps = state => ({
});

export default connect(mapStateToProps)(Certificates);