<script setup lang="ts">
const {edit} = defineProps({
  edit:Object
})
const emit = defineEmits(['close' , 'sendData'])
const params  = reactive({
  id:edit.id,
  color:edit.color,
  backgroundColor:edit.backgroundColor,
  name:edit.name,
  icon:edit.icon,
})
const paramsError  = reactive({
  color:false,
  backgroundColor:false,
  name:false,
  icon:false
})
function handleSubmit(){
  if (params.backgroundColor.length < 3){paramsError.backgroundColor = true}
  if (params.color.length < 3){paramsError.color = true}
  if (params.icon.length < 3){paramsError.icon = true}
  if (!params.name.length){paramsError.name=true}
  if (params.name.length && params.icon.length > 3 && params.color.length > 3 && params.backgroundColor.length > 3) {
    emit('sendData',params)
  }
}
</script>

<template>
  <div class="backdrop fixed inset-0 h-full px-3 w-full bg-[rgba(0,0,0,0.15)] overflow-y-auto" @click.self="emit('close')">
    <span v-if="Object.keys(edit).length">{{edit}}</span>
    <div class="modal-icons mt-[15vh] mx-auto md:w-1/2 lg:w-1/3 2xl:w-1/5 bg-white p-5 rounded ">
      <form action="" class="flex flex-col gap-3" @submit.prevent="handleSubmit">
        <div class="input-group flex flex-col gap-2 ">
          <label for="name"> نام آیکون :</label>
          <input v-model.trime="params.name" @keydown="paramsError.name=false"  class="px-3 py-1 text-sm rounded outline-0 border-[rgba(0,0,0,0.15]" type="text" id="name">
          <span v-if="paramsError.name" class="text-xs px-1 text-red-500">لطفا کادر بالا را پر کنید .</span>
        </div>
        <div class="input-group flex flex-col gap-2 ">
          <label for="icon"> آیکون :</label>
          <input v-model.trim="params.icon" class="px-3 py-1 text-sm rounded outline-0 border-[rgba(0,0,0,0.15]" type="text" id="icon">
          <span v-if="paramsError.icon" @keydown="paramsError.icon=false" class="text-xs px-1 text-red-500">لطفا کادر بالا را پر کنید .</span>
        </div>
        <div class="input-group flex flex-col gap-2 ">
          <label for="color">رنگ آیکون :</label>
          <input v-model.trim="params.color" class="px-3 py-1 text-sm rounded outline-0 border-[rgba(0,0,0,0.15]" type="text" id="color">
          <span v-if="paramsError.color" @keydown="paramsError.color=false" class="text-xs px-1 text-red-500">لطفا کادر بالا را پر کنید .</span>
        </div>
        <div class="input-group flex flex-col gap-2 ">
          <label for="background-color">رنگ زمینه :</label>
          <input v-model.trim="params.backgroundColor" @keydown="paramsError.backgroundColor=false" class="px-3 py-1 text-sm rounded outline-0 border-[rgba(0,0,0,0.15]" type="text" id="background-color">
          <span v-if="paramsError.backgroundColor" class="text-xs px-1 text-red-500">لطفا کادر بالا را پر کنید .</span>
        </div>
        <div class="input-group flex gap-2 justify-between mt-2">
          <button class="bg-green-500 text-white rounded mx-auto w-full px-3 py-1 text-sm">ثبت</button>
          <button class="bg-red-500 text-white rounded mx-auto w-full px-3 py-1 text-sm" @click="emit('close')">لغو</button>
        </div>
      </form>
    </div>
  </div>
</template>
<style scoped lang="scss">
input {
  border: 1px solid rgba(0, 0, 0, 0.15);
  &:focus {
    border: 2px solid rgba(0, 0, 255, 0.2);
    box-shadow: 0 0 3px rgba(0, 0, 255, 0.2);
  }
}
</style>