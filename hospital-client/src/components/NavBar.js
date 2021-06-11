import React from 'react';
import clsx from 'clsx';
import { makeStyles, useTheme } from '@material-ui/core/styles';
import { useKeycloak } from '@react-keycloak/web';
import Drawer from '@material-ui/core/Drawer';
import CssBaseline from '@material-ui/core/CssBaseline';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import List from '@material-ui/core/List';
import Typography from '@material-ui/core/Typography';
import Divider from '@material-ui/core/Divider';
import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/Menu';
import MoneyOff from '@material-ui/icons/MoneyOff';
import AddCircle from '@material-ui/icons/AddCircle';
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft';
import ChevronRightIcon from '@material-ui/icons/ChevronRight';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import PhotoLibrary from '@material-ui/icons/PhotoLibrary';
import Info from '@material-ui/icons/Info';
import ExitToApp from '@material-ui/icons/ExitToApp';
import FormatListBulletedIcon from '@material-ui/icons/FormatListBulleted';
import Settings from '@material-ui/icons/Settings';
import { withRouter } from 'react-router-dom';
import { PRIMARY_COLOR, SECONDARY_COLOR } from '../assets/constants';
import { connect } from 'react-redux';
import { logoutAction } from '../store/actions/authActions';
import { REQUESTS, LOGS, ALARMS_BLACKLISTED, ALARMS_BRUTEFORCE, ALARMS_ERROR, ALARMS_INACTIVE, MESSAGE_ALARMS, MESSAGES, PATIENTS, DOCTOR_RULES } from '../routes';
import authService from '../services/AuthService';

const drawerWidth = 240;

const useStyles = makeStyles((theme) => ({
    root: {
        display: 'flex',

    },
    appBar: {
        backgroundColor: PRIMARY_COLOR,
        transition: theme.transitions.create(['margin', 'width'], {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.leavingScreen,
        }),
    },
    appBarShift: {
        width: `calc(100% - ${drawerWidth}px)`,
        marginLeft: drawerWidth,
        transition: theme.transitions.create(['margin', 'width'], {
            easing: theme.transitions.easing.easeOut,
            duration: theme.transitions.duration.enteringScreen,
        }),
    },
    menuButton: {
        marginRight: theme.spacing(2),
    },
    hide: {
        display: 'none',
    },
    drawer: {

        width: drawerWidth,
        flexShrink: 0,
    },
    drawerPaper: {
        backgroundColor: '#FFFFFF',
        width: drawerWidth,
    },
    drawerHeader: {
        display: 'flex',
        alignItems: 'center',
        backgroundColor: SECONDARY_COLOR,
        padding: theme.spacing(0, 1),
        // necessary for content to be below app bar
        ...theme.mixins.toolbar,
        justifyContent: 'flex-end',
    },
    content: {
        flexGrow: 1,
        padding: theme.spacing(3),
        transition: theme.transitions.create('margin', {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.leavingScreen,
        }),
        marginLeft: -drawerWidth,
    },
    contentShift: {
        transition: theme.transitions.create('margin', {
            easing: theme.transitions.easing.easeOut,
            duration: theme.transitions.duration.enteringScreen,
        }),
        marginLeft: 0,
    },
}));

