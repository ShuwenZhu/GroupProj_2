import axios from "axios";

const DATA_QUERY_API = 'http://localhost:9000/usercontact/getUserContact?'
const STAT_UPDATE_API = 'http://localhost:9000/usercontact/updateuser?'
const AVAR_UPDATE_API = 'http://localhost:9000/usercontact/uploadavatar?'
const USER_CONTACT_UPDATE_API = 'http://localhost:9000/usercontact/update'

const transport = axios.create({
    withCredentials: true,

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

    async updateSetDefault(user, start, end){
        const config = {
            withCredentials: true,
            method: 'POST',
            url: USER_CONTACT_UPDATE_API + 'Default?userId='+ user + 
                '&s1=' + start[0] + 
                '&s2=' + start[1] + 
                '&s3=' + start[2] + 
                '&s4=' + start[3] + 
                '&s5=' + start[4] + 
                '&e1=' + end[0] + 
                '&e2=' + end[1] + 
                '&e3=' + end[2] + 
                '&e4=' + end[3] + 
                '&e5=' + end[4],
        }
        axios.request(config).then(res=>{});
    }
}

export default new ProfileService()