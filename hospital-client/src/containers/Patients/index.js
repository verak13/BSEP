import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Grid} from '@material-ui/core';
import { getPatients } from '../../store/actions/patientActions';

import TablePagination from '@material-ui/core/TablePagination';


import NavBar from '../../components/NavBar';

import MaterialTable from 'material-table';


function Patients(props) {

    const [pageSize, setPageSize] = React.useState(10);

    const handleChangePage = (event, newPage) => {
        console.log('ee', newPage)
        props.getPatients({ pageSize, page: newPage });
    };
  
    const handleChangePageSize = (event) => {
      setPageSize(parseInt(event.target.value, 10));
    };

    useEffect(() => {
        props.getPatients({ pageSize, page: 0 });
    }, []);

    useEffect(() => {
        console.log(props.total, 'tttooo')
    })
  
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
        <MaterialTable
        title="Patients list"
        columns={[
            { title: 'Id', field: 'id' },
            { title: 'JMBG', field: 'jmbg' },
            { title: 'Name', field: 'firstName' },
            { title: 'Last Name', field: 'lastName' },
            { title: 'Birth Date', field: 'birthDate' },
            { title: 'Height', field: 'height' },
            { title: 'Weight', field: 'weight' },
            { title: 'Gender', field: 'gender' },
            { title: 'Blood Type', field: 'bloodType' }
        ]}
        data={props.patients}        
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
            rowsPerPageOptions={[5]}
          />
            }
         }
        />
    </Grid>
    </>)
}

const mapStateToProps = state => ({
    patients: state.patients.all,
    total : state.patients.total || 0,
    page: state.patients.page
});

const mapDispatchToProps = {
    getPatients
}


 
export default connect(mapStateToProps, mapDispatchToProps)(Patients);