import { Navbar, Container } from 'react-bootstrap';
import React, { Component } from 'react'

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
                        <Navbar.Brand href='/HRManage'>Manage</Navbar.Brand>
                      <Navbar.Brand href='http://localhost:9999/logout?redirect=http://localhost:3000'> Log Out</Navbar.Brand>
                    </Container>
                  </Navbar>
                  <br></br>
                </>
        )
    }
}
export default NavBar