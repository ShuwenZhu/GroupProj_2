import {useSelector} from "react-redux";
import React from "react";

function User() { // Rule 2: call hooks in function component
    const userlist = useSelector((state)=>state.user)  // Rule 1: call hooks in top-level

    return <>{typeof userlist[0] !== 'undefined' &&
        <p>Hello {userlist[0].username}</p>}</>
}

export default User