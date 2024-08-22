<script setup lang="ts">
const {index, data} = defineProps({
  data: Object,
  index: Number
})
const emit = defineEmits(['remove', 'sendData'])
const params = reactive({
  id:data.id,
  title: data.title,
  text: data.text
})
const input = ref(null)
const textArea = ref(null)
const active = ref(true)
const edite = () => {
  active.value = !active.value
  if (!active.value) {
    input.value.disabled = false
    textArea.value.disabled = false
  } else {
     emit('sendData', params)
    input.value.disabled = true
    textArea.value.disabled = true
  }
}

</script>

<template>
  <div class="bg-white p-3 shadow-lg">
    <form action="" @submit.prevent="edite">
      <div class="input-group flex flex-col justify-center gap-2">
        <label :for="`question-${index}`" class="text-gray-500"> سوال : </label>
        <input v-model="params.title" :class="{'select-none':active , 'text-gray-500':active}"
               class="relative  border p-2  border-gray-100 outline-0 rounded" type="text" placeholder="سوال"
               :id="`question-${index}`" :disabled="active" ref="input">
      </div>
      <div class="input-group mt-4 flex flex-col justify-center gap-2">
        <label :for="`answer-${index}`" class="text-gray-500">پاسخ :</label>
        <textarea v-model="params.text" placeholder="پاسخ" :class="{'select-none':active,'text-gray-500':active}"
                  class="w-full  border p-2  border-gray-100 outline-0 rounded resize-y" name="" :id="`answer-${index}`"
                  cols="30" rows="5" :disabled="active" ref="textArea"></textarea>
      </div>
      <div class="flex gap-3">
        <button
            class="border border-blue-500 text-blue-500 transition duration-100 ease-in hover:bg-blue-500 rounded hover:text-white p-2 w-full text-center mt-4"
            v-if="active">ویرایش
        </button>
        <button
            class="border border-red-500 hover:bg-red-500 text-red-500 transition duration-100 ease-in rounded hover:text-white p-2 w-full text-center mt-4"
            type="submit"
            v-else>ثبت
        </button>
        <button
            class="border border-red-500 hover:bg-red-500 text-red-500 transition duration-100 ease-in rounded hover:text-white p-2 w-full text-center mt-4"
            type="button" @click="emit('remove' , data.id)" :class="{'opacity-50' : !active}"
            :disabled="!active"
          >حذف
        </button>
      </div>
    </form>
  </div>
</template>

<style scoped lang="scss">
</style>