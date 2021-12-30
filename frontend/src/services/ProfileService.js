import axios from "axios";

const DATA_QUERY_API = 'http://localhost:9000/usercontact/getUserContact?'
const STAT_UPDATE_API = 'http://localhost:9000/usercontact/updateDayUsage?'
const AVAR_UPDATE_API = 'http://localhost:9000/usercontact/uploadavatar?'

const transport = axios.create({
    withCredentials: true
})

const transport2 = axios.create({
    baseURL: 'http://localhost:9000/usercontact',
    withCredentials: true,
    headers: {
        "Content-type": "application/json"
    }
})

class ProfileService{
    fetchData(id)
    {
        return transport.get(DATA_QUERY_API+'userId='+id)
    }

    updateDateUsage(userId, floatDay, vacationDay)
    {
        transport.get(STAT_UPDATE_API
            + 'userId=' + userId
            + '&floatday=' + floatDay
            + '&vacationday=' + vacationDay
        ).then(res=>{})
    }

    
}

export default new ProfileService()