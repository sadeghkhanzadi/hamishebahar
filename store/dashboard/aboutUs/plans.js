import {defineStore} from "pinia";

export const useDashboardAboutUsPlansStore = defineStore('useDashboardAboutUsPlansStore', {
    state() {
        return {
            apiBaseUrl: useRuntimeConfig().public.apiBaseUrl,
            cookie: useCookie('jwt'),
            plans: "",
            showAlert:false,
            showModal:false,
            removeId:"",
            disabled:true
        }
    },
    getters: {
        getPlans(state) {
            return state.plans;
        },
        getDisabled(state) {
            return state.disabled;
        },
        getShowModal(state) {
            return state.showModal;
        },

    },
    actions:{
        async fetchingPlans(){
            const {data , status , error} = await useAsyncData( 'fetchingPlans' ,()=>$fetch(`${this.apiBaseUrl}/api/v1/view/plans`))
            if (status.value ==='success'){
                this.plans = data.value.result
                console.log(this.plans)
            }
            if(status.value === 'fail' || error.value){
                console.log(error.value)
            }
        },
        async insertPlans(obj){
            const {data , status , error} = await useAsyncData( 'insertPlans' ,()=>$fetch(`${this.apiBaseUrl}/api/v1/insert/plans`,{
                method:'POST',
                headers:{
                    Authorization:`${this.cookie}`,
                },
                body:JSON.stringify(obj)
            }))
            if (status.value ==='success'){
               await this.fetchingPlans()
                this.setShowModal (false)

            }
            if(status.value === 'fail' || error.value){
                console.log(error.value)
                this.setShowModal (false)
            }
        },
        async removePlansItem(id) {
            const {error, status} = await useAsyncData('removePlan',() => $fetch(`${this.apiBaseUrl}/api/v1/plans/${this.removeId}`, {
                method: 'DELETE',
                headers: {
                    Authorization: `${this.cookie}`
                }
            }))
            if (error.value) {
                console.log(error.value)
                this.showAlert = false
            }
            if (status.value === 'success') {
                await this.fetchingPlans()
                this.showAlert = false
            }

        },
        setShowModal(op){
            this.showModal = op
        }

    }
})
