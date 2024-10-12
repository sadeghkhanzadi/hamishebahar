import {defineStore} from "pinia";

export const useDashboardAboutUsStore = defineStore('useDashboardAboutUsStore', {
    state() {
        return {
            apiBaseUrl: useRuntimeConfig().public.apiBaseUrl,
            cookie: useCookie('jwt'),
            aboutUs: "",

        }
    },
    getters: {
        getPlans(state) {
            return state.plans;
        }
    },
    actions:{
        async fetchingAboutUs(){
            const {data , status , error} = await useAsyncData( 'fetchingAboutUs' ,()=>$fetch(`${this.apiBaseUrl}/api/v1/view/aboutus`))
            if (status.value ==='success'){
                this.plans = data.value.result
                console.log(this.plans)
            }
            if(status.value === 'fail' || error.value){
                console.log(error.value)
            }
        },
        async EditAboutUs(obj){
            const {data , status , error} = await useAsyncData( 'EditAboutUs' ,()=>$fetch(`${this.apiBaseUrl}/api/v1/aboutus/${obj.id}`))
            if (status.value ==='success'){
                console.log(this.plans)
             await this.fetchingAboutUs()
            }
            if(status.value === 'fail' || error.value){
                console.log(error.value)
            }
        },


    }
})
