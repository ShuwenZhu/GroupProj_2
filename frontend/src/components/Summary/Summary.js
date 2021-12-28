import { Table, Button } from 'react-bootstrap';
import React, { Component } from 'react'
import NavBar from '../NavBar/NavBar';
import SummaryService from '../../services/SummaryService';
import {store} from "../../redux/store";
import {useSelector} from "react-redux";
import userService from '../../services/UserService';
import { Popup, Button as But } from 'semantic-ui-react'


class Summary extends Component {
    constructor(props) {
        super(props);

        this.state = {
            data: [],
            numberOfSummary: 5,
        }
    }
    componentDidMount() {
        store.subscribe(()=> SummaryService.fetchTimesheet(store.getState().user[0].id).then((response) => this.setState({ data: response.data })));
        
        
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
        // return p.submissionStatus;
        if(p.submissionStatus === "Incomplete"){
            return <p>{p.submissionStatus} <Popup content='Add users to your feed' trigger={<But>!</But>} /></p>
        }
        else{
            return <p>{p.submissionStatus}</p>
        }  
    }

    approvalStatus(p){
        console.log(p);
        if(p.isApprovedAttachment){
            return <p>Approved</p>
        }
        else{
            return <p>Not Approved<Popup content='Add users to your feed' trigger={<But>!</But>} /></p>
        }  
    }

    commentStatus(p){
        return <td>{this.comment(p)}<Popup content='???' trigger={<But>!</But>} /></td>
    }

    comment(p){
        let floating = 0;
        let vacation = 0;
        let holiday = 0;

        let comment = "";

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
        if(floating > 0){
            comment += floating + " floating day(s) required ";
        }
        if(vacation > 0){
            comment += vacation + " vacation day(s) required ";
        }
        if(holiday > 0){
            comment += holiday + " holiday day(s) were included ";
        }
        return comment;
    }

    option(p){
        if(p.isApprovedAttachment){
            return <Button variant= "primary" onClick={() => this.showMore()}>View</Button>
        }
        else{
            return <Button variant= "primary" onClick={() => this.showMore()}>Edit</Button>
        }
    }

    render() {
        this.state.data.sort((oldDate, newDate) => new Date(...newDate.weekEnding.split('/')) - new Date(...oldDate.weekEnding.split('/')));
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
                            
                            <td>{this.commentStatus(p)}</td>

                            
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