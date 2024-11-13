import {defineStore} from "pinia";
export const useNewsStore = defineStore('useNewsStore', {
    state() {
        return {
            apiBaseUrl: useRuntimeConfig().public.apiBaseUrl,
            news: ""
        }
    },
    getters:{
        getNews(state) {
            return state.news;
        }
    },
    actions:{
        async fetchingNews(){
            const {data , error , status} = await useAsyncData('newsFetching' , ()=>$fetch(`/api/news`))
            if (status.value === 'success'){
                this.news=data.value.result
                console.log(this.news)
            }
            if(status.value === 'fail'|| error.value ){
                console.log(error.value)
            }

        }
    }
})
