<script setup lang="ts">
const showMedia = ref(false)
const form = reactive({
  title: "",
  text: "",
  startDate: "",
  endDate: "",
  is_active: "",
  medias: []
})
const status = ref(true)
const medias = ref([])
function selectFile(files){
  files.forEach(file=>{
    if (medias.value.find(item => item.id  == file.id )){
      return
    }
    else{
      medias.value.push(file)
    }
  })

}
function removeMedias(index:any){
  if (medias.value.length){
  medias.value = medias.value.filter(item => item.id != medias.value[index].id)
  }
}
</script>

<template>
  <div>
    <form action="" class="flex flex-col gap-y-3">
      <div class="input-group flex flex-col gap-2">
        <label for="title">تیتر</label>
        <input type="text" id="title">
      </div>
      <div class="input-group flex flex-col gap-2">
        <label for="text">متن</label>
        <input type="text" id="text">
      </div>
      <div class="input-group flex flex-col gap-2">
        <label for="startTime">زمان شروع نمایش :</label>
        <input type="datetime-local" multiple id="startTime">
      </div>
      <div class="input-group flex flex-col gap-2">
        <label for="startTime"> زمان پایان نمایش :</label>
        <input type="datetime-local"  id="startTime">
      </div>
      <div class="input-group flex flex-col gap-2 flex gap-2">
        <label for="status">وضعیت :</label>
        <div class="flex items-center gap-2">
          <input type="checkbox" class="w-4 h-4" v-model="status" id="status">
          <span v-if="status"> فعال </span>
          <span v-else> غیر فعال </span>
        </div>
      </div>
      <div class="input-group flex flex-col gap-2 mt-3">
        <div class="w-full flex flex-wrap gap-2 py-2 px-3  bg-blue-400 text-white rounded hover:bg-blue-500 transition duration-200 ease-linear cursor-pointer ">
          <button type="button" @click.prevent="showMedia=true" class="text-center w-full">
            انتخاب عکس
          </button>
           </div>
      </div>
      <div class="flex flex-wrap gap-2">
        <div v-if="medias.length" v-for="(item , index) in medias" :key="index" class="flex gap-2 items-center bg-blue-500 text-white text-sm cursor-pointer py-0.5 px-3 rounded-full" @click="removeMedias(index)">
          <span >{{item.name}}</span>
          <span class="relative top-0.5 font-bold">&times;</span>
        </div>
      </div>
    </form>
          <dashboard-card-select-media @close="showMedia = false" @select-file="selectFile" v-if="showMedia" />
  </div>
</template>

<style scoped lang="scss">
input:not([type="checkbox"]) {
  width: 100%;
  padding: 6px 12px;
  outline: 0;
  border-radius: 3px;

  &:focus {
    border: 2px solid rgba(0, 0, 255, 0.2);
    box-shadow: 0 0 3px rgba(0, 0, 255, 0.2);
  }
}
</style>
