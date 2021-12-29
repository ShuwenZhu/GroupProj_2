import axios from 'axios';
import {addUserS, removeUserS, store} from "../redux/store";

const API_BASE_URL = 'http://localhost:9999/login?redirect=http://localhost:3000';
const GET_USER_URL = 'http://localhost:9000/timesheet/whoami'
const LOG_OUT_URL = 'http://localhost:9999/logout?redirect=http://localhost:3000';

const transport = axios.create({
    
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
            //.catch(err => { window.location.href = API_BASE_URL })
    }

}
export default new UserService();