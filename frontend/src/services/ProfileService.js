import axios from "axios";

const DATA_QUERY_API = 'http://localhost:9000/usercontact/getUserContact?'
const STAT_UPDATE_API = 'http://localhost:9000/usercontact/updateuser?'
const AVAR_UPDATE_API = 'http://localhost:9000/usercontact/uploadavatar?'

const transport = axios.create({
    withCredentials: true
})

class ProfileService{
    fetchData(id)
    {
        return transport.get(DATA_QUERY_API+'userId='+id)
    }

    update(user)
    {
        return transport.get(
            STAT_UPDATE_API
            +'user='+user
        )
    }

    
}

export default new ProfileService()