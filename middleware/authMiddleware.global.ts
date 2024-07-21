// import {useToast} from "vue-toastification";

export default defineNuxtRouteMiddleware((to, from) => {
    // const toast = useToast()
    const cookie = useCookie('jwt')
if (to.path.startsWith('/dashboard') && cookie.value){
        return
}
if (!cookie.value && to.path.startsWith('/dashboard')){
    if (process.client){
    // toast.error('برای وارد شدن به پنل کاربری ابتدا باید وارد شوید.')
    }
    return   navigateTo({path:'/'})
}
})
