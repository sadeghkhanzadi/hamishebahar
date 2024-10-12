import Vue3PersianDatetimePicker from 'vue3-persian-datetime-picker'
export default defineNuxtPlugin(nuxtApp => {
    nuxtApp.vueApp.use(Vue3PersianDatetimePicker, {
        name: 'date-picker',
        props: {
            format: "YYYY-MM-DD",
            displayFormat:"jYYYY-jMM-jDD"
        }
    })
})
