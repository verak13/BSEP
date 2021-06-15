import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Grid} from '@material-ui/core';
import { getAlarmsCustom } from '../../store/actions/alarmActions';

import TablePagination from '@material-ui/core/TablePagination';


import NavBar from '../../components/NavBar';

import MaterialTable from 'material-table';


function AlarmsCustom(props) {

    const [pageSize, setPageSize] = React.useState(10);

    const handleChangePage = (event, newPage) => {
        console.log('ee', newPage)
        props.getAlarmsCustom({ pageSize, page: newPage });
    };
  
    const handleChangePageSize = (event) => {
      setPageSize(parseInt(event.target.value, 10));
    };

    useEffect(() => {
        props.getAlarmsCustom({ pageSize, page: 0 });
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
        title="Custom alarms list"
        style={{minWidth: '70%', minHeight:'100%', overflow:true}}     
        columns={[
            { title: 'Message', field: 'errorMsg' },
            { title: 'Rule', field: 'ruleName' },
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
            rowsPerPageOptions={[5]}
          />
            }
         }
        />
    </Grid>
    </>)
}

const mapStateToProps = state => ({
    alarms: state.alarmCustom.all,
    total : state.alarmCustom.total || 0,
    page: state.alarmCustom.page
});

const mapDispatchToProps = {
    getAlarmsCustom
}


 
export default connect(mapStateToProps, mapDispatchToProps)(AlarmsCustom);