import {useSelector} from "react-redux";
import React, { Component } from 'react';
import { useSearchParams } from "react-router-dom";

// to access useSelector hook in my class-based component
export const TimesheetHOC = (WrappedComponent) => {
    return function Component (props) {

        const userlist = useSelector((state)=>state.user);
        const [searchParams, setSearchParams] = useSearchParams();
        
        return <WrappedComponent user={userlist[0]} queryParam={searchParams.get("weekEnding")} {...props} />
            
    }

} 