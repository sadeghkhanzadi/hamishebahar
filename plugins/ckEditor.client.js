import { defineNuxtPlugin } from '#app';
import CKEditor from '@ckeditor/ckeditor5-vue';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';

export default defineNuxtPlugin((nuxtApp) => {
    nuxtApp.vueApp.use(CKEditor);
    nuxtApp.provide('ckeditor', ClassicEditor);
});
