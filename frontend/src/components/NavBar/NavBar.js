import { Navbar, Container } from 'react-bootstrap';
import React, { Component } from 'react'
import UserService from "../../services/UserService";
import {store} from "../../redux/store";
import {useSelector} from "react-redux";
import userService from "../../services/UserService";

function User() { // Rule 2: call hooks in function component
    const username = useSelector(state => state.user[0].username); // Rule 1: call hooks in top-level
    console.log('username:')
    console.log(username)
    return <>{username}</>
}

class NavBar extends Component {

    render() {
        return (
            <>
                <link
                    rel="stylesheet"
                    href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
                    integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
                    crossOrigin="anonymous"
                />
                  <Navbar expand="lg" variant="dark" bg="secondary" sticky="top">
                    <Container>
                      <Navbar.Brand href="/summary">Summary</Navbar.Brand>
                      <Navbar.Brand href="/timesheet">Timesheet</Navbar.Brand>
                      <Navbar.Brand href="/profile">Profile</Navbar.Brand>
                      <Navbar.Brand href='http://localhost:9999/logout?redirect=http://localhost:3000'> Log Out</Navbar.Brand>
                    </Container>
                      <User />
                  </Navbar>
                  <br></br>
                </>
        )
    }
}
export default NavBar