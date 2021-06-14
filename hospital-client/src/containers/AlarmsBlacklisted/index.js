import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Grid} from '@material-ui/core';
import { getAlarmsBlacklisted, getAlarmsBruteforce, getAlarmsError, getAlarmsInactive } from '../../store/actions/alarmActions';

import TablePagination from '@material-ui/core/TablePagination';


import NavBar from '../../components/NavBar';

import MaterialTable from 'material-table';


function AlarmsBlacklisted(props) {

    const [pageSize, setPageSize] = React.useState(10);

    const handleChangePage = (event, newPage) => {
        console.log('ee', newPage)
        props.getAlarmsBlacklisted({ pageSize, page: newPage });
    };
  
    const handleChangePageSize = (event) => {
      setPageSize(parseInt(event.target.value, 10));
    };

    useEffect(() => {
        props.getAlarmsBlacklisted({ pageSize, page: 0 });
    }, []);

  
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
            { title: 'IP Address', field: 'ip' },
            { title: 'User Email', field: 'userEmail' },
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
    alarms: state.alarmBlackListed.all,
    total : state.alarmBlackListed.total || 0,
    page: state.alarmBlackListed.page
});

const mapDispatchToProps = {
    getAlarmsBlacklisted
}


 
export default connect(mapStateToProps, mapDispatchToProps)(AlarmsBlacklisted);