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

const useStyles = makeStyles({
    table: {
      minWidth: 650,
    },
  });

  
function createData(name, calories, fat, carbs, protein) {
  return { name, calories, fat, carbs, protein };
}

const rows = [
  createData('Frozen yoghurt', 159, 6.0, 24, 4.0),
  createData('Ice cream sandwich', 237, 9.0, 37, 4.3),
  createData('Eclair', 262, 16.0, 24, 6.0),
  createData('Cupcake', 305, 3.7, 67, 4.3),
  createData('Gingerbread', 356, 16.0, 49, 3.9),
];



function Requests(props) {

    const classes = useStyles();


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
                            {rows.map((row) => (
                                <TableRow key={row.name}>
                                <TableCell component="th" scope="row">
                                    {row.name}
                                </TableCell>
                        <TableCell align="right">{row.calories}</TableCell>
                        <TableCell align="right">{row.calories}</TableCell>
                        <TableCell align="right">{row.calories}</TableCell>
                        <TableCell align="right">{row.calories}</TableCell>
                        <TableCell align="right">{row.calories}</TableCell>
                        <TableCell align="right">{row.calories}</TableCell>
                        <TableCell align="right">{row.calories}</TableCell>
                        <TableCell align="right">{row.calories}</TableCell>

                            </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </Grid>
        </Grid>
        <Footer />
    </div >
    )

}

const mapStateToProps = state => ({
});

const mapDispatchToProps = {
    getRequestsAction: getRequests,
}

export default connect(mapStateToProps, mapDispatchToProps)(Requests);