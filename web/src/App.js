import React from "react";
import { BrowserRouter as Router, Switch} from "react-router-dom";
import './App.css';
import UserProfile from './UserProfile';
import UserHome from './UserHome';
import Login from "./Login";
import Register from "./Register";
import Post from "./Post";
import PublicRoute from "./PublicRoute";
import PrivateRoute from "./PrivateRoute";
import ResultSearch from "./ResultSearch";

const App = () => {
    return (
      <Router>
          <Switch>
            <PublicRoute path="/register" component={Register} />
            <PublicRoute path="/login" component={Login} />
            <PrivateRoute path={'/post/:id'}  component={Post} />
            <PrivateRoute path='/home' component={UserHome}/>
            <PrivateRoute path={'/user/:id'} component={UserProfile}/>
            <PrivateRoute path={'/search/:key'} component={ResultSearch}/>
            <PrivateRoute path='/' component={UserHome}/>
          </Switch>
      </Router>
    );
};

export default App;
