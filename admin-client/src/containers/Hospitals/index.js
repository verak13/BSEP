import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Grid} from '@material-ui/core';
import { getHospitals } from '../../store/actions/hospitalConfigurationActions';



import TablePagination from '@material-ui/core/TablePagination';


import NavBar from '../../components/NavBar';

import MaterialTable from 'material-table';


function Hospitals(props) {

    const [pageSize, setPageSize] = React.useState(10);

    const handleChangePage = (event, newPage) => {
        console.log('ee', newPage)
        props.getHospitals({ pageSize, page: newPage });
    };
  
    const handleChangePageSize = (event) => {
      setPageSize(parseInt(event.target.value, 10));
    };

    useEffect(() => {
        props.getHospitals({ pageSize, page: 0 });
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
        title="Hospitals"
        style={{minWidth: '80%'}}
        columns={[
            { title: 'ID', field: 'id' },
            { title: 'Name', field: 'name' }
        ]}
        data={props.hospitals}        
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
    hospitals: state.hospitals.all,
    total : state.hospitals.total || 0,
    page: state.hospitals.page
});

const mapDispatchToProps = {
    getHospitals
}


 
export default connect(mapStateToProps, mapDispatchToProps)(Hospitals);