import axios from 'axios';

const API_BASE_URL = 'http://localhost:9000/timesheet/';

const transport = axios.create({
    withCredentials: true
})
class ApiService {
    fetchTimesheet(userId){
        // console.log(userId);
        return transport
        .get(API_BASE_URL + 'getUserWEByUserId?userId=' + userId)
        .catch(err => { window.location.href = API_BASE_URL })
    }

}
export default new ApiService();