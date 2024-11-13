import {defineStore} from "pinia";
export const useFaqStore = defineStore('useFaqStore', {
    state() {
        return {
            apiBaseUrl: useRuntimeConfig().public.apiBaseUrl,
            faq: ""
        }
    },
    getters:{
        getFaq(state) {
            return state.faq;
        }
    },
    actions:{
        async fetchingFAQ(){
            const {data , error , status} = await useAsyncData('faqFetching' , ()=>$fetch(`/api/faq`))
            if (status.value === 'success'){
                this.faq=data.value.result
                console.log(data)
            }
            if(status.value === 'fail'|| error.value ){
                console.log(error.value)
            }
        }
    }
})
