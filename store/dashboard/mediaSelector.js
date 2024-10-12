import {defineStore} from "pinia";
export const useDashboardMedia = defineStore('useDashboardData',{
    state(){
        return{
            apiBaseUrl:useRuntimeConfig().public.apiBaseUrl,
            cookie:useCookie('jwt').value,
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
            const {data , status , error} = await useFetch(`${this.apiBaseUrl}/api/v1/media`,{
                headers:{
                    Authorization:`${this.cookie}`
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
            const {data , status , error} = await useFetch(`${this.apiBaseUrl}/api/v1/media`,{
                headers:{
                    Authorization:`${this.cookie}`
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
