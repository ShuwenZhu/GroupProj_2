import axios from 'axios';

const GET_COMPOSITE_URL = 'http://localhost:9000/composite-service/getWeekEndListWithDayInfo?userId='
const UPDATE_TIMESHEET_URL = 'http://localhost:9000/timesheet/update'
const UPDATE_STATUS_URL = 'http://localhost:9000/timesheet/updateStats'


const transport = axios.create({
    withCredentials: true
})

class TimesheetService {
    fetchTimesheet(userId){
        return transport
            .get(GET_COMPOSITE_URL + userId)
            .then(res => {
                console.log(res.data);
                return res.data;
            })
    }

    updateStatus(userId, weDate, sbStats, approvedFile){
        return transport
            .get(UPDATE_STATUS_URL + '?userId=' + userId + '&weDate=' + weDate + '&sbStats=' + sbStats + '&approvedFile=' + approvedFile)
            .then(res => {
                console.log(res.data);
                return res.data;
            })
    }

    sendTimesheet(timesheet){
        // return transport
        //     .post(UPDATE_TIMESHEET_URL)
        //     .then(res => {
        //         console.log(res.data);
        //         return res.data;
        //     })
        const headers = { 
            // 'Access-Control-Request-Method': 'POST',
            // 'Access-Control-Request-Headers': 'Content-Type',
            // 'Access-Control-Request-Headers': 'Authorization',
            'Access-Control-Allow-Origin': 'http://localhost:3000',
            'Access-Control-Allow-Credentials': 'true'
            
        };

        axios.post(UPDATE_TIMESHEET_URL, timesheet, {headers})
            .then(response => {
                console.log(response);
                return response;
            })
    }


}
export default new TimesheetService();