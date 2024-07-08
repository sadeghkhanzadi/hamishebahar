import {defineStore} from "pinia";
const cookie = useCookie('jwt')
const {public:{baseUrl}} = useRuntimeConfig()
export const useDashboardMedia = defineStore('useDashboardData',{
    state(){
        return{
            dashboard:[]
        }
    },
    getters:{
        getDashboardMedia(state){
            return state.dashboard
        }
    },
    actions:{
        async fetchDashboardData(){
            const {data , status , error} = await useFetch(`${baseUrl}/api/v1/media`,{
                headers:{
                    Authorization:`${cookie.value}`
                }
            })
            if (status.value === 'success'){
                console.log(data.value)
                this.dashboard = data.value.result.content
            }
            if (error.value){
                console.log(error.value)
            }
        }
    }

})
