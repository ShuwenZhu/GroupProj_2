import { Table, Button } from 'react-bootstrap';
import React, { Component } from 'react'
import NavBar from '../NavBar/NavBar';
import SummaryService from '../../services/SummaryService';
import ProfileService from '../../services/ProfileService';
import {store} from "../../redux/store";
import { Popup, Button as But } from 'semantic-ui-react';
import 'semantic-ui-css/semantic.min.css'

class Summary extends Component {
    constructor(props) {
        super(props);

        this.state = {
            data: [],
            numberOfSummary: 5,
            contact: [],
        }
    }
    
    componentDidMount() {
        store.subscribe(()=> SummaryService.fetchTimesheet(store.getState().user[0].id).then((response) => this.setState({ data: response.data })));
        store.subscribe(()=> ProfileService.fetchData(store.getState().user[0].id).then((response) => this.setState({ contact: response.data })));
    }
    showMore(){
        if(this.state.numberOfSummary < this.state.data.length){
            this.setState({numberOfSummary: this.state.numberOfSummary + 5});
        }
    }
    showLess(){
        if(this.state.numberOfSummary > 5){
            this.setState({numberOfSummary: this.state.numberOfSummary - 5});
        }
        
    }

    submissionStatus(p){
        if(p.submissionStatus === "Incomplete"){
            return <p>{p.submissionStatus} <Popup content='Items due: Proof of Approved TimeSheet' trigger={<But>!</But>}/></p>
        }
        else{
            return <p>{p.submissionStatus}</p>
        }  
    }

    approvalStatus(p){     
        if(p.approvalStatus === "Approved"){
            return <p>Approved</p>
        }
        else if(p.approvalStatus === "Not Approved"){
            return <p>Not Approved <Popup content='Approval denied by Admin, please contact your HR manager' trigger={<But>!</But>}/></p>
        }
        else{
            return <p>N/A</p>
        }  
    }

    comment(p){
        let floating = 0;
        let vacation = 0;
        let holiday = 0;

        let year = p.weekEnding.split("/")[2];
        let floatingLeftOver = this.state.contact.maxFloatDays - this.state.contact.usedFloatDays;
        let vacationLeftOver = this.state.contact.maxVacationDays - this.state.contact.usedVacationDays;

        for(let i = 0; i < p.timesheet.length; i++){
            if(p.timesheet[i].isFloating === true){
                floating += 1;
            }
            else if(p.timesheet[i].isVacation === true){
                vacation += 1;
            }
            else if(p.timesheet[i].isHoliday === true){
                holiday += 1;
            }
        }

        if(floating > 0 && vacation > 0 && holiday > 0){
            return <p><p>{floating} floating day(s) required <Popup content={"Total floating days left in " + year + ": " + floatingLeftOver + " days"} trigger={<But>!</But>} /></p>
            <p>{vacation} vacation day(s) required <Popup content={"Total vacation days left in " + year + ": " + vacationLeftOver + " days"} trigger={<But>!</But>} /></p>
            <p>{holiday} holiday day(s) were included </p>
            </p>;
        }
        else if(floating > 0 && vacation > 0){
            return <p><p>{floating} floating day(s) required <Popup content={"Total floating days left in " + year + ": " + floatingLeftOver + " days"} trigger={<But>!</But>} /></p>
            <p>{vacation} vacation day(s) required <Popup content={"Total vacation days left in " + year + ": " + vacationLeftOver + " days"} trigger={<But>!</But>} /></p>
            </p>;
        }
        else if(floating > 0 && holiday > 0){
            return <p><p>{floating} floating day(s) required <Popup content={"Total floating days left in " + year + ": " + floatingLeftOver + " days"} trigger={<But>!</But>} /></p>
            <p>{holiday} holiday day(s) were included </p>
            </p>;
        }
        else if(vacation > 0 && holiday > 0){
            return <p><p>{vacation} vacation day(s) required <Popup content={"Total vacation days left in " + year + ": " + vacationLeftOver + " days"} trigger={<But>!</But>} /></p>
            <p>{holiday} holiday day(s) were included </p></p>;
        }

        else if(floating > 0){
            return <p>{floating} floating day(s) required <Popup content={"Total floating days left in " + year + ": " + floatingLeftOver + " days"} trigger={<But>!</But>} /></p>;
        }
        else if(vacation > 0){
            return <p>{vacation} vacation day(s) required <Popup content={"Total vacation days left in " + year + ": " + vacationLeftOver + " days"} trigger={<But>!</But>} /></p>;
        }
        else if(holiday > 0){
            return <p>{holiday} holiday day(s) were included</p>;
        }
    }

    option(p){
        if(p.approvalStatus === "Approved" && p.submissionStatus == "Completed"){
            return <Button variant= "primary" onClick={() => this.redirectTimesheet(p)}>View</Button>
        }
        else{
            return <Button variant= "primary" onClick={() => this.redirectTimesheet(p)}>Edit</Button>
        }
    }

    redirectTimesheet(p){
        let original = p.weekEnding;
        let slashTo2F = original.replaceAll("/", "%2F");
        window.location = "/timesheet?weekEnding=" + slashTo2F + "&user=" + p.userId;
    }

    render() {
        this.state.data.sort((oldDate, newDate) => new Date(...newDate.weekEnding.split('/')) + new Date(...oldDate.weekEnding.split('/')));
        return (
            <>
                <NavBar></NavBar>
                <Table striped bordered hover size="sm">
                    <thead>
                        <tr>
                            <th>Week Ending</th>
                            <th>Total Hours</th>
                            <th>Submission Status</th>
                            <th>Approval Status</th>
                            <th>Option</th>
                            <th>Comment</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.state.data.slice(0, this.state.numberOfSummary).map(p => 
                        <tr key={p.weekEnding}>
                            <td>{p.weekEnding}</td>
                            <td>{p.compensatedHour}</td>
                            
                            <td>{this.submissionStatus(p)}</td>
                            <td>{this.approvalStatus(p)}</td>
                            <td>{this.option(p)}</td>
                            <td>{this.comment(p)}</td>

                        </tr>)}
                    </tbody>
                </Table>
                <Button variant="outline-secondary" onClick={() => this.showMore()}>
                    Show More
                </Button>
                <Button variant="outline-secondary" onClick={() => this.showLess()}>
                    Show Less
                </Button>
            </>
        )
    }
}
export default Summary