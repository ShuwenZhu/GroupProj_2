// import { Form, Button } from 'react-bootstrap';
// import React, { Component } from 'react'
// import NavBar from '../NavBar/NavBar';
// import { View, ScrollView, Text, StyleSheet } from 'react-native';
// import { Avatar } from 'react-native-elements';


//     class Contact extends React.Component {
//         constructor(props) {
//           super(props);
//           this.state = {value: ''};
      
//           this.handleChange = this.handleChange.bind(this);
//           this.handleSubmit = this.handleSubmit.bind(this);
//         }
      
//         handleChange(event) {
//           this.setState({value: event.target.value});
//         }
      
//         handleSubmit(event) {
//           alert('A name was submitted: ' + this.state.value);
//           event.preventDefault();
//         }
      
//         render() {
//           return (
//             <form onSubmit={this.handleSubmit}>
//               <label>
//                 Contact
//                 <input type="text" value={this.state.value} onChange={this.handleChange} />
//                 {/* <input type="text" value={this.state.value} onChange={this.handleChange} />
//                 <input type="text" value={this.state.value} onChange={this.handleChange} /> */}
//               </label>
// {/* 
//               <label>
//                 Contact
//                 <input type="text" value={this.state.value} onChange={this.handleChange} />
//                 <input type="text" value={this.state.value} onChange={this.handleChange} />
//               </label>
//               <input type="submit" value="Submit" /> */}
//             </form>
//           );
//         }
//       }

//       export default Contact;