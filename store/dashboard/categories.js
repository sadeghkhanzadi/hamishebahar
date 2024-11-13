import {defineStore} from "pinia";

export const useDashboardCategoriesStore = defineStore('useDashboardCategoriesStore', {
    state() {
        return {
            apiBaseUrl: useRuntimeConfig().public.apiBaseUrl,
            cookie: useCookie('jwt'),
            categories: "",
            showModal: false,
            showAlertModal: false
        }
    },
    getters: {
        getCategories(state) {
            return state.categories;
        },
    },
    actions: {
        async fetchingCategories(size) {
            const {
                data,
                status,
                refresh
            } = await useAsyncData('fetchCategories', () => $fetch(`${this.apiBaseUrl}/api/v1/view/category`, {
                headers: {
                    Authorization: `${this.cookie}`
                },
                query:{
                    size:size
                }
            }))
            if (status.value === 'success') {
                this.categories = data.value.result.content
            }
        },
        async insertCategories(body) {
            const {
                data,
                status,
                error
            } = await useAsyncData('insertCategories', () => $fetch(`${this.apiBaseUrl}/api/v1/insert/category`, {
                method: `POST`,
                headers: {
                    Authorization: `${this.cookie}`
                },
                body: JSON.stringify(body)
            }))
            if (status.value === 'success') {
                console.log(data.value)
                await refreshNuxtData()
                await this.fetchingCategories()
                useNuxtApp().$toast.success('آیتم با موفقیت اضافه شد')
            }
            if (error.value) {
                useNuxtApp().$toast.error('خطای سیستم مجددا تلاش کنید')
            }
            this.showModal = false
        },
        async removeItem(id) {
            const {
                data,
                status,
                error
            } = await useAsyncData('deleteCategories', () => $fetch(`${this.apiBaseUrl}/api/v1/category/${id}`, {
                method: `DELETE`,
                headers: {
                    Authorization: `${this.cookie}`
                },
                body: JSON.stringify({id: id})
            }))
            if (status.value === 'success') {
                    await this.fetchingCategories()
                if (process.client) {
                    useNuxtApp().$toast.success('آیتم با موفقیت پاک شد')
                }
                await this.fetchingCategories()
            }
            if (error.value) {
                useNuxtApp().$toast.error('خطای سیستم مجددا تلاش کنید')
            }
            this.showAlertModal = false
        },
        async editCategory(body) {
            const {
                data,
                status
            } = await useAsyncData('editCategories', () => $fetch(`${url}/api/v1/category/${body.id}`, {
                method: `PUT`,
                headers: {
                    Authorization: `${this.cookie}`
                },
                body: JSON.stringify(body)
            }))
            if (status.value === 'success') {
                useNuxtApp().$toast.success('آیتم با موفقیت ویرایش شد')
                await this.fetchingCategories()
                await refreshNuxtData()
            } else {
                useNuxtApp().$toast.error('خطای سیستم مجددا تلاش کنید')
            }
        }

    }
})
