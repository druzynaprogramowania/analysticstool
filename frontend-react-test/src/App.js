import React, { Component } from 'react';
import './App.css';
import Dashboard from './Layout/Dashboard';
import Header from './Layout/Header';
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route } from 'react-router-dom';
import AddItem from './components/Item/AddItem';
import { Provider } from 'react-redux';
import store from './store';
import UpdateItem from './components/Item/UpdateItem';
import Landing from './Layout/Landing';
import Register from './UserManagement/Register';
import Login from './UserManagement/Login';
import Chart from './components/Chart';

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

            <Route exact path="/chart" component={Chart}/>
            <Route exact path="/dashboard" component={Dashboard} />
            <Route exact path="/addItem" component={AddItem} />
            <Route exact path="/updateItem/:id" component={UpdateItem} />
          </div>
        </Router>
      </Provider>
    );
  }
}

export default App;
