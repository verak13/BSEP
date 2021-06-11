import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Grid } from '@material-ui/core';
import { getReports } from '../../store/actions/reportActions';
import ArrowBackIcon from '@material-ui/icons/ArrowBack';
import { Link } from 'react-router-dom';
import Button from '@material-ui/core/Button';
import MaterialTable from 'material-table';

import { REPORT } from '../../assets/routes';
import NavBar from '../../components/NavBar';
import { formatTimestampWithTime } from '../../utils/index';

function ReportList(props) {

    const [data, setData] =  React.useState(props.reports);
    useEffect(() => {
        props.getReports();
    }, []);

    useEffect(() => {
        const newData = props.reports.map(r => {
            r.date = formatTimestampWithTime(r.date)
            console.log(r)
            return r;
        })
        setData(newData)
    }, props.reports);

    return (
    <>
    <NavBar />
    <Grid
        container
        direction="column"
        md={10}
        spacing={2}
        style={{ margin: '0 auto', marginTop: 100, minHeight: '100vh' }}
    >
        <Grid item xs={3} align="left">
            <Button
                variant="contained"
                color="default"
                startIcon={<ArrowBackIcon />}
            >
                <Link style={{ textDecoration: 'none', color: 'black' }} to={REPORT} className="btn btn-primary">Back</Link>
            </Button>
        </Grid>
        <Grid item xs={12}>
            <MaterialTable
            title="Report list"
            columns={[
                { title: 'Date', field: 'date' },
                { title: 'Total logs', field: 'totalLogCount' },
                { title: 'Error logs', field: 'errorLogCount' },
                { title: 'Login logs', field: 'loginLogCount'},
                { title: 'Login error logs', field: 'loginErrorLogCount' },
                { title: 'Application logs', field: 'applicationLogCount'},
                { title: 'Total alarms', field: 'alarmsTriggered'},
                { title: 'Brute force alarms', field: 'bruteForceAlarmsTriggered'},
                { title: 'Error log alarms', field: 'errorLogAlarmsTriggered'},
                { title: 'Inactive alarms', field: 'inactiveUserAlarmsTriggered'},
                { title: 'IP alarms', field: 'ipAlarmsTriggered'}
            ]}
            data={data}        
            options={{
                filtering: true
            }}
            />
        </Grid>
    </Grid>
    </>)
}

const mapStateToProps = state => ({
    reports: state.report.all,
});

const mapDispatchToProps = {
    getReports
}

export default connect(mapStateToProps, mapDispatchToProps)(ReportList);