function NavBar(props) {
    const classes = useStyles();
    const theme = useTheme();
    const { keycloak } = useKeycloak();
    const [open, setOpen] = React.useState(false);

    const handleDrawerOpen = () => {
        setOpen(true);
    };

    const handleDrawerClose = () => {
        setOpen(false);
    };

    return (
        <div className={classes.root}>
            <CssBaseline />
            <AppBar
                position="fixed"
                className={clsx(classes.appBar, {
                    [classes.appBarShift]: open,
                })}
            >
                <Toolbar>
                    <IconButton
                        color="inherit"
                        aria-label="open drawer"
                        onClick={handleDrawerOpen}
                        edge="start"
                        className={clsx(classes.menuButton, open && classes.hide)}
                    >
                        <MenuIcon />
                    </IconButton>
                    <Typography variant="h6" noWrap onClick={() => props.history.push('/')}>
                        The Hospital
                    </Typography>
                </Toolbar>
            </AppBar>
            <Drawer
                className={classes.drawer}
                variant="persistent"
                anchor="left"
                open={open}
                classes={{
                    paper: classes.drawerPaper,
                }}
            >
                <div className={classes.drawerHeader}>
                    <IconButton onClick={handleDrawerClose}>
                        {theme.direction === 'ltr' ? <ChevronLeftIcon style={{ color: 'white' }} /> : <ChevronRightIcon />}
                    </IconButton>
                </div>
                <Divider />


                {keycloak?.authenticated && authService.getRole() === 'HOSPITAL_ADMIN' ? <>
                    <List>                        

                        <ListItem onClick={() => props.history.push(REQUESTS)} button key={'Requests'}>
                            <ListItemIcon><AddCircle /></ListItemIcon>
                            <ListItemText primary={'Add Request'} />
                        </ListItem>
                        <ListItem onClick={() => props.history.push(LOGS)} button key={'Logs'}>
                            <ListItemIcon><FormatListBulletedIcon /></ListItemIcon>
                            <ListItemText primary={'See all logs'} />
                        </ListItem>
                        <ListItem onClick={() => props.history.push(ALARMS_BLACKLISTED)} button key={'AlarmsBlacklisted'}>
                            <ListItemIcon><FormatListBulletedIcon /></ListItemIcon>
                            <ListItemText primary={'Alarms: Blacklisted IP Adresses'} />
                        </ListItem>
                        <ListItem onClick={() => props.history.push(ALARMS_BRUTEFORCE)} button key={'AlarmsBruteforce'}>
                            <ListItemIcon><FormatListBulletedIcon /></ListItemIcon>
                            <ListItemText primary={'Alarms: Brute Force Login Attack'} />
                        </ListItem>
                        <ListItem onClick={() => props.history.push(ALARMS_ERROR)} button key={'AlarmsError'}>
                            <ListItemIcon><FormatListBulletedIcon /></ListItemIcon>
                            <ListItemText primary={'Alarms: Error Log'} />
                        </ListItem>
                        <ListItem onClick={() => props.history.push(ALARMS_INACTIVE)} button key={'AlarmsInactive'}>
                            <ListItemIcon><FormatListBulletedIcon /></ListItemIcon>
                            <ListItemText primary={'Alarms: Inactive User'} />
                        </ListItem>
                    </List>
                    <Divider />
                    </>
                : null }
                {keycloak?.authenticated && authService.getRole() === 'DOCTOR' ? <>
                    <List>                        
                        <ListItem onClick={() => props.history.push(MESSAGES)} button key={'Messages'}>
                            <ListItemIcon><FormatListBulletedIcon /></ListItemIcon>
                            <ListItemText primary={'See Messages from Devices'} />
                        </ListItem>
                        <ListItem onClick={() => props.history.push(MESSAGE_ALARMS)} button key={'MessageAlarms'}>
                            <ListItemIcon><FormatListBulletedIcon /></ListItemIcon>
                            <ListItemText primary={'Alarms: Patients'} />
                        </ListItem>
                        <ListItem onClick={() => props.history.push(PATIENTS)} button key={'Patients'}>
                            <ListItemIcon><FormatListBulletedIcon /></ListItemIcon>
                            <ListItemText primary={'See Patients\' Medical Records'} />
                        </ListItem>
                        <ListItem onClick={() => props.history.push(DOCTOR_RULES)} button key={'DoctorRules'}>
                            <ListItemIcon><AddCircle /></ListItemIcon>
                            <ListItemText primary={'Create Alarms for Specific Patients'} />
                        </ListItem>
                    </List>
                    <Divider />
                    </>
                : null }
                {keycloak?.authenticated  ? <>
                    <List>
                        
                        <ListItem onClick={() => keycloak.logout()} button key={'Log Out'}>
                            <ListItemIcon><ExitToApp /></ListItemIcon>
                            <ListItemText primary={'Log Out'} />
                        </ListItem>
                    </List> :


                    <List>


                        <ListItem button onClick={() => {}} key={'Kako postati admin'}>
                            <ListItemIcon><Info /></ListItemIcon>
                            <ListItemText primary={'Kako postati admin'} />
                        </ListItem>

            
                    </List>
                    </> : null }
            </Drawer>

        </div >
    );
}

const mapStateToProps = state => ({
    isAuthenticated: state.auth.isAuthenticated
});

const mapDispatchToProps = {
    logout: logoutAction
}

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(NavBar));