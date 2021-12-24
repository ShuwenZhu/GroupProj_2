import React from 'react';
import {BrowserRouter as Router, useRoutes } from 'react-router-dom';
import './App.css';
import Summary from './components/Summary/Summary';


function Routes() {
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
