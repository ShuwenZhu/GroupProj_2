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
        transport
            .get(GET_USER_URL)
            .then(res => {
                console.log(res.data)
                store.dispatch(addUserS(res.data))
            })
            .catch(err => { window.location.href = API_BASE_URL })

        // return axios.get(API_BASE_URL)
        //     .then(response=>
        // {
        //     if (response.data.length < 300)
        //     {
        //         console.log(response)
        //         // console.log(response.headers)
        //         // let user = JSON.parse(sessionStorage.user);
        //         // if (sessionStorage.length == 0)
        //             window.location.href = response.request.responseURL
        //         // else
        //         //     console.log(sessionStorage)
        //         // const token = user.data.id;
        //         // console.log(token)
        //         // store.dispatch(addUserS({userId: 0, token: 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJpZFwiOjEsXCJ1c2VybmFtZVwiOlwiYWRtaW5cIixcImVtYWlsXCI6XCJhZG1pbkBiZWFjb25maXJlLmNvbVwifSIsImlhdCI6MTY0MDM3MDY0NH0.0tyasFFniAYJGh5VpNxr2m9U3J6_GVR8n_EtZ4WEce8'}))
        //
        //     } else
        //     {
        //         console.log(response)
        //     }
        // }).catch((error)=>{
        //     console.log('error' + error);
        //     // window.location.href = "http://localhost:9999/login?redirect=" + window.location
        // });
    }

}
export default new UserService();