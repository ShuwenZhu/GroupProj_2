import { Table, Button } from 'react-bootstrap';
import React, { Component } from 'react'
import NavBar from '../NavBar/NavBar';

class Summary extends Component {
    constructor(props) {
        super(props);

        this.state = {
            data: [],
            numberOfSummary: 5,
        }
    }
    componentDidMount() {
        // need to create service for 5 recent information
        // ApiService.fetchSummary(this.state.numberOfSummary).then((response) => this.setState({ data: response.data }));
    }
    showMore(){
        this.setState({numberOfSummary: this.state.numberOfSummary + 5});
        console.log(this.state.numberOfSummary);
        // ApiService.fetchSummary(this.state.numberOfSummary).then((response) => this.setState({ data: response.data }));
        //idea = once we click the button, the show value will add up to 5. 
        //Later, we can use fetch(show-value) to display the number of the recent week ending.
    }
    render() {
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
                        {/* hard coded for now... need something like 
                        
                        {this.state.data.map(p => 
                        <tr key={p.hardness}>
                            <td>{p.name}</td>...
                        </tr>)}
                        
                        later
                        */}
                        <tr>
                            <td>12/17/2021</td>
                            <td>40</td>
                            <td>Not Started</td>
                            <td>N/A</td>
                            <td>Edit</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>12/10/2021</td>
                            <td>32</td>
                            <td>Incomplete</td>
                            <td>Not approved</td>
                            <td>Edit</td>
                            <td>1 Floating Day Required</td>
                        </tr>
                        <tr>
                            <td>12/03/2021</td>
                            <td>40</td>
                            <td>Complete</td>
                            <td>Not approved</td>
                            <td>View</td>
                            <td></td>
                        </tr>
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