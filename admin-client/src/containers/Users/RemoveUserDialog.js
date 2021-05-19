import React from 'react';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import { Button } from '@material-ui/core';

function RemoveUserDialog(props) {

    return (
      <Dialog 
        open={props.open} 
        onClose={props.handleClose}
        aria-labelledby="form-dialog-title"
      >
        <DialogTitle id="form-dialog-title">Remove user</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Are you sure you want to remove this user?
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={props.handleClose} color="primary">
            Cancel
          </Button>
          <Button onClick={props.handleRemove} color="secondary">
            Remove
          </Button>
        </DialogActions>
      </Dialog>

    )
}

export default RemoveUserDialog;