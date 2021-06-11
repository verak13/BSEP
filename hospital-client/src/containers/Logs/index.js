import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Grid} from '@material-ui/core';
import { getLogs } from '../../store/actions/logActions';

import TablePagination from '@material-ui/core/TablePagination';


import NavBar from '../../components/NavBar';

import MaterialTable from 'material-table';


function Logs(props) {

    const [pageSize, setPageSize] = React.useState(10);

    const handleChangePage = (event, newPage) => {
        console.log('ee', newPage)
        props.getLogs({ pageSize, page: newPage });
    };
  
    const handleChangePageSize = (event) => {
      setPageSize(parseInt(event.target.value, 10));
    };

    useEffect(() => {
        props.getLogs({ pageSize, page: 0 });
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
        title="Log list"
        columns={[
            { title: 'Date', field: 'timestamp' },
            { title: 'Type', field: 'type' },
            { title: 'Source', field: 'source' },
            { title: 'Severityr', field: 'severity' },
            { title: 'IP', field: 'ip' },
            { title: 'Usern', field: 'username' },
            { title: 'Message', field: 'message' }
        ]}
        data={props.logs}        
        options={{
            filtering: true
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
    logs: state.logs.all,
    total : state.logs.total || 0,
    page: state.logs.page
});

const mapDispatchToProps = {
    getLogs
}


 
export default connect(mapStateToProps, mapDispatchToProps)(Logs);