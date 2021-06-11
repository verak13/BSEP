import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Grid} from '@material-ui/core';
import { getMessageAlarms } from '../../store/actions/messageAlarmActions';

import TablePagination from '@material-ui/core/TablePagination';


import NavBar from '../../components/NavBar';

import MaterialTable from 'material-table';


function MessageAlarms(props) {

    const [pageSize, setPageSize] = React.useState(10);

    const handleChangePage = (event, newPage) => {
        console.log('ee', newPage)
        props.getMessageAlarms({ pageSize, page: newPage });
    };
  
    const handleChangePageSize = (event) => {
      setPageSize(parseInt(event.target.value, 10));
    };

    useEffect(() => {
        props.getMessageAlarms({ pageSize, page: 0 });
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
        title="Message alarms list"
        columns={[
            { title: 'Patient Id', field: 'patientId' },
            { title: 'Variable', field: 'variable' },
            { title: 'Value', field: 'value' },
            { title: 'Value', field: 'otherValue' },
            { title: 'Timestamp', field: 'date' }
        ]}
        data={props.messageAlarms}        
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
    messageAlarms: state.messageAlarms.all,
    total : state.messageAlarms.total || 0,
    page: state.messageAlarms.page
});

const mapDispatchToProps = {
    getMessageAlarms
}


 
export default connect(mapStateToProps, mapDispatchToProps)(MessageAlarms);