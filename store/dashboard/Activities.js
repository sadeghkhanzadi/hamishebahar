import {defineStore} from "pinia";

export const useDashboardActivitiesStore = defineStore('useDashboardActivitiesStore', {
    state() {
        return {
            apiBaseUrl: useRuntimeConfig().public.apiBaseUrl,
            cookie: useCookie('jwt'),
            activities: "",
            showNewActivityModal:false,
            showAlert:false,
            removeId:"",
            disabled:true
        }
    },
    getters: {
        getActivities(state) {
            return state.activities;
        },
        getDisabled(state) {
            return state.disabled;
        },
        getShowModal(state) {
            return state.showNewActivityModal;
        },

    },
    actions:{

        async fetchingActivities(){
            console.log('fetching')
            const {data ,status, error} = await useAsyncData('getActivity', () => $fetch(`${this.apiBaseUrl}/api/v1/view/activity`))
            if (status.value === 'success') {
                this.activities =  data.value.result
            }
            if (error.value) {
                console.log(error.value)
                console.log('fetch failed')

            }
        },
        async insertActivities(obj){
            const {status, error} = await useAsyncData('postActivity', () => $fetch(`${this.apiBaseUrl}/api/v1/insert/activity`, {
                method: 'POST',
                headers: {authorization: `${this.cookie}`},
                body: JSON.stringify(obj)
            }))
            if (status.value === 'success') {
                // toast.success('درخواست شما با موفقیت ثبت شد.')
                this.showNewActivityModal = false
               await this.fetchingActivities()
            }
            if (error.value) {
                console.log(error.value)
            }
        },
        async removeActivities(result ){
            if (result) {
                const {status, error} = await useAsyncData('removeActivity', () => $fetch(`${this.apiBaseUrl}/api/v1/activity/${this.removeId}`, {
                    method: 'DELETE',
                    headers: {authorization: `${this.cookie}`},
                }))
                if (status.value === 'success') {
                    // toast.success('آیتم مورد نظر با موفقیت حذف شد .')
                   await this.fetchingActivities()
                }
                if (error.value) {
                    // toast.success('دروخواست شما با خطا مواجه شد لطفا مجدد امتحان کنید .')
                    console.log(error.value)
                }
            }
           this.showAlert = false
        },
        async editeActivities(result ){
            console.log(result)
            if (result) {
                const {status, error} = await useAsyncData('removeActivity', () => $fetch(`${this.apiBaseUrl}/api/v1/activity/${result.id}`, {
                    method: 'PUT',
                    headers: {authorization: `${this.cookie}`},
                    body: JSON.stringify(result)
                }))
                if (status.value === 'success') {
                    // toast.success('آیتم مورد نظر با موفقیت حذف شد .')
                   await this.fetchingActivities()
                    this.disabled = true
                }
                if (error.value) {
                    // toast.success('دروخواست شما با خطا مواجه شد لطفا مجدد امتحان کنید .')
                    console.log(error.value)
                }
            }
        }
    }
})
