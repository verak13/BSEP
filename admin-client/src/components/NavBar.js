import React from 'react';
import clsx from 'clsx';
import { makeStyles, useTheme } from '@material-ui/core/styles';
import Drawer from '@material-ui/core/Drawer';
import CssBaseline from '@material-ui/core/CssBaseline';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import List from '@material-ui/core/List';
import Typography from '@material-ui/core/Typography';
import Divider from '@material-ui/core/Divider';
import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/Menu';
import { useKeycloak } from '@react-keycloak/web';
import AddCircle from '@material-ui/icons/AddCircle';
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft';
import ChevronRightIcon from '@material-ui/icons/ChevronRight';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import PhotoLibrary from '@material-ui/icons/PhotoLibrary';
import Info from '@material-ui/icons/Info';
import ExitToApp from '@material-ui/icons/ExitToApp';
import authService from '../services/AuthService';
import { withRouter } from 'react-router-dom';
import { PRIMARY_COLOR, SECONDARY_COLOR } from '../assets/constants';
import { connect } from 'react-redux';
import PersonIcon from '@material-ui/icons/Person';
import { CERTIFICATES } from '../assets/routes';
import { REQUESTS, ADD_CONFIG, HOSPITALS } from '../routes';
import FormatListBulletedIcon from '@material-ui/icons/FormatListBulleted';
import ExitToAppIcon from '@material-ui/icons/ExitToApp';


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
    const { keycloak } = useKeycloak();
    const theme = useTheme();
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
                        Admin Panel
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


                {keycloak?.authenticated && authService.getRole() === 'SUPER_ADMIN' ?<>
                    <List>
                        <ListItem onClick={() => props.history.push(CERTIFICATES)} button key={'Certificates'}>
                            <ListItemIcon><FormatListBulletedIcon /></ListItemIcon>
                            <ListItemText primary={'Certificates'} />
                        </ListItem>

                        <ListItem onClick={() => props.history.push(REQUESTS)} button key={'Requests'}>
                            <ListItemIcon><FormatListBulletedIcon /></ListItemIcon>
                            <ListItemText primary={'Requests'} />
                        </ListItem>

                        <ListItem onClick={() => props.history.push(HOSPITALS)} button key={'Hospitals'}>
                            <ListItemIcon><FormatListBulletedIcon /></ListItemIcon>
                            <ListItemText primary={'See Hospitals'} />
                        </ListItem>

                        <ListItem onClick={() => props.history.push(ADD_CONFIG)} button key={'AddConfig'}>
                            <ListItemIcon><AddCircle /></ListItemIcon>
                            <ListItemText primary={'Add Configuration for Hospital'} />
                        </ListItem>
                        <ListItem onClick={() => window.location.href = "https://localhost:3001"} button key={'SuperAdmin'}>
                            <ListItemIcon><ExitToAppIcon /></ListItemIcon>
                            <ListItemText primary={'Go to Hospital App'} />
                        </ListItem>
                    </List>
                    <Divider />
                    <List>
                        <ListItem onClick={() => keycloak.logout()} button key={'Log Out'}>
                            <ListItemIcon><ExitToApp /></ListItemIcon>
                            <ListItemText primary={'Log Out'} />
                        </ListItem>
                    </List></> :


                    <List>




            
                    </List>}
            </Drawer>

        </div >
    );
}

export default withRouter(NavBar);