import {defineStore} from "pinia";

export const useDashboardIconsStore = defineStore('useDashboardIconsStore', {
    state() {
        return {
            apiBaseUrl: useRuntimeConfig().public.apiBaseUrl,
            cookie: useCookie('jwt'),
            icons: "",
            showModal:false,
            showEditModal:false
        }
    },
    getters: {
        getIcons(state) {
            return state.icons;
        },
    },
    actions:{
        async insertIcon(obj) {
            const {status, error} =await useAsyncData('postingIcon', () => $fetch(`${this.apiBaseUrl}/api/v1/insert/icon`, {
                method: 'POST',
                headers: {authorization: `${this.cookie}`},
                body: JSON.stringify(obj)
            }))
            if (status.value === 'success') {
                console.log(status.value)
                this.showModal = false
                await this.fetchingIcons()
            }
            if (error.value) {
                console.log(error.value)
            }
        },
        async fetchingIcons() {
            const {data, status, error} =await useAsyncData('fetchIcon', () => $fetch(`${this.apiBaseUrl}/api/v1/icon`, {
                headers: {authorization: `${this.cookie}`},
            }))
            if (status.value === 'success') {
                this.icons = data.value.result.content
                this.showModal = false
            }
            if (error.value) {
                console.log(error.value)
            }
        },
        async  editIcon(obj) {
            await useAsyncData('postingIcon', () => $fetch(`${this.apiBaseUrl}/api/v1/icon/${obj.id}`, {
                    method: 'PUT',
                    headers: {authorization: `${this.cookie}`},
                    body: JSON.stringify(obj),
                    onResponse({response}) {
                        if (response.status === 200) {
                            this.showEditModal = false
                           this.fetchingIcons()
                        } else {
                            console.log(response)
                        }
                    }
                }
            ))
        }

    }
})
