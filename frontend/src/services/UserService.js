import axios from 'axios';
import Cookies from 'js-cookie';
import {addUserS, removeUserS, store} from "../redux/store";
import data from "bootstrap/js/src/dom/data";

const API_BASE_URL = 'http://localhost:9999/login?redirect=http://localhost:3000';
const GET_USER_URL = 'http://localhost:9000/timesheet/whoami'
const LOG_OUT_URL = 'http://localhost:9999/logout?redirect=http://localhost:3000';
const config = {
    // method: 'GET', // *GET, POST, PUT, DELETE, etc.
    mode: 'cors', // no-cors, *cors, same-origin
    // cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
    credentials: 'include', // *include, same-origin, omit,
    headers: {
        'Content-Type': 'application/json'
        // 'Content-Type': 'application/x-www-form-urlencoded',
    },
    redirect: 'follow', // manual, *follow, error
    // referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
    body: JSON.stringify(data) // body data type must match "Content-Type" header
};
const transport = axios.create({
    withCredentials: true
})

class UserService {

    logout(){
        console.log('logging out')
        store.dispatch(removeUserS())
        return axios.get(LOG_OUT_URL)
            .then(
                response=>{
                    console.log(response);
                    // window.location.href = "http://localhost:9999/logout"
                }
        )
    }
    fetchUser(){
        return transport
            .get(GET_USER_URL)
            .then(res => {
                // console.log(res.data)
                store.dispatch(addUserS(res.data))
            })
            .catch(err => { window.location.href = API_BASE_URL })
    }

}
export default new UserService();