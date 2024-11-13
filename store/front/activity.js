import {defineStore} from "pinia";
export const useActivityStore = defineStore('useActivityStore', {
    state() {
        return {
            apiBaseUrl: useRuntimeConfig().public.apiBaseUrl,
            activity: ""
        }
    },
    getters:{
        getActivity(state) {
            return state.activity;
        }
    },
    actions:{
        async fetchingActivity(){
            const {data , error , status} = await useAsyncData('activityFetching' , ()=>$fetch(`/api/activities`))
            if (status.value === 'success'){
                this.activity=data.value.result
                console.log(this.activity[0])
            }
            if(status.value === 'fail'|| error.value ){
                console.log(error.value)
            }
        }
    }
})
