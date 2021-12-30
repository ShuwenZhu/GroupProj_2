import { Form, Button, Container, Row, Col, View, Image, StyleSheet } from 'react-bootstrap';
import React, { Component } from 'react'
import NavBar from '../NavBar/NavBar';
import {store} from "../../redux/store";
import ProfileService from "../../services/ProfileService";

const IMAGE_EX = "https://preview.redd.it/dm4yvdcd11771.jpg?width=360&format=pjpg&auto=webp&s=ddd96212d2b35ede767845b7a26cfac5e6276cda";
    class Profile extends React.Component {
        constructor(props) {
          super(props);
          this.state = {
            temp1:"",
            temp2:"",
            temp3:"",
            EC1FN:"",
            EC1LN:"",
            EC1Contact:"",
            EC2FN:"",
            EC2LN:"",
            EC2Contact:"",
            file:null,
            filePath:"",
            autoImage: true,
            imageExample:"https://preview.redd.it/dm4yvdcd11771.jpg?width=360&format=pjpg&auto=webp&s=ddd96212d2b35ede767845b7a26cfac5e6276cda",
            data: [],
            displaystate: true,
            numberOfEmergencyContact: 2,
          };
      
          // this.handleInputChange = this.handleInputChange(this);
          this.handlePhoneNumberChange = this.handlePhoneNumberChange.bind(this);
          this.handleEmailChange = this.handleEmailChange.bind(this);
          this.handleAddressChange = this.handleAddressChange.bind(this);

          this.handleEC1FNChange = this.handleEC1FNChange.bind(this);
          this.handleEC1LNChange = this.handleEC1LNChange.bind(this);
          this.handleEC1ContactChange = this.handleEC1ContactChange.bind(this);

          this.handleEC2FNChange = this.handleEC2FNChange.bind(this);
          this.handleEC2LNChange = this.handleEC2LNChange.bind(this);
          this.handleEC2ContactChange = this.handleEC2ContactChange.bind(this);

          this.handleFileChange = this.handleFileChange.bind(this);
          this.handleFileSubmit = this.handleFileSubmit.bind(this);
          this.handleSubmit = this.handleSubmit.bind(this);
          this.handleClick = this.handleClick.bind(this);
        }

        handleClick() {
          this.setState({
            displaystate: !this.state.displaystate
          });
        }

        handlePhoneNumberChange(event) {
          this.setState({
            temp1 : event.target.value
          });
        }

        handleEmailChange(event) {
          this.setState({
            temp2 : event.target.value
          });
        }

        handleAddressChange(event) {
          this.setState({
            temp3 : event.target.value
          });
        }
      
        handleEC1FNChange(event) {
          this.setState({EC1FN : event.target.value});
        }
        handleEC1LNChange(event) {
          this.setState({EC1LN : event.target.value});
        }
        handleEC1ContactChange(event) {
          this.setState({EC1Contact : event.target.value});
        }

        handleEC2FNChange(event) {
          this.setState({EC2FN : event.target.value});
        }
        handleEC2LNChange(event) {
          this.setState({EC2LN : event.target.value});
        }
        handleEC2ContactChange(event) {
          this.setState({EC2Contact : event.target.value});
        }

        handleSubmit(event) {
          event.preventDefault();
          ProfileService.updateContact(
            this.state.data.userId,
            this.state.temp1,
            this.state.temp2,
            this.state.temp3,
            this.state.EC1FN,
            this.state.EC1LN,
            this.state.EC1Contact,
            this.state.EC2FN,
            this.state.EC2LN,
            this.state.EC2Contact,
             );



          // this.state.data.phoneNumber = this.state.temp1;
          // this.state.data.email = this.state.temp2;
          // this.state.data.addr = this.state.temp3;

          // this.state.data.emergencyContact[0].firstName = this.state.EC1FN;
          // this.state.data.emergencyContact[0].lastName = this.state.EC1LN;
          // this.state.data.emergencyContact[0].contactNumber = this.state.EC1Contact;

          // this.state.data.emergencyContact[1].firstName = this.state.EC2FN;
          // this.state.data.emergencyContact[1].lastName = this.state.EC2LN;
          // this.state.data.emergencyContact[1].contactNumber = this.state.EC2Contact;

          // console.log(this.state.data);
        }

        handleFileChange(event) {
          this.setState({
            file: URL.createObjectURL(event.target.files[0]),
            // file: e.target.files[0],
            // imageExample: e.target.files[0]
          })
        }

        handleFileSubmit = (event) => {
          this.setState({
            autoImage: !this.state.autoImage,
          });
          // this.state.autoImage = false;
          this.state.data.avatar = this.state.file;
          console.log(this.state.data.avatar);
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

      getFirstFullName() {
        if (this.state.data.emergencyContact[0].firstName === "") {
          return 'N/A'
        }else{
          return this.state.data.emergencyContact[0].firstName + " " + this.state.data.emergencyContact[0].lastName;
        } 
      }

      getFirstPhoneNumber() {
        if (this.state.data.emergencyContact[0].contactNumber === "") {
          return 'N/A';
        }else{
          return this.state.data.emergencyContact[0].contactNumber;
        } 
      }
      
      getSecondFullName() {
        if (this.state.data.emergencyContact[1].firstName === "") {
          return 'N/A';
        }else{
          return this.state.data.emergencyContact[1].firstName + " " + this.state.data.emergencyContact[1].lastName;  
        } 
      }

      getSecondPhoneNumber() {
        if (this.state.data.emergencyContact[1].contactNumber === "") {
          return 'N/A';
        }else{
          return this.state.data.emergencyContact[1].contactNumber;
        } 
      }

      

        render() {
          
          if (this.state.data.length === 0) {
            return (
                <>    
                <NavBar/>            
                <>No available data</>
                </>
            )
         }else{
          // console.log('data')
          // console.log(this.state.data)
          return <>
              <NavBar/>
              <img src= {this.state.imageExample} hidden={!this.state.autoImage}/>
              <img src= {this.state.file} hidden={this.state.autoImage}/>
              <form onSubmit={this.handleSubmit} style={{margin: 100}}>
                
                <h3><b>Contact</b></h3>

                <label>Phone Number:
                <input hidden={this.state.displaystate} 
                onChange={this.handlePhoneNumberChange}
                defaultValue={this.state.data.phoneNumber} 
                />
                
                <p hidden={!this.state.displaystate}>{this.state.data.phoneNumber}</p>
                </label>

                <br/><br/>

                <label>Email:
                <input hidden={this.state.displaystate} 
                onChange={this.handleEmailChange}
                defaultValue={this.state.data.email}
                />
                
                <p hidden={!this.state.displaystate}>{this.state.data.email}</p>
                </label>

                <br/><br/>

                <label>Address:
                <input hidden={this.state.displaystate} 
                onChange={this.handleAddressChange}
                defaultValue={this.state.data.addr}
                />
                
                <p hidden={!this.state.displaystate}>{this.state.data.addr}</p>
                </label>

                <br/><br/>

                <h3><b>Emergency Contact 1</b></h3>

                <label>
                  <p hidden={!this.state.displaystate}>Full Name:</p>
                  <p hidden={this.state.displaystate}>First Name:</p>
                  <input hidden={this.state.displaystate} 
                  onChange={this.handleEC1FNChange}
                  defaultValue={this.state.data.emergencyContact[0].firstName=="" ? 'N/A' : this.state.data.emergencyContact[0].firstName}
                  />
                  <p hidden={this.state.displaystate}>Last Name:</p>
                  <input hidden={this.state.displaystate} 
                  onChange={this.handleEC1LNChange}
                  defaultValue={this.state.data.emergencyContact[0].lastName=="" ? 'N/A' : this.state.data.emergencyContact[0].lastName}
                  />

                <p hidden={!this.state.displaystate}>{this.getFirstFullName()}</p>
                </label>

                <br/><br/>

                <label>Contact Phone Number:
                <input hidden={this.state.displaystate} 
                onChange={this.handleEC1ContactChange}
                defaultValue={this.state.data.emergencyContact[0].contactNumber=="" ? 'N/A' : this.state.data.emergencyContact[0].contactNumber}
                />
                
                <p hidden={!this.state.displaystate}>{this.getFirstPhoneNumber()}</p>
                </label>

                <br/><br/>

                <h3><b>Emergency Contact 2</b></h3>

                <label>
                  <p hidden={!this.state.displaystate}>Full Name:</p>
                  <p hidden={this.state.displaystate}>First Name:</p>
                  <input hidden={this.state.displaystate} 
                  onChange={this.handleEC2FNChange}
                  defaultValue={this.state.data.emergencyContact[1].firstName=="" ? 'N/A' : this.state.data.emergencyContact[1].firstName}
                  />
                  <p hidden={this.state.displaystate}>Last Name:</p>
                  <input hidden={this.state.displaystate} 
                  onChange={this.handleEC2LNChange}
                  defaultValue={this.state.data.emergencyContact[1].lastName=="" ? 'N/A' : this.state.data.emergencyContact[1].lastName}
                  />

                <p hidden={!this.state.displaystate}>{this.getSecondFullName()}</p>
                </label>

                <br/><br/>

                <label>Contact Phone Number:
                <input hidden={this.state.displaystate} 
                onChange={this.handleEC2ContactChange}
                defaultValue={this.state.data.emergencyContact[0].contactNumber=="" ? 'N/A' : this.state.data.emergencyContact[1].contactNumber}
                />
                
                <p hidden={!this.state.displaystate}>{this.getSecondPhoneNumber()}</p>
                </label>

                <br/><br/>

                <Button hidden={!this.state.displaystate} onClick={this.handleClick}>Edit</Button>
              
                <Button hidden={this.state.displaystate} type="submit" value="Submit">Save</Button>
                <span>&nbsp;&nbsp;</span>
                <Button hidden={this.state.displaystate} onClick={this.handleClick}>Cancel</Button>
                
              </form>

              <Container>
          <Row>
            <Col xs={6}>
              <Form.Group controlId="formFileSm" className="mb-3" onChange={this.handleFileChange}>
                <Form.Control type="file" />
                <img src={this.state.file} hidden={!this.state.autoImage}/>
              </Form.Group>
            </Col>
            <Col>
            </Col>
            <Col>
              <Button onClick={this.handleFileSubmit}>
                Submit
              </Button>
            </Col>
          </Row>
        </Container>
              
          </>
         }
        }
      }
       

      export default Profile

      {/* <div style={{width: 500}}>
              <Form align='center'>
                <div style={{width: 500, margin: 100}}>
                <h2>Personal Contact</h2>
              <Form.Group as={Row} className="mb-3" controlId="formContact" >
              <p><Form.Label column align='left'>
                PhoneNumber
                </Form.Label></p>
                <Form.Control plaintext readOnly defaultValue= {this.state.data.phoneNumber} align='center'/>
              </Form.Group></div>

              <Form.Group as={Row} className="mb-3" controlId="formPlaintextPassword">
                <Form.Label column sm="2">
                Email
                </Form.Label>
                <Form.Control plaintext readOnly defaultValue= {this.state.data.email} />
               </Form.Group>

               <Form.Group as={Row} className="mb-3" controlId="formPlaintextPassword">
                <Form.Label column sm="2">
                Address
                </Form.Label>
                <Form.Control plaintext readOnly defaultValue= {this.state.data.addr} />
               </Form.Group>
               
              <br/><br/>

              <h2>Emergency Contact 1</h2>
              <Form.Group as={Row} className="mb-3" controlId="formPlaintextPassword">
                <Form.Label column sm="2">
                FullName
                </Form.Label>
                <Form.Control plaintext readOnly defaultValue= {this.getFirstFullName()}/>
              </Form.Group>

              <Form.Group as={Row} className="mb-3" controlId="formPlaintextPassword">
                <Form.Label column sm="2">
                ContactNumber
                </Form.Label>
                <Form.Control plaintext readOnly defaultValue= {this.getFirstPhoneNumber()}/>
              </Form.Group>

              <br/><br/>

              <h2>Emergency Contact 2</h2>
              <Form.Group as={Row} className="mb-3" controlId="formPlaintextPassword">
                <Form.Label column sm="2">
                FullName
                </Form.Label>
                <Form.Control plaintext readOnly defaultValue= {this.getSecondFullName()}/>
              </Form.Group>

              <Form.Group as={Row} className="mb-3" controlId="formPlaintextPassword">
                <Form.Label column sm="2">
                ContactNumber
                </Form.Label>
                <Form.Control plaintext readOnly defaultValue= {this.getSecondPhoneNumber()}/>
              </Form.Group>

              <Form.Group as={Row} className="mb-3">
              
               <Button type="edit">Edit</Button>
              
              </Form.Group>
              </Form>
              </div> */}