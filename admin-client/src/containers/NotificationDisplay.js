import React, { useState, useEffect } from 'react'
import Snackbar from '@material-ui/core/Snackbar';
import Alert from '@material-ui/lab/Alert';
import { connect } from 'react-redux';
import { resetMessage } from '../store/actions/notificationActions';

function NotificationDisplay(props) {
    const [open, setOpen] = useState(false);

    useEffect(() => {
        setOpen(true);

    }, [props.message])

    const handleClose = (event, reason) => {
        if (reason === 'clickaway')
            return;
        setOpen(false);
        props.reset()
    }
    return (
        <div>
            {props.message === null ? '' :
                <Snackbar open={open} autoHideDuration={2000} onClose={(event, reason) => handleClose(event, reason)}>
                    <Alert onClose={(event, reason) => handleClose(event, reason)} severity={(props.type).toLowerCase()}>
                        {props.message}
                    </Alert>
                </Snackbar>
            }
        </div>
    )
}
const mapStateToProps = state => {
    return {
        message: state.notification.message,
        type: state.notification.type
    }
};

const mapDispatchToProps = {
    reset: resetMessage
}

export default connect(mapStateToProps, mapDispatchToProps)(NotificationDisplay)