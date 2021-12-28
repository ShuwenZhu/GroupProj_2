import axios from 'axios';

const API_BASE_URL = 'http://localhost:9000/';
class ApiService {
    fetchTimesheet(){
        // return fetch(API_BASE_URL + 'posts').then((response) => response.json());
        return axios.get(API_BASE_URL + 'timesheet-service/getTimesheet/' + numberOfSummary);
    }

}
export default new ApiService();