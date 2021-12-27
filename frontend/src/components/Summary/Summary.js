import { Table, Button } from 'react-bootstrap';
import React, { Component } from 'react'
import NavBar from '../NavBar/NavBar';
import SummaryService from '../../services/SummaryService';
// import {useSelector} from "react-redux";

class Summary extends Component {
    constructor(props) {
        super(props);

        this.state = {
            data: [],
            numberOfSummary: 5,
            // sorted_date: [],
        }
        
    }
    componentDidMount() {
        SummaryService.fetchTimesheet(1).then((response) => this.setState({ data: response.data }));
        
    }
    showMore(){
        this.setState({numberOfSummary: this.state.numberOfSummary + 5});
    }
    render() {
        this.state.data.sort((oldDate, newDate) => new Date(...oldDate.weekEnding.split('/')) - new Date(...newDate.weekEnding.split('/'))).reverse();
        console.log(this.state.data);
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
                            <td>{p.submissionStatus}</td>
                            <td>{p.approvalStatus}</td>
                            <td>Edit</td>
                            <td></td>
                        </tr>)}
                    </tbody>
                </Table>
                <Button variant="outline-secondary" onClick={() => this.showMore()}>
                    Show More
                </Button>
            </>
        )
    }
}
export default Summary