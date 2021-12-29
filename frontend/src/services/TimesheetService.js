import axios from 'axios';

const API_BASE_URL = 'http://localhost:9999/login?redirect=http://localhost:3000';
const GET_USER_URL = 'http://localhost:9000/timesheet/whoami'
const GET_COMPOSITE_URL = 'http://localhost:9000/composite-service/getWeekEndListWithDayInfo?userId=1'

const transport = axios.create({
    withCredentials: true
})

class TimesheetService {
    fetchTimesheet(){
        return transport
            .get(GET_COMPOSITE_URL)
            .then(res => {
                console.log(res.data);
                return res.data;
            })
    }


}
export default new TimesheetService();