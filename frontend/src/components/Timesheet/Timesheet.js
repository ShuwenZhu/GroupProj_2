import { Table, Button, DropdownButton, Dropdown } from 'react-bootstrap';
import React, { Component } from 'react'
import NavBar from '../NavBar/NavBar';
import TimePicker from 'react-bootstrap-time-picker';

const DAYS_OF_WEEK = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday'];

class TimeSheet extends Component {
  constructor(props) {
      super(props);
      this.state = {
          weekEnding: new Date("1/8/2022"),
          billingHours: 32,
          compensatedHours: 40,
          uploadedFormApproval: "Approved Timesheet",
          startingTimes: ["9:00:00","9:00:00","9:00:00","9:00:00","9:00:00"],
          endingTimes: ["9:00:00","9:00:00","9:0:000","9:00:00","9:00:00"],
          floatingDays: [false, false, false, false, false],
          holidays: [false, false, false, true, false],
          vacationDays: [false, false, false, false, false]
      }
  }
  componentDidMount() {

    // load default timesheet for user
  }

  getTotalBillingHours = () => {
    let totalHours = 0; 
    for (let i = 0; i < 5; i++){
      if (!(this.state.floatingDays[i] || this.state.vacationDays[i] || this.state.holidays[i])){
        totalHours += ((this.state.endingTimes[i].replace(/[:]/g,"") - this.state.startingTimes[i].replace(/[:]/g,"")) / 10000);
      }
    }
    return totalHours;
  }

  getTotalCompensatedHours = () => {
    let totalHours = this.getTotalBillingHours();
    for (let i = 0; i < 5; i++){
      if (this.state.floatingDays[i] || this.state.vacationDays[i]){
        totalHours += 8;
      }
    }
    return totalHours;
  }

  handleStartingTimeChange = (time, i) => {
    var date = new Date(null);
    date.setSeconds(time); 
    var result = date.toISOString().substr(11, 8);

    let startingTimes = [...this.state.startingTimes];
    startingTimes[i] = result; 
    console.log(result);
    this.setState({startingTimes});
  }

  handleEndingTimeChange = (time, i) => {
    var date = new Date(null);
    date.setSeconds(time); 
    var result = date.toISOString().substr(11, 8);

    let endingTimes = [...this.state.endingTimes];
    endingTimes[i] = result; 
    this.setState({endingTimes});
  }

  toggleFloatingDay = (i) => {
    // check if it is a holiday
    if (this.state.holidays[i])
      return;

    // check if this day is already toggled on
    let day = this.state.floatingDays[i];

    // if already on, toggle off
    if (day){
      let floatingDays = [...this.state.floatingDays];
      floatingDays[i] = false; 
      this.setState({floatingDays});
    }

    // if off, toggle on and toggle vacation day off
    else{
      let floatingDays = [...this.state.floatingDays];
      floatingDays[i] = true; 
      this.setState({floatingDays});

      let vacationDays = [...this.state.vacationDays];
      vacationDays[i] = false; 
      this.setState({vacationDays}); 
    }
  }

  toggleVacationDay = (i) => {    
    // check if it is a holiday
    if (this.state.holidays[i])
      return;
      
    // check if this day is already toggled on
    let day = this.state.vacationDays[i];

    // if already on, toggle off
    if (day){
      let vacationDays = [...this.state.vacationDays];
      vacationDays[i] = false; 
      this.setState({vacationDays});
    }

    // if off, toggle on and toggle vacation day off
    else{
      let vacationDays = [...this.state.vacationDays];
      vacationDays[i] = true; 
      this.setState({vacationDays});

      let floatingDays = [...this.state.floatingDays];
      floatingDays[i] = false; 
      this.setState({floatingDays}); 
    }
  }

  handleSelect = (selected) => {
    this.setState({
      uploadedFormApproval: selected
    })
  }

  render() {
    return (
      <>
        <NavBar></NavBar>
        <div className="container">
          <div className="row">
            <div className="col-sm">
              Week Ending {this.state.weekEnding.toLocaleDateString()}
            </div>
            <div className="col-sm">
              Total Billing Hours {this.getTotalBillingHours()}
            </div>
            <div className="col-sm">
              Total Compensated Hours {this.getTotalCompensatedHours()}
            </div>
          </div>
        </div>       
        <Table striped bordered hover size="sm">
          <thead>
            <tr>
              <th>Day</th>
              <th>Date</th>
              <th>Starting Time</th>
              <th>Ending Time</th>
              <th>Total Hours</th>
              <th>Floating Day</th>
              <th>Vacation</th>
              <th>Holiday</th>
            </tr>
          </thead>
          <tbody>
            {DAYS_OF_WEEK.map( (day, index) => (
              <tr>
                <td>{day}</td>
                <td>{new Date(new Date(this.state.weekEnding).setDate(this.state.weekEnding.getDate() - (5 - index))).toLocaleDateString()}</td>
                <td>
                  {(!this.state.floatingDays[index] && !this.state.vacationDays[index] && !this.state.holidays[index])
                    ? <TimePicker start="0:00" end="23:00" step={60} initialValue="9:00" onChange={(e) => this.handleStartingTimeChange(e, index)} value={this.state.startingTimes[index]}/>
                    : <div></div>
                  }
                </td>
                <td>
                {(!this.state.floatingDays[index] && !this.state.vacationDays[index] && !this.state.holidays[index]) 
                    ? <TimePicker start="0:00" end="23:00" step={60} initialValue="17:00" onChange={(e) => this.handleEndingTimeChange(e, index)} value={this.state.endingTimes[index]}/>
                    : <div></div>
                  }
                </td>
                <td>
                  {(!this.state.floatingDays[index] && !this.state.vacationDays[index] && !this.state.holidays[index])
                  ? <>{((this.state.endingTimes[index].replace(/[:]/g,"") - this.state.startingTimes[index].replace(/[:]/g,""))) / 10000}</>
                  : <>N/A</>}</td>
                <td><Button variant="outline-secondary" onClick={() => this.toggleFloatingDay(index)}>{(this.state.floatingDays[index]) ? "X" : "-"}</Button></td>
                <td><Button variant="outline-secondary" onClick={() => this.toggleVacationDay(index)}>{(this.state.vacationDays[index]) ? "X" : "-"}</Button></td>
                <td><Button variant="outline-secondary">{(this.state.holidays[index]) ? "X" : "-"}</Button></td>
              </tr>
            ))}
          </tbody>
        </Table>

        <div className="container">
          <div className="row">
            <div className="col-sm">
              <DropdownButton id="dropdown-basic-button" title={this.state.uploadedFormApproval} value={this.state.uploadedFormApproval} onSelect={this.handleSelect}>
                <Dropdown.Item eventKey="Approved Timesheet">Approved Timesheet</Dropdown.Item>
                <Dropdown.Item eventKey="Unapproved Timesheet">Unapproved Timesheet</Dropdown.Item>
              </DropdownButton>
            </div>
            <div className="col-sm">
              <Button variant="secondary" /*onClick={ let user choose file to upload }*/>
                Choose file
              </Button>
              uploaded file name
            </div>
            <div className="col-sm">
              <Button variant="primary" /*onClick={ set current timesheet as reusable template}*/>
                Set Default
              </Button> 
            </div>
            <div className="col-sm">
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
