import {defineStore} from "pinia";

export const useDashboardAccessLevelStore = defineStore('useDashboardAccessLevelStore', {
    state() {
        return {
            apiBaseUrl: useRuntimeConfig().public.apiBaseUrl,
            cookie: useCookie('jwt'),
            accessLevel: "",
        }
    },
    getters: {
        getAccessLevel(state) {
            return state.accessLevel;
        },

    },
    actions:{
        async fetchingAccessLevel(){
            const {data ,status, error} = await useAsyncData('getUsers', () => $fetch(`${this.apiBaseUrl}/api/v1/register/users`))
            if (status.value === 'success') {
                this.accessLevel =  data.value.result
                console.log(this.accessLevel)
            }
            if (error.value) {
                console.log(error.value)
                console.log('fetch failed')

            }
        },
        async register(obj){
            const {status, error} = await useAsyncData('accessLevelRegister', () => $fetch(`${this.apiBaseUrl}/api/v1/register/users`, {
                method: 'POST',
                headers: {authorization: `${this.cookie}`},
                body: JSON.stringify(obj)
            }))
            if (status.value === 'success') {
                useNuxtApp().$toast.success('درخواست شما با موفقیت ثبت شد.')
                this.showNewActivityModal = false
                await this.fetchingAccessLevel()
            }
            if (error.value) {
                console.log(error.value)
                useNuxtApp().$toast.warning('دروخواست شما با خطا مواجه شد لطفا مجدد امتحان کنید .')
            }
        },
        async removeActivities(result ){
            if (result) {
                const {status, error} = await useAsyncData('removeActivity', () => $fetch(`${this.apiBaseUrl}/api/v1/activity/${this.removeId}`, {
                    method: 'DELETE',
                    headers: {authorization: `${this.cookie}`},
                }))
                if (status.value === 'success') {
                    useNuxtApp().$toast.success('آیتم مورد نظر با موفقیت حذف شد .')
                    await this.fetchingAccessLevel()
                }
                if (error.value) {
                    useNuxtApp().$toast.warning('دروخواست شما با خطا مواجه شد لطفا مجدد امتحان کنید .')
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
                    useNuxtApp().$toast.success('تغییرات با موفقیت اعمال شد .')
                    await this.fetchingAccessLevel()
                    this.disabled = true
                }
                if (error.value) {
                    useNuxtApp().$toast.warning('دروخواست شما با خطا مواجه شد لطفا مجدد امتحان کنید .')
                    console.log(error.value)
                }
            }
        }
    }
})
