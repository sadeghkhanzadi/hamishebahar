<script setup lang="ts">
const emit = defineEmits(['close' , 'sendData'])
const params = reactive({
  title : "" ,
  text : ""
})
const disabled = ref(false)
const error = reactive({
  input:false,
  textArea:false,
})
const sendData = ()=>{
  if(params.title.length && params.text.length){
    disabled.value =true
    emit('sendData' , params)
  }
  else {
    error.input = true
    error.textArea = true
  }
}
</script>

<template>
  <div class="backdrop fixed top-0 left-0 z-50 w-full h-full bg-[rgba(0,0,0,0.3)] px-2 flex items-center md:items-stretch" @click.self="emit('close')">
    <div class="modal-content w-full sm:w-1/2 lg:w-1/3 mx-auto  md:mt-[15vh] overflow-hidden" >
      <div class="bg-white p-3 shadow-lg rounded">
        <form action="" @submit.prevent="sendData">
          <div class="input-group flex flex-col justify-center gap-2">
            <label for="question" class="text-gray-500">   سوال :  </label>
            <input v-model="params.title" class="border p-2 border-gray-100 outline-0 rounded" type="text" placeholder="سوال" id="question">
            <span v-if="error.input" class="text-xs text-red-500">برای ارسال فرم بودن سوال الزامی میباشد</span>
          </div>
          <div class="input-group mt-4 flex flex-col justify-center gap-2">
            <label for="answer" class="text-gray-500">پاسخ :</label>
            <textarea v-model="params.text" placeholder="پاسخ" class="w-full border p-2  border-gray-100 outline-0 rounded resize-y" name="" id="answer" cols="30" rows="5"></textarea>
            <span v-if="error.textArea" class="text-xs text-red-500">برای ارسال فرم بودن پاسخ الزامی میباشد</span>
          </div>
          <div class="mt-4">
            <button class="bg-blue-500 rounded text-white p-2 w-full" :disabled="disabled" >ثبت</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>
