import Toast from "vue-toastification";
import "vue-toastification/dist/index.css";
export default defineNuxtPlugin(nuxtApp=>{
    nuxtApp.vueApp.use(Toast , {
        position: "top-center",
        timeout: 5000,
        closeOnClick: true,
        pauseOnFocusLoss: true,
        pauseOnHover: true,
        draggable: true,
        draggablePercent: 0.6,
        showCloseButtonOnHover: false,
        hideProgressBar: true,
        closeButton: false,
        icon: false,
        rtl: true,
        transition: "Vue-Toastification__bounce",
        maxToasts: 5,
        newestOnTop: true
    })
})






