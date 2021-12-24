import React from 'react';
import {BrowserRouter as Router, useRoutes } from 'react-router-dom';
import './App.css';
import Summary from './components/Summary/Summary';
import {useSelector} from "react-redux";
import userService from "./services/UserService";


function Routes() {
  let user = useSelector((state)=> state.user);
  if (user.length == 0)
  {
    console.log("not logged in")
    userService.fetchUser()
  } else
    console.log(user)
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
