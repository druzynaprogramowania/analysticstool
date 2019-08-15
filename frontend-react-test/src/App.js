import React, { Component } from 'react';
import './App.css';
import Dashboard from './Layout/Dashboard';
import Header from './Layout/Header';
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import AddItem from './components/Item/AddItem';
import { Provider } from 'react-redux';
import store from './store';
import UpdateItem from './components/Item/UpdateItem';
import Landing from './Layout/Landing';
import Register from './UserManagement/Register';
import Login from "./UserManagement/Login";
import jwt_decode from "jwt-decode";
import setJWTToken from "./securityUtils/setJWTToken";
import { SET_CURRENT_USER } from "./actions/types";
import { logout } from "./actions/securityActions";
import SecuredRoute from "./securityUtils/SecureRoute";

const jwtToken = localStorage.jwtToken;

if (jwtToken) {
  setJWTToken(jwtToken);
  const decoded_jwtToken = jwt_decode(jwtToken);
  store.dispatch({
    type: SET_CURRENT_USER,
    payload: decoded_jwtToken
  });

  const currentTime = Date.now() / 1000;
  if (decoded_jwtToken.exp < currentTime) {
    store.dispatch(logout());
    window.location.href = "/";
  }
}

class App extends Component {
  render() {
    return (
      <Provider store={store}>
        <Router>
          <div className="App">
            <Header />

            <Route exact path="/" component={Landing} />
            <Route exact path="/register" component={Register} />
            <Route exact path="/login" component={Login} />

            <Switch>
              <SecuredRoute exact path="/dashboard" component={Dashboard} />
              <SecuredRoute exact path="/addItem" component={AddItem} />
              <SecuredRoute exact path="/updateItem/:id" component={UpdateItem} />           
            </Switch>        
          </div>
        </Router>
      </Provider>
    );
  }
}

export default App;
