import React, { useState, useEffect } from 'react'
import { connect } from 'react-redux';
import NavBar from '../../components/NavBar';
import { Paper, Grid, Button, TextField, Divider, Container } from '@material-ui/core';
import DeleteIcon from '@material-ui/icons/Delete';
import Footer from '../../components/Footer';
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Select from '@material-ui/core/Select';
import MenuItem from '@material-ui/core/MenuItem';
import FormControl from '@material-ui/core/FormControl';
import Snackbar from '@material-ui/core/Snackbar';
import MuiAlert from '@material-ui/lab/Alert';

import {  getUsers } from '../../store/actions/userActions';
import RemoveUserDialog from './RemoveUserDialog';

const ROLES = ["ADMIN", "DOCTOR"]

function Alert(props) {
    return <MuiAlert elevation={6} variant="filled" {...props} />;
  }

const useStyles = makeStyles(theme => ({
    table: {
      minWidth: 650,
    },
    formControl: {
        margin: theme.spacing(1),
        minWidth: 120,
      },
      selectEmpty: {
        marginTop: theme.spacing(2),
      },
  }));


function Users(props) {

    const classes = useStyles();

    useEffect(() => {
        props.getUsers('DOCTOR');

    }, []);

    const [open, setOpen] = React.useState(false);
    const [userID, setUserID] = React.useState('');
    const [roles, setRoles] =  React.useState(props.users.reduce((acc, user) => { 
        acc[user.email] = user.role; 
        return acc
    }, {}));
    const [snackbar, setSnackbar] = React.useState({ open: false, message: '' });

  
    const handleCloseSnackbar = (event, reason) => {
      if (reason === 'clickaway') {
        return;
      }
      setSnackbar({ open: false, message: '' });
    }

    const handleChangeRole = (event, userEmail) => {
        setRoles(prevState => {
            let roles = Object.assign({}, prevState);
            roles[userEmail] = event.target.value;      
            return roles;                                
          })
        setSnackbar({ open: true, message: 'Successfully changed role!' })
    };

    const removeUser = () => {
        setOpen(false);
        console.log('removing user with id: ', userID);
        setSnackbar({ open: true, message: 'Successfully removed user!' })
    }

    return (
        <div>
        <NavBar />
        <Grid
            container
            component={Paper}
            direction="column"
            spacing={2}
            alignItems="center"
            style={{ margin: '0 auto', marginTop: 100, minHeight: '100vh' }}
        >
            <h1>USERS</h1>
            <Grid>
            <TableContainer component={Paper}>
                <Table className={classes.table} aria-label="simple table">
                    <TableHead>
                    <TableRow>
                        <TableCell align="right">Email</TableCell>
                        <TableCell align="right">First name</TableCell>
                        <TableCell align="right">Last name</TableCell>
                        <TableCell align="center">ROLE</TableCell>
                        <TableCell align="right">Remove</TableCell>
                    </TableRow>
                    </TableHead>
                    <TableBody>
                            {props.users.map( user => (
                                <TableRow key={user.email}>
                                    <TableCell component="th" scope="row">{user.email}</TableCell>
                                    <TableCell align="right">{user.firstName}</TableCell>
                                    <TableCell align="right">{user.lastName}</TableCell>
                                    <TableCell align="right">
                                        <FormControl variant="outlined" className={classes.formControl}>
                                            <Select
                                                labelId="role-selector"
                                                id="role-selector"
                                                value={roles[user.email]}
                                                onChange={e => handleChangeRole(e, user.email)}
                                            >
                                                {ROLES.map(rs => (
                                                <MenuItem value={rs}>{rs}</MenuItem>
                                                ))}
                                            
                                            </Select>
                                        </FormControl>
                                    </TableCell>
                                    <TableCell align="right">
                                        <Button color='secondary' onClick={() => {
                                            setOpen(true); 
                                            setUserID(user.email)
                                            }}>
                                            <DeleteIcon />
                                        </Button>
                                    </TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </Grid>
        </Grid>
        <RemoveUserDialog 
            handleClose={() => setOpen(false)}
            open={open}
            handleRemove={removeUser}
        />
        <Snackbar open={snackbar.open} autoHideDuration={6000} onClose={handleCloseSnackbar}>
            <Alert onClose={handleCloseSnackbar} severity="success">
                {snackbar.message}
            </Alert>
        </Snackbar>
        <Footer />
    </div >
    )

}


const mapStateToProps = state => ({
    users: state.users.all || []
});

const mapDispatchToProps = {
   getUsers: getUsers
}


export default connect(mapStateToProps, mapDispatchToProps)(Users);