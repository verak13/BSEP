import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Grid} from '@material-ui/core';
import { getAlarmsBlacklisted, getAlarmsBruteforce, getAlarmsError, getAlarmsInactive } from '../../store/actions/alarmActions';

import TablePagination from '@material-ui/core/TablePagination';


import NavBar from '../../components/NavBar';

import MaterialTable from 'material-table';


function AlarmsInactive(props) {

    const [pageSize, setPageSize] = React.useState(10);

    const handleChangePage = (event, newPage) => {
        console.log('ee', newPage)
        props.getAlarmsInactive({ pageSize, page: newPage });
    };
  
    const handleChangePageSize = (event) => {
      setPageSize(parseInt(event.target.value, 10));
    };

    useEffect(() => {
        props.getAlarmsInactive({ pageSize, page: 0 });
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
            { title: 'User Email', field: 'userEmail' },
            { title: 'Days Inactive', field: 'daysInactive' },
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
    alarms: state.alarmInactive.all,
    total : state.alarmInactive.total || 0,
    page: state.alarmInactive.page
});

const mapDispatchToProps = {
    getAlarmsInactive
}


 
export default connect(mapStateToProps, mapDispatchToProps)(AlarmsInactive);