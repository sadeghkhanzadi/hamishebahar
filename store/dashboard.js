import {defineStore} from "pinia";
const cookie = useCookie('jwt')
const apiBaseUrl = useState('apiBaseUrl').value
export const useDashboardMedia = defineStore('useDashboardData',{
    state(){
        return{
            dashboard:[],
            media:[],
        }
    },
    getters:{
        getDashboardMedia(state){
            return state.dashboard
        },
        getMedia(state){
            return state.media
        }
    },
    actions:{
        async fetchDashboardData(queryStatus){
            const {data , status , error} = await useFetch(`${apiBaseUrl}/api/v1/media`,{
                headers:{
                    Authorization:`${cookie.value}`
                },
                query:{
                    status:queryStatus
                }
            })
            if (status.value === 'success'){
                console.log(data.value)
                this.dashboard = data.value.result.content
            }
            if (error.value){
                console.log(error.value)
            }
        },
        async fetchDashboardMediaData(){
            const {data , status , error} = await useFetch(`${apiBaseUrl}/api/v1/media`,{
                headers:{
                    Authorization:`${cookie.value}`
                },
            })
            if (status.value === 'success'){
                console.log(data.value)
                this.media = data.value.result.content
            }
            if (error.value){
                console.log(error.value)
            }
        }
    }

})
