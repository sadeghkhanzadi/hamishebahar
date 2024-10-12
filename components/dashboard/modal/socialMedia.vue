<script setup lang="ts">
const {icons} = defineProps(['icons'])
const emit = defineEmits(['close', 'sendData'])
const params = reactive({
  name: "",
  url: "",
  icon: "یک مورد را انتخاب کنید.",
})
const paramsError = reactive({
  url: false,
  name: false,
  icon: false
})

function handleSubmit() {
  if (params.name.length < 3) {
    paramsError.name = true
  }
  if (params.url.length < 3) {
    paramsError.url = true
  }
  if (params.icon.length < 3) {
    paramsError.icon = true
  }
  if (params.name.length && params.icon.length > 3 && params.url.length > 3) {
    let icon = icons.filter((item) => item.icon == params.icon)
    emit('sendData', {
      icon: {id:icon[0].id},
      name: params.name,
      url: params.url,
    })
  }

}

</script>

<template>
  <div class="backdrop fixed inset-0 h-full px-3 w-full bg-[rgba(0,0,0,0.15)] overflow-y-auto"
       @click.self="emit('close')">
    <div class="modal-icons mt-[15vh] mx-auto md:w-1/2 lg:w-1/3 2xl:w-1/5 bg-white p-5 rounded ">
      <form action="" class="flex flex-col gap-3" @submit.prevent="handleSubmit">
        <div class="input-group flex flex-col gap-2 ">
          <label for="icon"> آیکون :</label>
          <div class="flex items-center gap-3">
            <select v-model="params.icon" class=" w-full py-1 h-1/2 rounded outline-0">
              <option value="یک مورد را انتخاب کنید.">
                <p>
                  <span class="text-xs">یک مورد را انتخاب کنید .</span>
                </p>
              </option>
              <option :value="item.icon" v-for="(item , index) in icons" :key="index">
                <p>
                  <span>{{ item.name }}</span>
                  <span class="bg-blue-500 w-1/2">
                <i :class="item.icon"/>
                </span>
                </p>
              </option>
            </select>
            <span class="flex items-center justify-center">
            <i :class="params.icon" :style="{color:params.icon.color , backgroundColor:params.icon.backgroundColor }" class="fa-xl"/>
          </span>
          </div>
          <span v-if="paramsError.icon" @keydown="paramsError.icon=false" class="text-xs px-1 text-red-500">لطفا کادر بالا را پر کنید .</span>
        </div>
        <div class="input-group flex flex-col gap-2 ">
          <label for="name"> نام :</label>
          <input v-model.trime="params.name" @keydown="paramsError.name=false"
                 class="px-3 py-1 text-sm rounded outline-0 border-[rgba(0,0,0,0.15]" type="text" id="name">
          <span v-if="paramsError.name" class="text-xs px-1 text-red-500">لطفا کادر بالا را پر کنید .</span>
        </div>
        <div class="input-group flex flex-col gap-2 ">
          <label for="color">لینک :</label>
          <input v-model.trim="params.url" class="px-3 py-1 text-sm rounded outline-0 border-[rgba(0,0,0,0.15]"
                 type="text" id="color">
          <span v-if="paramsError.url" @keydown="paramsError.url=false" class="text-xs px-1 text-red-500">لطفا کادر بالا را پر کنید .</span>
        </div>
        <div class="input-group flex justify-center mt-2">
          <button class="bg-green-500 text-white rounded mx-auto w-full px-3 py-1">ثبت</button>
        </div>
      </form>
    </div>
  </div>
</template>
<style scoped lang="scss">

input, select {
  border: 1px solid rgba(0, 0, 0, 0.15);

  &:focus {
    border: 2px solid rgba(0, 0, 255, 0.2);
    box-shadow: 0 0 3px rgba(0, 0, 255, 0.2);
  }
}

</style>