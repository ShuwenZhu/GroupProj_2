import { Table, Button } from 'react-bootstrap';
import React, { Component } from 'react'
import NavBar from '../NavBar/NavBar';
import SummaryService from '../../services/SummaryService';
import {store} from "../../redux/store";
import {useSelector} from "react-redux";
import userService from '../../services/UserService';


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
    render() {
        console.log(store.getState());
        this.state.data.sort((oldDate, newDate) => new Date(...oldDate.weekEnding.split('/')) - new Date(...newDate.weekEnding.split('/'))).reverse();
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
                <Button variant="outline-secondary" onClick={() => this.showLess()}>
                    Show Less
                </Button>
            </>
        )
    }
}
export default Summary