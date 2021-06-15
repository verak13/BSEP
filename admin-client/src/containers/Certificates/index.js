import React, { useState, useEffect } from 'react'

import { connect } from 'react-redux';
import Typography from '@material-ui/core/Typography';
import NavBar from '../../components/NavBar';
import { Paper, Grid, Button, TextField, Divider, Container } from '@material-ui/core';
import { Formik, Form, Field } from 'formik';
import * as Yup from 'yup';
import { withFormikField } from '../../utils';
import Footer from '../../components/Footer';

import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import Checkbox from '@material-ui/core/Checkbox';
import FormGroup from '@material-ui/core/FormGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import FormControl from '@material-ui/core/FormControl';
import FormLabel from '@material-ui/core/FormLabel';
import {  getCertificates, revokeCertificate } from '../../store/actions/certificateActions';
import InputLabel from '@material-ui/core/InputLabel';
import MenuItem from '@material-ui/core/MenuItem';
import FormHelperText from '@material-ui/core/FormHelperText';
import Select from '@material-ui/core/Select';

const REVOKE_REASONS = ["UNSPECIFIED", "KEY_COMPROMISE", "CA_COMPROMISE", "AFFILIATION_CHANGED", "SUPERSEDED", "CESSATION_OF_OPERATION", 
                        "CERTIFICATE_HOLD", "REMOVE_FROM_CRL", "PRIVILEGE_WITHDRAWN", "AA_COMPROMISE"];


const useStyles = makeStyles(theme => ({
    table: {
      minWidth: 850
    },
    formControl: {
        margin: theme.spacing(1),
        minWidth: 120,
      },
      selectEmpty: {
        marginTop: theme.spacing(2),
      },
  }));


function Certificates(props) {

    const classes = useStyles();

    useEffect(() => {
        props.getCertificatesAction();
    }, []);

    const [open, setOpen] = React.useState(false);

    const handleClickOpen = () => {
      setOpen(true);
    };
  
    const handleClose = () => {
      setOpen(false);
    };

    const handleRevoke = () => {
        if(revokeCert.reason == ""){
            alert("Pick a reason");
            return;
        }
        
        props.revokeCertificateAction(revokeCert);
        setOpen(false);
        props.getCertificatesAction();
    }
    
    const [revokeCert, setRevokeCert] = React.useState({alias:'', reason:'', issuer:''});
    
    

    const handleChange = (event) => {
        setRevokeCert({...revokeCert, reason: event.target.value});
      };

    return (
        <div>
        <NavBar />
        <Grid
            container
            component={Paper}
            direction="column"
            xs={10}
            md={10}
            alignItems="center"
            style={{ margin: '0 auto', marginTop: 100, minHeight: '100vh' }}
        >
            <h1>CERTIFICATES</h1>



            <Grid md={8}>
            <TableContainer component={Paper}>
                <Table className={classes.table} aria-label="simple table">
                    <TableHead>
                    <TableRow>
                        <TableCell align="right">Common Name</TableCell>
                        <TableCell align="right">Issuer</TableCell>
                        <TableCell align="right">CA</TableCell>
                        <TableCell align="right">Revoked</TableCell>
                        
                        <TableCell align="right">Revoke Certificate</TableCell>

                    </TableRow>
                    </TableHead>
                    <TableBody>
                            {props.certificates.map( row => (
                                <TableRow key={row.email}>
                                <TableCell component="th" scope="row">
                                    {row.commonName}
                                </TableCell>
                        <TableCell align="right">{row.issuer}</TableCell>
                        <TableCell align="right">{row.ca + ''}</TableCell>
                        <TableCell align="right">{row.revoked + ''}</TableCell>
                        
                        <TableCell align="right"><Button variant='outlined' color='secondary' onClick={() => {setOpen(true); setRevokeCert({...revokeCert, alias:row.commonName, issuer:row.issuer})}}>REVOKE</Button></TableCell>
                        
                            </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </Grid>
        </Grid>


        <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
        <DialogTitle id="form-dialog-title">Revoke Certificate</DialogTitle>
        <DialogContent>
          <DialogContentText>
            To revoke a certificate, please select a valid reason below.
          </DialogContentText>
          <FormControl className={classes.formControl}>
        <InputLabel id="demo-simple-select-label">Reason</InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          value={revokeCert.reason}
          onChange={handleChange}
        >
            {REVOKE_REASONS.map(rs => (
            <MenuItem value={rs}>{rs}</MenuItem>
            ))}
          
        </Select>
      </FormControl>

        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose} color="primary">
            Cancel
          </Button>
          <Button onClick={handleRevoke} color="secondary">
            Revoke
          </Button>
        </DialogActions>
      </Dialog>

        <Footer />
    </div >
    )

}


const mapStateToProps = state => ({
    certificates: state.certificates.certificates
});

const mapDispatchToProps = {
    revokeCertificateAction: revokeCertificate,
    getCertificatesAction: getCertificates,
}




export default connect(mapStateToProps, mapDispatchToProps)(Certificates);