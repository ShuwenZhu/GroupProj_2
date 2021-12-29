import { Form, Button, Row } from 'react-bootstrap';
import React, { Component } from 'react'
import NavBar from '../NavBar/NavBar';
import {store} from "../../redux/store";
import ProfileService from "../../services/ProfileService";
// import { View, ScrollView, Text, StyleSheet } from 'react-native';
// import { Avatar } from 'react-native-elements';


    class Profile extends React.Component {
        constructor(props) {
          super(props);
          this.state = {
            data: [],
            numberOfEmergencyContact: 2,
          };
      
          this.handleChange = this.handleChange.bind(this);
          this.handleSubmit = this.handleSubmit.bind(this);
        }
      
        handleChange(event) {
          this.setState({value: event.target.value});
        }
      
        handleSubmit(event) {
          alert('A name was submitted: ' + this.state.value);
          event.preventDefault();
        }

        componentDidMount() {
          // send HTTP request
          // save it to the state
          store.subscribe(
            ()=> ProfileService.fetchData(store.getState().user[0].id)
                .then(
                    (response) => this.setState({ data: response.data})
                    )
            );
      }
      
        render() {
          
          if (this.state.data.length === 0) {
            return (
                <>    
                <NavBar/>            
                <>No available data</>
                </>
            )
         }else
         {
          console.log('data')
          console.log(this.state.data)
          return <>
              <NavBar/>
              <form>
              <div>
                Contact<br/><br/>
                
                {this.state.data.phoneNumber}<br/><br/>
                {this.state.data.email}<br/><br/>
                {this.state.data.addr}<br/><br/>
                
              </div>
              <br/>
              {this.state.data.emergencyContact.map(
                ecinfo => {
                  
                }
              )}
              emergencyContact
              <br/>

              </form>
          </>
         }
        }
      }
        
      


            {/* <form onSubmit={this.handleSubmit}>
              <label>
                Contact<br></br>
                <input type="text" value={this.state.value} onChange={this.handleChange} /><br></br>
                <br></br>
                <input type="text" value={this.state.value} onChange={this.handleChange} /><br></br>
                <br></br>
                <input type="text" value={this.state.value} onChange={this.handleChange} /><br></br>
                <br></br>
              </label>
              <br></br>
              <label>
                Emergency Contact1<br></br>
                <input type="text" value={this.state.value} onChange={this.handleChange} /><br></br>
                <br></br>
                <input type="text" value={this.state.value} onChange={this.handleChange} /><br></br>
                <br></br>
              </label>
              <br></br>
              <label>
                Emergency Contact2<br></br>
                <input type="text" value={this.state.value} onChange={this.handleChange} /><br></br>
                <br></br>
                <input type="text" value={this.state.value} onChange={this.handleChange} /><br></br>
                <br></br>
              </label>
            <br></br>
            <input type="submit" value="Submit" /> 
            </form> */}
          
            
          
       

      export default Profile