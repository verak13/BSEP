import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import NavBar from '../../components/NavBar';
import Autocomplete from '@material-ui/lab/Autocomplete';
import TextField from '@material-ui/core/TextField';
import { Grid } from '@material-ui/core';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import Accordion from '@material-ui/core/Accordion';
import AccordionSummary from '@material-ui/core/AccordionSummary';
import AccordionDetails from '@material-ui/core/AccordionDetails';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import CircularProgress from '@material-ui/core/CircularProgress';
import Button from '@material-ui/core/Button';
import SaveIcon from '@material-ui/icons/Save';

import { addLogRule } from '../../store/actions/rulesActions';

function LogRules(props) {
    
    const types = ["ERROR", "LOGIN_ERROR", "LOGIN", "APPLICATION"]; 
    const severity = ["ERROR", "INFO", "TRACE", "WARN"]; 

    const [msg, setMsg] = React.useState("");
    const [ruleName, setRuleName] = React.useState("");
    const [log, setLog] = React.useState({ type: null, severity: null})
    const [succLog, setSuccLog] = React.useState({ value: 0, error: false, type: null});
    const [precLog, setPrecLog] = React.useState({ value: 0, error: false, type: null});
    const [logFrequency, setLogFrequency] = React.useState({ times: 1, sec: 60, timesError: false, secError: false});
    const [loader, setLoader] = React.useState(false);

    const validateSucSec = sec => {
        if (sec < 0) {
            setSuccLog(prevState => ({...prevState, value:0, error: true}));
        } else {
            setSuccLog(prevState => ({...prevState, value:sec, error: false}));
        }
    }
    const validatePrecSec = sec => {
        if (sec < 0) {
            setPrecLog(prevState => ({...prevState, value:0, error: true}));
        } else {
            setPrecLog(prevState => ({...prevState, value:sec, error: false}));
        }
    }
    const validateLogFrequencyTimes = times => {
        if (times < 0) {
            setLogFrequency(prevState => ({ ...prevState, timesError: true}));
        } else {
            setLogFrequency(prevState => ({ ...prevState, timesError: false, times: times}));
        }
    }
    const validateLogFrequencySec = sec => {
        if (sec < 0) {
            setLogFrequency(prevState => ({ ...prevState, secError: true}));
        } else {
            setLogFrequency( prevState => ({ ...prevState, secError: false, sec: sec}));
        }
    }

    const saveRule = () => {
        const values = {
            name : ruleName,
            type: {
                list: log.type
            },
            severity: {
                list: log.severity
            },
            times: logFrequency.times,
            seconds: logFrequency.sec,
            precTypes: {
                list: precLog.type? precLog.type.length > 0 ? precLog.type: null:null
            },
            precSec: precLog.value,
            succTypes: {
                list: succLog.type? succLog.type.length > 0? succLog.type: null:null
            },
            succSec: succLog.value,
            msg : msg
        }
        props.addLogRule(values);
        setLoader(true);
    }

    useEffect(() => setLoader(false), [props.success])

    return (
    <>
        <NavBar />
        <Grid
            container
            direction="row"
            justify="center"
            alignItems="center"
            spacing={2}
            component={Paper}
            style={{ margin: '100px auto', minHeight: '20vh' , width: '80%', backgroundColor: '#e6eeff'}}
        >
            <Grid item xs={12} align="center">
                <Typography variant="h3" noWrap>Create Log rule</Typography>
            </Grid>
            {/* Ime pravila */}
            <Grid item xs={12} style={{margin: '40px'}}>
                <TextField
                    required
                    id="standard-full-width"
                    label="Rule name"
                    style={{ margin: 8 }}
                    placeholder="Insert new rule name"
                    helperText="Rule name should explain rule purpose"
                    fullWidth
                    onChange={e => setRuleName(e.target.value)}
                    margin="normal"
                    InputLabelProps={{
                        shrink: true,
                    }}
                />
            </Grid>
            {/* Tip i severity */}
            <Grid item xs={12} style={{margin: '10px 40px'}}>
                <Autocomplete
                multiple
                id="tags-outlined"
                options={types}
                getOptionLabel={(option) => option}
                filterSelectedOptions
                onChange={(e, values) => setLog(prevState => ({...prevState, type: values}))}
                renderInput={(params) => (
                    <TextField
                        {...params}
                        variant="outlined"
                        label="Log TYPE"
                        placeholder="Select log type"
                    />
                )}
                />
            </Grid>
            <Grid item xs={12} style={{margin: '10px 40px'}}>
                <Autocomplete
                multiple
                id="tags-outlined"
                options={severity}
                getOptionLabel={(option) => option}
                onChange={(e, values) => setLog(prevState => ({...prevState, severity: values}))}
                filterSelectedOptions
                renderInput={(params) => (
                    <TextField
                        {...params}
                        variant="outlined"
                        label="Log SEVERITY"
                        placeholder="Select log severity"
                    />
                )}
                />
            </Grid>
            {/* koliko puta za koje vreme */}
            <Grid item xs={12} style={{margin: '10px 40px'}}>
                <Typography variant="h6" noWrap>Select log frequency</Typography>
            </Grid>
            <Grid item xs={6} align="center">
                <TextField
                    id="standard-number"
                    label="Times log appeard"
                    type="number"
                    variant="outlined"
                    helperText= "Must be positive number!"
                    error={logFrequency.timesError}
                    value={logFrequency.times}
                    onChange={e => validateLogFrequencyTimes(e.target.value)}
                    InputLabelProps={{
                        shrink: true,
                    }}
                />
            </Grid>
            <Grid item xs={6} align="center">
                <TextField
                    id="standard-number"
                    label="Over seconds"
                    type="number"
                    value={logFrequency.sec}
                    variant="outlined"
                    helperText= "Must be positive number!"
                    error={logFrequency.secError}
                    onChange={e => validateLogFrequencySec(e.target.value)}
                    InputLabelProps={{
                        shrink: true,
                    }}
                />
            </Grid>
            {/* Odavde su succ i pred */}
            <Grid item xs={12} style={{margin: '30px 40px 10px'}}>
                <Accordion style={{backgroundColor: '#ccddff'}}>
                    <AccordionSummary
                    expandIcon={<ExpandMoreIcon />}
                    aria-controls="panel1a-content"
                    id="panel1a-header"
                    >
                        <Typography variant="h6" noWrap>Select precedent log</Typography>
                    </AccordionSummary>
                    <AccordionDetails>
                        <Grid item xs={6} >
                            <Autocomplete
                                multiple
                                id="tags-outlined"
                                options={types}
                                getOptionLabel={(option) => option}
                                filterSelectedOptions
                                onChange={(e, values) => setPrecLog(prevState => ({...prevState, type: values}))}
                                renderInput={(params) => (
                                    <TextField
                                        {...params}
                                        variant="outlined"
                                        label="Log TYPE"
                                        placeholder="Select precedent log type"
                                    />
                            )}
                            />
                        </Grid>
                        <Grid item xs={6} align="center">
                            <TextField
                                id="standard-number"
                                label="Seconds it appears before"
                                type="number"
                                variant="outlined"
                                helperText= "Must be positive number!"
                                error={precLog.error}
                                onChange={e => validatePrecSec(e.target.value)}
                                InputLabelProps={{
                                    shrink: true,
                                }}
                            />
                        </Grid>
                    </AccordionDetails>
                </Accordion>
            </Grid>
            <Grid item xs={12} style={{margin: '0px 40px'}}>
                <Accordion style={{backgroundColor: '#ccddff'}}>
                    <AccordionSummary
                    expandIcon={<ExpandMoreIcon />}
                    aria-controls="panel1a-content"
                    id="panel1a-header"
                    >
                        <Typography variant="h6" noWrap>Select successor log</Typography>
                    </AccordionSummary>
                    <AccordionDetails>
                        <Grid item xs={6} >
                            <Autocomplete
                                multiple
                                id="tags-outlined"
                                options={types}
                                getOptionLabel={(option) => option}
                                filterSelectedOptions
                                onChange={(e, values) => setSuccLog(prevState => ({...prevState, type: values}))}
                                renderInput={(params) => (
                                    <TextField
                                        {...params}
                                        variant="outlined"
                                        label="Log TYPE"
                                        placeholder="Select successor log type"
                                    />
                            )}
                            />
                        </Grid>
                        <Grid item xs={6} align="center">
                            <TextField
                                id="standard-number"
                                label="Seconds it appears after"
                                type="number"
                                variant="outlined"
                                helperText= "Must be positive number!"
                                error={succLog.error}
                                onChange={e => validateSucSec(e.target.value)}
                                InputLabelProps={{
                                    shrink: true,
                                }}
                            />
                        </Grid>
                    </AccordionDetails>
                </Accordion>
            </Grid>
            <Grid item xs={12} style={{margin: '40px'}}>
                <TextField
                    required
                    id="standard-full-width"
                    label="Alarm msg"
                    style={{ margin: 8 }}
                    placeholder="Insert alarm message"
                    helperText="This message will be sent by email when alarm is triggered"
                    fullWidth
                    onChange={e => setMsg(e.target.value)}
                    margin="normal"
                    InputLabelProps={{
                        shrink: true,
                    }}
                />
            </Grid>
            <Grid item xs={3} style={{margin: '20px 40px'}} >
                <Button variant="contained" color="primary"  startIcon={<SaveIcon />} onClick={saveRule}>
                    Save
                </Button>
                {loader &&    <CircularProgress style={{marginTop:'5px'}}/>}
            </Grid>                    
        </Grid>
    </>)
}

const mapStateToProps = state => ({
    success : state.notification.type
});

const mapDispatchToProps = {
    addLogRule
}


 
export default connect(mapStateToProps, mapDispatchToProps)(LogRules);