import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Grid} from '@material-ui/core';
import { getAlarmsBlacklisted, getAlarmsBruteforce, getAlarmsError, getAlarmsInactive } from '../../store/actions/alarmActions';

import TablePagination from '@material-ui/core/TablePagination';


import NavBar from '../../components/NavBar';

import MaterialTable from 'material-table';


function AlarmsBruteforce(props) {

    const [pageSize, setPageSize] = React.useState(10);

    const handleChangePage = (event, newPage) => {
        console.log('ee', newPage)
        props.getAlarmsBruteforce({ pageSize, page: newPage });
    };
  
    const handleChangePageSize = (event) => {
      setPageSize(parseInt(event.target.value, 10));
    };

    useEffect(() => {
        props.getAlarmsBruteforce({ pageSize, page: 0 });
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
        title="Alarm list"
        columns={[
            { title: 'Username', field: 'username' },
            { title: 'Attempts', field: 'attempts' },
            { title: 'Date', field: 'date' }
        ]}
        data={props.alarms}        
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
    alarms: state.alarmBruteforce.all,
    total : state.alarmBruteforce.total || 0,
    page: state.alarmBruteforce.page
});

const mapDispatchToProps = {
    getAlarmsBruteforce
}


 
export default connect(mapStateToProps, mapDispatchToProps)(AlarmsBruteforce);