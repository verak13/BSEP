import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { getMessages } from '../../store/actions/messageActions';
import { Paper, Grid, Button, TextField, Divider, Container } from '@material-ui/core';
import { Formik, Form, Field } from 'formik';
import * as Yup from 'yup';
import { withFormikField } from '../../utils';

import TablePagination from '@material-ui/core/TablePagination';


import NavBar from '../../components/NavBar';

import MaterialTable from 'material-table';


function Messages(props) {

    const FormikTextField = withFormikField(TextField);

    const [pageSize, setPageSize] = React.useState(5);

    const handleChangePage = (event, newPage) => {
        console.log('ee', newPage)
        props.getMessages({ pageSize, page: newPage, patient: 0 });
    };
  
    const handleChangePageSize = (event) => {
      setPageSize(parseInt(event.target.value, 10));
    };

    useEffect(() => {
        props.getMessages({ pageSize, page: 0, patient: 0  });
    }, []);

    useEffect(() => {
        console.log(props.total, 'tttooo')
    })

    const handleSubmit = values => {
        if (values.patientId=='') {
            values.patientId = 0;
        }
        console.log(values);
        props.getMessages({ pageSize, page: 0, patient: values.patientId });
     }
  
    return (
    <>
    <NavBar />
    <Grid
        container
        direction="column"
        md={10}
        alignItems="center"
        style={{ margin: '0 auto', marginTop: 100, minHeight: '100vh' }}
    >

        <Formik
            initialValues={{ patientId: 0 }}
            validationSchema={Yup.object().shape({
                patientId: Yup.string()
                .matches(
                    /^$|[0-9]+$/,
                    "Must Be Integer"
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
                            name="patientId"
                            variant="outlined"
                            fullWidth
                            label="Patient Id"
                        />
                    </Grid>
                    
                    <Grid container justify='center' style={{ margin: '10px 0px' }}>
                        <Button variant='contained' color='primary' type="submit">Search By Patient Id</Button>
                    </Grid>
                </Grid>
            </Form>
        </Formik>

        <MaterialTable
        title="Message list"
        columns={[
            { title: 'Patient Id', field: 'patientId' },
            { title: 'Patient', field: 'patient' },
            { title: 'Timestamp', field: 'timestamp' },
            { title: 'Body Temperature', field: 'bodyTemperature' },
            { title: 'Pulse Rate', field: 'pulseRaye' },
            { title: 'Respiration Rate', field: 'respirationRate' },
            { title: 'Diastolic Blood Pressure', field: 'bloodPressure' },
            { title: 'Systolic blood Pressure', field: 'heartRate' }
        ]}
        data={props.messages}        
        options={{
            filtering: false
        }}
         components={{
            Pagination: () =>  
            <TablePagination
            component="div"
            count={props.total}
            page={props.page}
            onChangePage={handleChangePage}
            rowsPerPage={pageSize}
            onChangeRowsPerPage={handleChangePageSize}
          />
            }
         }
        />
    </Grid>
    </>)
}

const mapStateToProps = state => ({
    messages: state.messages.all,
    total : state.messages.total || 0,
    page: state.messages.page
});

const mapDispatchToProps = {
    getMessages
}


 
export default connect(mapStateToProps, mapDispatchToProps)(Messages);