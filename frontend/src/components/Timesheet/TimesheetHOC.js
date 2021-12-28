import {useSelector} from "react-redux";
import React, { Component } from 'react';

// to access useSelector hook in my class-based component
export const TimesheetHOC = (WrappedComponent) => {
    return function Component (props) {

        const userlist = useSelector((state)=>state.user);
        
        return <WrappedComponent userlist={userlist} {...props} />
            
    }

} 