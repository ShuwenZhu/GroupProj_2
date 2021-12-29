import React, { Component }  from 'react';
import NavBar from "../NavBar/NavBar";
import {Table} from "react-bootstrap";
import HRService from "../../services/HRService";

class HRApprove extends Component {

    constructor(props) {
        super(props);
        this.state = {data:''}
        this.create = this.create.bind(this);
        this.approveUpdate = this.approveUpdate.bind(this);
        this.rejectUpdate = this.rejectUpdate.bind(this);
        this.delete = this.delete.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.removeFromLocal = this.removeFromLocal.bind(this);
    }

    componentDidMount() {
        // send HTTP request
        // save it to the state
        HRService.fetchData()
            .then(res => {
                console.log(res.data)
                this.setState({data: res.data})
            }).catch(err=>{console.log(err)})
    }

    create(e) {
        // add entity - POST
        e.preventDefault();
    }
    approveUpdate(e) {
        // update entity - PUT
        // e.preventDefault();
        console.log('approve')
        console.log(e);
        HRService.update(e.userId, e.weekEnding, 'Approved')
            .then(res=>console.log(res.data))
            .catch(err=>console.log(err))
        this.removeFromLocal(e)
    }

    rejectUpdate(e) {
        // update entity - PUT
        // e.preventDefault();
        console.log('reject')
        console.log(e);
        HRService.update(e.userId, e.weekEnding, 'Not Approved')
            .then(res=>console.log(res.data))
            .catch(err=>console.log(err))
        this.removeFromLocal(e)
    }

    removeFromLocal(e){
        var array = [...this.state.data]; // make a separate copy of the array
        var index = array.indexOf(e)
        if (index !== -1) {
            array.splice(index, 1);
            this.setState({data: array});
        }
    }

    delete(e) {
        // delete entity - DELETE
        e.preventDefault();
    }
    handleChange(changeObject) {
        this.setState(changeObject)
    }

    render() {
        if (this.state.data.length === 0) {
            return (
                <>
                <NavBar/>
                <>No available data</>
                </>
            )
        }
        console.log('data')
        console.log(this.state.data)
        return(
        <>
            <NavBar/>
            <h3>HR Timesheet Management</h3>
            <Table striped bordered hover size="sm">
                <thead>
                <tr>
                    <td><p align='center'><b>User ID</b></p></td>
                    <td><p align='center'><b>Weekend Date</b></p></td>
                    <td><p align='center'><b>Total Hours</b></p></td>
                    <td><p align='center'><b>Submission Status</b></p></td>
                    <td><p align='center'><b>Admin Option</b></p></td>
                </tr>
                </thead>
                <tbody>
                {this.state.data &&
                this.state.data.map(row => {
                    if (row.approvalStatus && row.approvalStatus === 'N/A')
                    return <tr align='right' key={row.userId}>
                        <td>{row.userId}</td>
                        <td>{row.weekEnding}</td>
                        <td>{row.compensatedHour}</td>
                        <td>{row.submissionStatus}</td>
                        <td> <div align='center'>
                            <button className='btn-dark' onClick={() => this.approveUpdate(row)}>Accept</button>
                            &nbsp;
                            <button className='btn-secondary' onClick={() => this.rejectUpdate(row)}>Reject</button>
                        </div></td>
                    </tr>
                    else
                        return <></>
                })}
                </tbody>
            </Table>
        </>
        )
    }
}

export default HRApprove