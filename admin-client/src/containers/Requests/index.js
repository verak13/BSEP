import React, { useState, useEffect } from 'react'

import { connect } from 'react-redux';
import Typography from '@material-ui/core/Typography';
import NavBar from '../../components/NavBar';
import { Paper, Grid, Button, TextField, Divider, Container } from '@material-ui/core';
import { Formik, Form, Field } from 'formik';
import * as Yup from 'yup';
import { withFormikField } from '../../utils';
import Footer from '../../components/Footer';
import { getRequests } from '../../store/actions/requestActions';
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
import { addCertificate } from '../../store/actions/certificateActions';
import { removeRequest } from '../../store/actions/requestActions';



const useStyles = makeStyles({
    table: {
      minWidth: 650,
    },
  });


function Requests(props) {

    const classes = useStyles();
    const initialState = {
        requestId: 0,
        cRLSign: false,
        dataEncipherment: false,
        decipherOnly: false,
        digitalSignature: false,
        encipherOnly: false,
        keyAgreement: false,
        keyCertSign: false,
        keyEncipherment: false,
        nonRepudiation: false,
      };
    
    const [open, setOpen] = React.useState(false);
    const [state, setState] = React.useState(initialState);
    
      const handleChange = (event) => {
        console.log(state);
        setState({ ...state, [event.target.name]: event.target.checked });
      };    
    
    
    const handleClose = () => {
        setOpen(false);
      };

    const handleAdd = () => {
        props.addCertificateAction(state);
        setOpen(false);
    }

    const handleDelete = (requestId) => {
        props.deleteCertificateRequestAction(requestId);
    }

    useEffect(() => {
        props.getRequestsAction();
    }, []);
  
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
            <h1>Certificate Requests</h1>
        <Grid md={8}>
            <TableContainer component={Paper}>
                <Table className={classes.table} aria-label="simple table">
                    <TableHead>
                    <TableRow>
                        <TableCell>Request ID</TableCell>
                        <TableCell align="right">Common Name</TableCell>
                        <TableCell align="right">Country</TableCell>
                        <TableCell align="right">Organization</TableCell>
                        <TableCell align="right">Org Unit</TableCell>
                        <TableCell align="right">State</TableCell>
                        <TableCell align="right">Locality</TableCell>
                        <TableCell align="right">Email</TableCell>

                        <TableCell align="right">Add Certificate</TableCell>

                    </TableRow>
                    </TableHead>
                    <TableBody>
                            {props.requests.map( row => (
                                <TableRow key={row.id}>
                                <TableCell component="th" scope="row">
                                    {row.id}
                                </TableCell>
                        <TableCell align="right">{row.commonName}</TableCell>
                        <TableCell align="right">{row.countryName}</TableCell>
                        <TableCell align="right">{row.organization}</TableCell>
                        <TableCell align="right">{row.organizationUnitName}</TableCell>
                        <TableCell align="right">{row.stateName}</TableCell>
                        <TableCell align="right">{row.localityName}</TableCell>
                        <TableCell align="right">{row.email}</TableCell>
                        <TableCell align="right"><Button variant='outlined' color='primary' onClick={() => {setOpen(true); setState({...initialState, requestId:row.id})}}>ADD</Button></TableCell>
                        <TableCell align="right"><Button variant='outlined' color='primary' onClick={() => {handleDelete(row.id);}}>DELETE</Button></TableCell>

                            </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </Grid>
        </Grid>



        <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
        <DialogTitle id="form-dialog-title">Add Certificate</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Tick the options below to create the certificate.
            If you don't know what does what mean, read the manual.
          </DialogContentText>
          <FormControlLabel
            value="start"
            control={<Checkbox color="primary" checked={state.cRLSign} onChange={handleChange} name="cRLSign"/>}
            label="cRLSign"
            labelPlacement="start"
          />

        <FormControlLabel
            value="start"
            control={<Checkbox color="primary" checked={state.dataEncipherment} onChange={handleChange} name="dataEncipherment" />}
            label="dataEncipherment"
            labelPlacement="start"
          />

        <FormControlLabel
            value="start"
            control={<Checkbox color="primary" checked={state.decipherOnly} onChange={handleChange} name="decipherOnly"/>}
            label="decipherOnly"
            labelPlacement="start"
          />
          <FormControlLabel
            value="start"
            control={<Checkbox color="primary" checked={state.digitalSignature} onChange={handleChange} name="digitalSignature"/>}
            label="digitalSignature"
            labelPlacement="start"
          />


          <FormControlLabel
            value="start"
            control={<Checkbox color="primary" checked={state.encipherOnly} onChange={handleChange} name="encipherOnly"/>}
            label="encipherOnly"
            labelPlacement="start"
          />

          <FormControlLabel
            value="start"
            control={<Checkbox color="primary" checked={state.keyAgreement} onChange={handleChange} name="keyAgreement"/>}
            label="keyAgreement"
            labelPlacement="start"
          />

          <FormControlLabel
            value="start"
            control={<Checkbox color="primary" checked={state.keyCertSign} onChange={handleChange} name="keyCertSign" />}
            label="keyCertSign"
            labelPlacement="start"
          />

          <FormControlLabel
            value="start"
            control={<Checkbox color="primary" checked={state.keyEncipherment} onChange={handleChange} name="keyEncipherment" />}
            label="keyEncipherment"
            labelPlacement="start"
          />
    
          <FormControlLabel
            value="start"
            control={<Checkbox color="primary" checked={state.nonRepudiation} onChange={handleChange} name="nonRepudiation"/>}
            label="nonRepudiation"
            labelPlacement="start"
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose} color="primary">
            Cancel
          </Button>
          <Button onClick={handleAdd} color="primary">
            Add
          </Button>
        </DialogActions>
      </Dialog>


        <Footer />
    </div >
    )

}

const mapStateToProps = state => ({
    requests: state.requests.requests
});

const mapDispatchToProps = {
    getRequestsAction: getRequests,
    addCertificateAction: addCertificate,
    deleteCertificateRequestAction: removeRequest,
}

export default connect(mapStateToProps, mapDispatchToProps)(Requests);