import {defineStore} from "pinia";
export const useHomeStore = defineStore('useHomeStore', {
    state() {
        return {
            apiBaseUrl: useRuntimeConfig().public.apiBaseUrl,
            faq: ""
        }
    },
    actions:{
        async fetchingFAQ(){
            const {data , error , status} = await useAsyncData('faqFetching' , ()=>$fetch(`${this.apiBaseUrl}/api/v1/view/question `))
            if (status.value === 'success'){
                this.faq=data.value.result
                console.log(this.faq)
            }
            if(status.value === 'fail'|| error.value ){
                console.log(error.value)
            }
        }
    }
})