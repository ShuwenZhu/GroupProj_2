import React from 'react';
import {BrowserRouter as Router, useRoutes } from 'react-router-dom';
import './App.css';
import Summary from './components/Summary/Summary';
import Profile from './components/Profile/Profile';
import Timesheet from './components/Timesheet/Timesheet';
import {useSelector} from "react-redux";
import userService from "./services/UserService";
import {addUserS, store} from "./redux/store";
import User from "./components/User/User";
import HRApprove from "./components/HRApprove/HRApprove";

function Routes() {
  const userlist = useSelector((state)=>state.user)  // Rule 1: call hooks in top-level
  if (userlist.length == 0) {
    userService.fetchUser()
  }

  // console.log(userlist[0])

  let routes = useRoutes([
    { path: "/", element: <Summary /> },
    { path: "summary", element: <Summary />},
    { path: "timesheet", element: <Timesheet />},
    // { path: "profile", element: <Profile />},
    {path: 'HRManage', element: <HRApprove/>}
  ])
  return routes;
}

const App = () => {
  return (
    <Router>
      <User/>
      <Routes/>
    </Router>
  );
};

export default App;
