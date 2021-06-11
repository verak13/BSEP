import React, { useEffect, useState } from 'react';
import { connect } from 'react-redux';
import { Grid } from '@material-ui/core';
import Select from '@material-ui/core/Select';
import MenuItem from '@material-ui/core/MenuItem';
import InputLabel from '@material-ui/core/InputLabel';
import FormControl from '@material-ui/core/FormControl';
import Accordion from '@material-ui/core/Accordion';
import AccordionSummary from '@material-ui/core/AccordionSummary';
import AccordionDetails from '@material-ui/core/AccordionDetails';
import Typography from '@material-ui/core/Typography';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import Button from '@material-ui/core/Button';
import CreateIcon from '@material-ui/icons/Create';
import DnsIcon from '@material-ui/icons/Dns';
import { Link } from 'react-router-dom';

import NavBar from '../../components/NavBar';
import { generateReport, getReports } from '../../store/actions/reportActions';
import AlarmChart from './AlarmChart';
import LogChart from './LogChart';

import { REPORT_LIST } from '../../assets/routes';
import { months } from '../../utils/index';

function Report(props) {
    useEffect(() => {
        props.getReports();
    }, []);

    const [selected, setSelected] = useState({ start: 4, end: 6, year: 2021});

    return (
    <>
        <NavBar />
        <Grid
            container
            alignItems="center"
            spacing={2}
            style={{ margin: '0 auto', marginTop: 100, minHeight: '100vh' , width: '70%'}}
        >   
            <Grid item xs={6}>
                <Accordion>
                    <AccordionSummary
                    expandIcon={<ExpandMoreIcon />}
                    aria-controls="panel1a-content"
                    id="panel1a-header"
                    >
                        <Typography style={{fontWeight:600}}>REPORT FILTER</Typography>
                    </AccordionSummary>
                    <AccordionDetails>
                    <Grid item xs={3} align="left">
                <FormControl style={{minWidth: 100}}>
                    <InputLabel id="demo-simple-select-outlined-label">Start month</InputLabel>
                    <Select
                        labelId="startMonth"
                        id="startMonth"
                        value={selected.start}
                        onChange={(e) => { setSelected((prevState) => ({
                            ...prevState,
                            start: e.target.value,
                        }))}}
                    >
                        {Object.entries(months).map(keyValue => <MenuItem value={keyValue[0]}>{keyValue[1]}</MenuItem>)}
                </Select>
                </FormControl>
                </Grid>
                <Grid item xs={3} align="center">
                <FormControl style={{minWidth: 100}}>
                    <InputLabel id="demo-simple-select-outlined-label">End month</InputLabel>
                    <Select
                        labelId="endMonth"
                        id="endMonth"
                        value={selected.end}
                        onChange={(e) => { setSelected((prevState) => ({
                            ...prevState,
                            end: e.target.value,
                        }))}}
                    >
                        {Object.entries(months).map(keyValue => <MenuItem value={keyValue[0]}>{keyValue[1]}</MenuItem>)}
                </Select>
                </FormControl>
            </Grid>
            <Grid item xs={3} align="right">
            <FormControl style={{minWidth: 100}}>
                <InputLabel id="demo-simple-select-outlined-label">Year</InputLabel>
                <Select
                    labelId="endMonth"
                    id="endMonth"
                    value={selected.year}
                    onChange={(e) => { setSelected((prevState) => ({
                        ...prevState,
                        year: e.target.value,
                      }))}}
                >
                    <MenuItem value={2019}>2019</MenuItem>
                    <MenuItem value={2020}>2020</MenuItem>
                    <MenuItem value={2021}>2021</MenuItem>
              </Select>
              </FormControl>
            </Grid>
                </AccordionDetails>
                </Accordion>
            </Grid>
            <Grid item xs={3} align="right">
                <Button
                    variant="contained"
                    color="primary"
                    startIcon={<CreateIcon />}
                    onClick={props.generateReport}
                >
                    New report
                 </Button>
            </Grid>      
            <Grid item xs={3} align="left">
                <Button
                    variant="contained"
                    color="default"
                    startIcon={<DnsIcon />}
                >
                   <Link style={{ textDecoration: 'none', color: 'black' }} to={REPORT_LIST} className="btn btn-primary">Report List</Link>
                 </Button>
            </Grid>            
            <Grid item xs={12}>
                <AlarmChart data={props.reports} start={selected.start} end={selected.end} year={selected.year}/>
            </Grid>
            <Grid item xs={12}>
                <LogChart data={props.reports} start={selected.start} end={selected.end} year={selected.year}/>
            </Grid>
        </Grid>
    </>)
}

const mapStateToProps = state => ({
    report : state.report.report,
    reports: state.report.all
});

const mapDispatchToProps = {
    generateReport, getReports
}


 
export default connect(mapStateToProps, mapDispatchToProps)(Report);