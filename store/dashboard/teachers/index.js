import {defineStore} from "pinia";

export const useDashboardTeachersStore = defineStore('useDashboardTeachersStore', {
    state() {
        return {
            apiBaseUrl: useRuntimeConfig().public.apiBaseUrl,
            cookie: useCookie('jwt'),
            teachers: "",
            teacherItem: "",
            showAlert: false,
            pendingRequest: false,
            showNewTeachersForm: false,
            showEditTeachersForm: false
        }
    },
    getters: {
        getTeachers(state) {
            return state.teachers;
        },
    },
    actions: {
        async fetchingTeachers(jobTitle = null, firstName = null, lastName = null, size = null) {
            const {
                data,
                error,
                status
            } = await useAsyncData('fetchTeacher', () => $fetch(`${this.apiBaseUrl}/api/v1/view/teacher`, {
                query: {
                    firstName,
                    lastName,
                    jobTitle,
                    size
                }
            }))
            if (status.value === 'success') {
                this.teachers = data.value.result.content
            }
            if (error.value) {
                console.log(error.value)
            }
        },
        async removeTeacherItem(id) {
            const {error, status} = await useFetch(() => `${this.apiBaseUrl}/api/v1/teacher/${id}`, {
                method: 'DELETE',
                headers: {
                    Authorization: `${this.cookie}`
                }
            })
            if (error.value) {
                console.log(error.value)
            }
            if (status.value === 'success') {
                this.showAlert = false
                await this.fetchingTeachers()
            }
        },
        async postNewTeachers(information) {
            this.pendingRequest = true
            const imgId = []
            information.medias.forEach(item => {
                imgId.push({id: item.id})
            })
            const json = {
                firstName: information.name,
                lastName: information.lastName,
                age: information.age,
                jobTitle: information.jobTitle,
                is_active: information.is_active,
                documentFiles: imgId,
                teacherCode: information.personalCode,
                phoneNumber: information.phoneNumber,
                workExperience: information.workExperience,
                nationalCode: information.nationalCode
            }
            const {error, status} = await useFetch(() => `${this.apiBaseUrl}/api/v1/teacher`, {
                method: 'POST',
                body: JSON.stringify(json),
                headers: {
                    Authorization: `${this.cookie}`
                }
            })
            if (error.value) {
                console.log(error.value)
            }
            if (status.value === 'success') {
                this.showNewTeachersForm = false
                await this.fetchingTeachers()
            }
            this.pendingRequest = false
        },
        async editeTeachers(information ,editTeacherId) {
            this.pendingRequest= true
            const imgId = []
            information.medias.forEach(item => {
                imgId.push({id: item.id})
            })
            const json = {
                firstName: information.name,
                lastName: information.lastName,
                age: information.age,
                jobTitle: information.jobTitle,
                is_active: information.is_active,
                documentFiles:imgId,
                teacherCode:information.personalCode,
                phoneNumber:information.phoneNumber,
                workExperience : information.workExperience,
                nationalCode: information.nationalCode
            }
                json.id = editTeacherId
                const { error, status} = await useFetch(()=>`${this.apiBaseUrl}/api/v1/teacher/${editTeacherId}`, {
                    method: 'PUT',
                    body:JSON.stringify(json),
                    headers:{
                        Authorization:`${this.cookie}`
                    }
                })
                if (error.value){
                    console.log(error.value)
                }
                if (status.value === 'success'){
                    this.pendingRequest = false
                    this.showEditTeachersForm = false
                    await this.fetchingTeachers()
                }
        }

    }
})
