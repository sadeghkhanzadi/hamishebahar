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
        getAboutUs(state) {
            return state.aboutUs;
        }
    },
    actions: {
        async fetchingAboutUs() {
            const {
                data,
                status,
                error
            } = await useAsyncData('fetchingAboutUs', () => $fetch(`${this.apiBaseUrl}/api/v1/view/aboutus`))
            if (status.value === 'success') {
                this.aboutUs = data.value.result
                console.log(this.aboutUs)
            }
            if (status.value === 'fail' || error.value) {
                console.log(error.value)
            }
        },
        async insertAboutUs(obj) {
            const {
                data,
                status,
                error
            } = await useAsyncData('insertAboutUs', () => $fetch(`${this.apiBaseUrl}/api/v1/insert/aboutus`, {
                method: "POST",
                body: JSON.stringify(obj),
                headers: {
                    Authorization: `${this.cookie}`,
                }
            }))
            if (status.value === 'success') {
                console.log(this.aboutUs)
                await this.fetchingAboutUs()
                useNuxtApp().$toast.success('درخواست شما با موفقیت ثبت شد.')
            }
            if (status.value === 'fail' || error.value) {
                console.log(error.value)
                useNuxtApp().$toast.warning('دروخواست شما با خطا مواجه شد لطفا مجدد امتحان کنید .')
            }
        },
        async EditAboutUs(obj) {
            const {
                data,
                status,
                error
            } = await useAsyncData('EditAboutUs', () => $fetch(`${this.apiBaseUrl}/api/v1/aboutus/${obj.id}`, {
                method: "PUT",
                body: JSON.stringify(obj),
                headers: {
                    Authorization: `${this.cookie}`,
                }
            }))
            if (status.value === 'success') {
                console.log(this.aboutUs)
                await this.fetchingAboutUs()
                useNuxtApp().$toast.success('درخواست شما با موفقیت ثبت شد.')

            }
            if (status.value === 'fail' || error.value) {
                console.log(error.value)
                useNuxtApp().$toast.warning('دروخواست شما با خطا مواجه شد لطفا مجدد امتحان کنید .')

            }
        },


    }
})
