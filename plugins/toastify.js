import Vue3Toastify, { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';
export default defineNuxtPlugin((nuxtApp) => {
    nuxtApp.vueApp.use (Vue3Toastify , {
        autoClose: 5000,
        transition: toast.TRANSITIONS.FLIP,
        closeOnClick: true,
        pauseOnFocusLoss: true,
        pauseOnHover: true,
        draggable: true,
        position: "top-right",
        draggablePercent: 0.6,
        showCloseButtonOnHover: true,
        hideProgressBar: false,
        closeButton: false,
        rtl:true,
        icon:false
    })

    return {
        provide: {
            toast,
        },
    };
});
