import {defineStore} from "pinia";

export const useDashboardCoursesStore = defineStore('useDashboardCoursesStore', {
    state() {
        return {
            apiBaseUrl: useRuntimeConfig().public.apiBaseUrl,
            cookie :useCookie('jwt'),
            courses: "",
            courseItem: "",
            showRemoveModal:false
        }
    },
    getters: {
        getCourses(state) {
            return state.courses;
        },
        getCourseItem(state) {
            return state.courseItem;
        },
    },
    actions: {
        async sendData(body){
            const {data, error , status} = await useAsyncData('sendData' , ()=>$fetch(`${this.apiBaseUrl}/api/v1/insert/course` , {
                method: 'POST',
                headers:{
                    Authorization: ` ${this.cookie}`
                },
                body:JSON.stringify(
                    {
                        courseCode :body.courseCode,
                        title :body.title,
                        text :body.text,
                        startTime :body.startTime,
                        endTime :body.endTime,
                        coste :body.coste,
                        is_active : body.is_active,
                        teacher :body.teacher,
                        category :body.category,
                        medias : body.medias
                    }
                )
            }))
            if (status.value === 'success'){
                console.log(data.value)
                this.toast.success('آیتم با موفقیت ثبت شد')
            }
            if (error.value){
                console.log(error.value)
            }
        },
        async fetchingCourses() {
            const {
                data,
                error,
                status
            } = await useAsyncData('sendData', () => $fetch(`${this.apiBaseUrl}/api/v1/view/course/` ))
            if (status.value === 'success') {
                this.courses = data.value.result.content
            }
            if (error.value) {
                console.log(error.value)
            }
        },
        async fetchingCourseItem(courseCode="") {
            const {
                data,
                error,
                status
            } = await useAsyncData('sendData', () => $fetch(`${this.apiBaseUrl}/api/v1/view/course/${courseCode}`, ))
            if (status.value === 'success') {
                this.courseItem = data.value.result
            }
            if (error.value) {
                console.log(error.value)
            }
        },
        async deleteItem(removeId){
            const {data, error , status} = await useAsyncData('deleteData' , ()=>$fetch(`${this.apiBaseUrl}/api/v1/course/${removeId}`,{
                method:"DELETE",
                headers:{
                    Authorization: ` ${this.cookie}`
                }
            } ))
            if (status.value === 'success'){
                this.showRemoveModal = false
                await this.fetchingCourses()
                // toast.success('آیتم با موفقیت پاک شد')

            }
            if (error.value){
                console.log(error.value)
            }
        },
        async editData(body){
            const {data, error , status} = await useAsyncData('editData' , ()=>$fetch(`${apiBaseUrl}/api/v1/course/${body.id}` , {
                method: 'put',
                headers:{
                    Authorization: ` ${this.cookie}`
                },
                body:JSON.stringify(body)
            }))
            if (status.value === 'success'){
                await this.fetchingCourses()
                this.toast.success('آیتم با موفقیت ویرایش شد')
                navigateTo('/dashboard/courses')
            }
            if (error.value){
                console.log(error.value)
            }
        }
    }
})
