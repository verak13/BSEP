import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Grid} from '@material-ui/core';
import { getLogs } from '../../store/actions/logActions';

import TablePagination from '@material-ui/core/TablePagination';


import NavBar from '../../components/NavBar';

import MaterialTable from 'material-table';
import Filter from './Filter';


function Logs(props) {

    const [pageSize, setPageSize] = React.useState(10);

    let search = {
        from : null,
        to: null,
        ip : "",
        source: "",
        type: "",
        severity: "",
        username: "",
        message: ""
    }

    const handleChangePage = (event, newPage) => {
        console.log('ee', newPage)
        props.getLogs({ pageSize, page: newPage , search});
    };
  
    const handleChangePageSize = (event) => {
      setPageSize(parseInt(event.target.value, 10));
    };

    useEffect(() => {
        props.getLogs({ pageSize, page: 0 , search});
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
        title="Log list"
        columns={[
            { title: 'Date', field: 'timestamp' , filterComponent: (props) => <Filter {...{type: 'timestamp', search: search, pageSize}} />,},
            { title: 'Type', field: 'type', filterComponent: (props) => <Filter {...{type: 'type', search: search, pageSize}} /> },
            { title: 'Source', field: 'source', filterComponent: (props) => <Filter {...{type: 'source', search: search, pageSize}} /> },
            { title: 'Severityr', field: 'severity' , filterComponent: (props) => <Filter {...{type: 'severity', search: search, pageSize}} />},
            { title: 'IP', field: 'ip', filterComponent: (props) => <Filter {...{type: 'ip', search: search, pageSize}} /> },
            { title: 'Usern', field: 'username' , filterComponent: (props) => <Filter {...{type: 'username', search: search, pageSize}} />},
            { title: 'Message', field: 'message', width: '100px', filterComponent: (props) => <Filter {...{type: 'message', search: search, pageSize}} /> }
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