import axios from 'axios';

const API_BASE_URL = 'http://localhost:9000/';
const LOG_OUT_URL = 'http://localhost:9999';
const config = {
    method: 'GET', // *GET, POST, PUT, DELETE, etc.
    mode: 'cors', // no-cors, *cors, same-origin
    // cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
    credentials: 'include', // *include, same-origin, omit,
    // headers: {
    //     'Content-Type': 'application/json'
    //     // 'Content-Type': 'application/x-www-form-urlencoded',
    // },
    redirect: 'follow', // manual, *follow, error
    // referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
    // body: JSON.stringify(data) // body data type must match "Content-Type" header
};
class UserService {
    logout(){
        return axios.get(LOG_OUT_URL,
            config).then(
                response=>{
                    console.log(response);
                    window.location.href = "http://localhost:9999/login?redirect=" + window.location
                }
        )
    }
    fetchUser(){
        // return fetch(API_BASE_URL + 'posts').then((response) => response.json());
        return axios.get(API_BASE_URL + 'timesheet/getAllRecord',
            config).then(response=>
        {
            if (response && response.request.responseURL)
            {
                console.log(response)
            }
        }).catch((error)=>{
            console.log('error' + error);
            // window.location.href = "http://localhost:9999/login?redirect=" + window.location
        });
    }

}
export default new UserService();