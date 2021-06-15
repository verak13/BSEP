import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Grid} from '@material-ui/core';
import { getAlarmsBlacklisted, getAlarmsBruteforce, getAlarmsError, getAlarmsInactive } from '../../store/actions/alarmActions';

import TablePagination from '@material-ui/core/TablePagination';


import NavBar from '../../components/NavBar';

import MaterialTable from 'material-table';


function AlarmsBlacklisted(props) {

    const [pageSize, setPageSize] = React.useState(5);

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
        style={{ margin: '0 auto', marginTop: 100, minHeight: '30vh' }}
    >
        <MaterialTable
        title="Blacklisted IP alarms list"
        columns={[
            { title: 'IP Address', field: 'ip' },
            { title: 'User Email', field: 'userEmail' },
            { title: 'Date', field: 'date' }
        ]}
        data={props.alarms}  
        style={{minWidth: '70%', minHeight:'100%', overflow:true}}      
        options={{
            filtering: false,
            
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
    alarms: state.alarmBlackListed.all,
    total : state.alarmBlackListed.total || 0,
    page: state.alarmBlackListed.page
});

const mapDispatchToProps = {
    getAlarmsBlacklisted
}


 
export default connect(mapStateToProps, mapDispatchToProps)(AlarmsBlacklisted);