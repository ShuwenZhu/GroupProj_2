import axios from "axios";

const DATA_QUERY_API = 'http://localhost:9000/timesheet/getAllR4ApproveRecord'
const STAT_UPDATE_API = 'http://localhost:9000/timesheet/changeStatSet?'

const transport = axios.create({
    withCredentials: true
})

class HRService{
    fetchData()
    {
        return transport.get(DATA_QUERY_API)
    }

    update(userId, date, status)
    {
        return transport.post(
            STAT_UPDATE_API
            +'userId='+userId
            +'&date='+date
            +'&status='+status
        )
    }
}

export default new HRService()