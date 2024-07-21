import CKEditor from '@ckeditor/ckeditor5-vue';
import '~/assets/style/ckEditor.css'
export  default defineNuxtPlugin(nuxtApp=>{
nuxtApp.vueApp.use(CKEditor)
})
