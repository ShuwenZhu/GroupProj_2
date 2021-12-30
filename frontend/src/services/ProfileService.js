import axios from "axios";

const DATA_QUERY_API = 'http://localhost:9000/usercontact/getUserContact?'
const STAT_UPDATE_API = 'http://localhost:9000/usercontact/updatePersonal?'
const AVAR_UPDATE_API = 'http://localhost:9000/usercontact/uploadavatar?'

const transport = axios.create({
    withCredentials: true
})

class ProfileService{
    fetchData(id)
    {
        return transport.get(DATA_QUERY_API+'userId='+id)
    }

    updateData(data)
    {
        return transport.post(
            STAT_UPDATE_API
            +'data='+data
        )
    }
    
}

export default new ProfileService()