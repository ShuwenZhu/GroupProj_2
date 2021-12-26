import { Table, Button, DropdownButton, Dropdown } from 'react-bootstrap';
import React, { Component } from 'react'
import NavBar from '../NavBar/NavBar';
import TimePicker from 'react-bootstrap-time-picker';

class TimeSheet extends Component {
  constructor(props) {
      super(props);
      this.state = {
          weekEnding: new Date("1/8/2022"),
          billingHours: 32,
          compensatedHours: 40,
          uploadedFormStatus: "Approved Timesheet"
      }
      const daysOfWeek = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
  }
  componentDidMount() {
    
  }


  render() {
    return (
      <>
        <NavBar></NavBar>
        <div class="container">
          <div class="row">
            <div class="col-sm">
              Week Ending {this.state.weekEnding.toLocaleDateString()}
            </div>
            <div class="col-sm">
              Total Billing Hours {this.state.billingHours}
            </div>
            <div class="col-sm">
              Total Compensated Hours {this.state.compensatedHours}
            </div>
          </div>
        </div>
        <Button variant="primary" /*onClick={ set current timesheet as reusable template}*/>
          Set Default
        </Button>        
        <Table striped bordered hover size="sm">
          <thead>
            <tr>
              <th>Day</th>
              <th>Date</th>
              <th>Starting Time</th>
              <th>Ending Time</th>
              <th>Total Hours</th>
              <th>Floating Day</th>
              <th>Holiday</th>
              <th>Vacation</th>
            </tr>
          </thead>
          <tbody>
            {['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'].map( (day) => (
              <tr>
                <td>{day}</td>
                <td>{this.state.weekEnding.toLocaleDateString()}</td>
                <td>
                <TimePicker start="0:00" end="23:00" step={60} initialValue="9:00" />
                </td>
                <td>
                <TimePicker start="0:00" end="23:00" step={60} initialValue="17:00"/>
                </td>
                <td>0</td>
                <td><Button variant="outline-secondary">-</Button></td>
                <td><Button variant="outline-secondary">-</Button></td>
                <td><Button variant="outline-secondary">-</Button></td>
              </tr>
            ))}
          </tbody>
        </Table>

        <div class="container">
          <div class="row">
            <div class="col-sm">
              <DropdownButton id="dropdown-basic-button" title={this.state.uploadedFormStatus}>
                <Dropdown.Item href="#/action-1">Approved Timesheet</Dropdown.Item>
                <Dropdown.Item href="#/action-2">Unapproved Timesheet</Dropdown.Item>
              </DropdownButton>
            </div>
            <div class="col-sm">
              <Button variant="secondary" /*onClick={ let user choose file to upload }*/>
                Choose file
              </Button>
              uploaded file name
            </div>
            <div class="col-sm">
            </div>
            <div class="col-sm">
              <Button variant="primary" /*onClick={ save current Timesheet }*/>
                Save
              </Button>            
            </div>
          </div>
        </div>

      </>
    )
}
}

export default TimeSheet;
