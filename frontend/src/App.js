import React from 'react';
import {BrowserRouter as Router, useRoutes } from 'react-router-dom';
import './App.css';
import Summary from './components/Summary/Summary';
import {useSelector} from "react-redux";
import userService from "./services/UserService";


function Routes() {
  // const userlist = useSelector((state)=>state.user)
  // if (userlist.length == 0)
  // {
  //   userService.fetchUser()
  // }
  let routes = useRoutes([
    { path: "/", element: <Summary /> },
    { path: "summary", element: <Summary />},
    // { path: "timesheet", element: <Timesheet />},
    // { path: "profile", element: <Profile />},
  ])
  return routes;
}

const App = () => {
  return (
    <Router>
      <Routes />
    </Router>
  );
};

export default App;
