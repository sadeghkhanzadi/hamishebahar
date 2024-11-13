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
                useNuxtApp().$toast.success('درخواست شما با موفقیت ثبت شد.')
            }
            if(status.value === 'fail' || error.value){
                console.log(error.value)
                this.setShowModal (false)
                useNuxtApp().$toast.warning('دروخواست شما با خطا مواجه شد لطفا مجدد امتحان کنید .')
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
                useNuxtApp().$toast.warning('دروخواست شما با خطا مواجه شد لطفا مجدد امتحان کنید .')
            }
            if (status.value === 'success' && !error.value) {
                await this.fetchingPlans()
                this.showAlert = false
                useNuxtApp().$toast.success('درخواست شما با موفقیت ثبت شد.')
            }

        },
        async editPlansItem(obj){
            const {data , status , error} = await useAsyncData( 'EditPlans' ,()=>$fetch(`${this.apiBaseUrl}/api/v1/plans/${obj.id}`,{
                method:'PUT',
                headers:{
                    Authorization:`${this.cookie}`,
                },
                body:JSON.stringify(obj)
            }))
            if (status.value ==='success' && !error.value){
                this.setShowModal (false)
                await this.fetchingPlans()
                useNuxtApp().$toast.success('درخواست شما با موفقیت ثبت شد.')
            }
            if(status.value === 'fail' || error.value){
                console.log(error.value)
                this.setShowModal (false)
                useNuxtApp().$toast.warning('دروخواست شما با خطا مواجه شد لطفا مجدد امتحان کنید .')

            }
        },
        setShowModal(op){
            this.showModal = op
        }

    }
})
