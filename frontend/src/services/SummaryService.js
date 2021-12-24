import axios from 'axios';

const API_BASE_URL = 'http://localhost:9000/';
class ApiService {
    fetchSummary(numberOfSummary){
        // return fetch(API_BASE_URL + 'posts').then((response) => response.json());
        return axios.get(API_BASE_URL + 'summary-service/getSummary/' + numberOfSummary);
    }

}
export default new ApiService();