import React from 'react'
import Typography from '@material-ui/core/Typography'
import Button from '@material-ui/core/Button'
import Grid from '@material-ui/core/Grid'
import NavBar from '../components/NavBar'
import Footer from '../components/Footer';
import Container from '@material-ui/core/Container';
import { makeStyles } from '@material-ui/core/styles';
import { LOGIN } from '../assets/routes'
import { homePageStyle } from '../assets/styles'


const useStyles = makeStyles(homePageStyle);


export default function HomePage(props) {
    const classess = useStyles();

    return (
        <>
            <NavBar />
            <div className={classess.body}  >
                <Grid container justify="center" direction='column' alignItems='center' md={12} >
                    <Grid item xs={10} md={12} >
                        <Typography variant="h3" style={{ color: 'white', paddingTop: 100 }}>SOMETHING COOL <span style={{ color: '#050A30' }}>IS HERE</span></Typography>
                    </Grid>
                    <Grid item xs={10} md={6} lg={4}>
                        <Typography variant='h6' style={{ color: 'white', margin: '30px 0' }}>
                            Who are we? Admin panel. Welcome.
                    </Typography>


                    </Grid>
                    <Grid item xs>
                        <Button className={classess.button} onClick={() => props.history.push(LOGIN)} variant="contained" color="secondary" >LOGIN</Button>
                    </Grid>
                </Grid>

            </div>
            <Footer />
        </>
    )
}
