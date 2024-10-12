<template>
  <ckeditor :editor="editor" :data="content" @change="onChange" />
</template>

<script>
import { ref } from 'vue';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
// import {
//   // ... سایر پلاگین‌ها
// } from '@ckeditor/ckeditor5-build-classic';

export default {
  setup() {
    const content = ref('');
    const editor = ref(null);

    const onChange = (event, editor) => {
      content.value = editor.getData();
    };

    const createEditor = async () => {
      editor.value = await ClassicEditor.create(document.querySelector('.editor'), {
        plugins: [
          // ... آرایه‌ای از پلاگین‌های مورد نظر
        ],
        toolbar: [
          'heading', '|',
          'bold', 'italic', 'underline', 'strikethrough', '|',
          'link', 'image', 'table', 'code', '|',
          'bulletedList', 'numberedList', '|',
          'indent', 'blockQuote', '|',
          'fontfamily', 'fontsize', 'fontcolor', '|',
          'alignment', '|',
          'undo', 'redo'
        ]
      });
    };

    createEditor();

    return {
      content,
      editor,
      onChange
    };
  }
};
</script>
