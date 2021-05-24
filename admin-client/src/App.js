import React from 'react';
import { Provider } from 'react-redux';
import Keycloak from 'keycloak-js'
import { ReactKeycloakProvider } from '@react-keycloak/web'
import store from './store/store';
import { ConnectedRouter } from 'connected-react-router';
import { createBrowserHistory } from 'history';
import Routes from './components/Routes';
import NotificationDisplay from './containers/NotificationDisplay';
import { Dimmer, Header, Icon } from 'semantic-ui-react'
import authService from './services/AuthService';

const history = createBrowserHistory();
function App() {
  const keycloak = new Keycloak()
  const initOptions = { pkceMethod: 'S256' , onLoad: 'login-required'}

  
  const handleOnEvent = async (event, error) => {
    if (event === 'onAuthSuccess') {
      if (keycloak.authenticated) {
        authService.login(keycloak.token)
      }
    } else {
      console.log(event)
    }
  }

  const loadingComponent = (
    <Dimmer inverted active={true} page>
      <Header style={{ color: '#4d4d4d' }} as='h2' icon inverted>
        <Icon loading name='cog' />
        <Header.Content>Keycloak is loading
          <Header.Subheader style={{ color: '#4d4d4d' }}>or running authorization code flow with PKCE</Header.Subheader>
        </Header.Content>
      </Header>
    </Dimmer>
  )

  return (
    <ReactKeycloakProvider
      authClient={keycloak}
      initOptions={initOptions}
      LoadingComponent={loadingComponent}
      onEvent={(event, error) => handleOnEvent(event, error)}
    >
      <Provider store={store} >
        <ConnectedRouter history={history}>
          <NotificationDisplay />
          <Routes />
        </ConnectedRouter>
      </Provider>
    </ReactKeycloakProvider>
  );
}

export default App;
