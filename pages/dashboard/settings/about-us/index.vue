<script setup lang="ts">
import {useDashboardAboutUsStore} from "~/store/dashboard/aboutUs";
import SelectOption from "~/components/dashboard/selectOption.vue";
const showMedia = ref(false)
const store = useDashboardAboutUsStore()
store.fetchingAboutUs()
 const params = reactive({
   text:"",
   address:"",
   phoneNumberCompany:"",
   mobileNumber:"",
   emailAddress:"",
   is_active:true,
   medias:[],
   aboutUsPlans:[{id:1}]
 })
function selectFile(files){
  files.forEach(file=>{
    if ( params.medias.find(item => item.id  == file.id )){
      return
    }
    else{
      params.medias.push(file)
    }
  })

}
</script>

<template>
<section>
  <div class="bg-white p-5">
    <form action="">
      <div class="lg:grid grid-cols-3 gap-2">
        <div class="form-control flex flex-col gap-2 mt-2">
          <label class="text-sm" for="phoneCompany">شماره تماس :</label>
          <input v-model="params.phoneNumberCompany" type="text" id="phoneCompany" class="px-3 py-1.5 rounded outline-0 border border-gray-200 ">
        </div>
        <div class="form-control flex flex-col gap-2 mt-2">
          <label class="text-sm" for=" mobile ">شماره همراه :</label>
          <input v-model="params.mobileNumber" type="text" id=" mobile " class="px-3 py-1.5 rounded outline-0 border border-gray-200 ">
        </div>
        <div class="form-control flex flex-col gap-2 mt-2">
          <label class="text-sm" for="email">ایمیل :</label>
          <input v-model="params.emailAddress" type="email" id="email" class="px-3 py-1.5 rounded outline-0 border border-gray-200 ">
        </div>
        <div class="form-control flex flex-col gap-2 mt-2 col-span-3">
          <label class="text-sm" for="description">توضیحات :</label>
          <textarea v-model="params.text" rows="3" id="description" class="px-3 py-1.5 rounded outline-0 border border-gray-200 "/>
        </div>
        <div class="form-control flex flex-col gap-2 mt-2 col-span-3">
          <label class="text-sm" for="address">آدرس :</label>
          <textarea v-model="params.address" rows="2" id="address" class="px-3 py-1.5 rounded outline-0 border border-gray-200 "/>
        </div>
        <div class="form-control col-span-3">
          <div class="input-group flex flex-col gap-2 mt-3">
            <div class="w-full flex flex-wrap gap-2 py-2 px-3  bg-blue-400 text-white rounded hover:bg-blue-500 transition duration-200 ease-linear cursor-pointer ">
              <button type="button" @click.prevent="showMedia=true" class="text-center w-full">
                رسانه
              </button>
            </div>
          </div>
          <div class="flex flex-wrap gap-2 mt-2">
            <div v-if="params.medias.length" v-for="(item , index) in params.medias" :key="index" class="flex gap-2 items-center bg-blue-500 text-white text-sm cursor-pointer py-0.5 px-3 rounded-full" @click="removeMedias(index)">
              <span >{{item.name}}</span>
              <span class="relative top-0.5 font-bold">&times;</span>
            </div>
          </div>
        </div>
        <div class="form-control flex flex-col gap-2 mt-2 col-span-3">
          <label class="text-sm" for="">برنامه ها :</label>
          <select-option @send-data="params.aboutUsPlans = $event" :currentItem="icon" :data="icons" curentIcon=""/>
          <input v-model="params.aboutUsPlans" type="text" class="px-3 py-1.5 rounded outline-0 border border-gray-200 ">
        </div>
        <div class="form-control flex items-center gap-2 mt-2 col-span-3">
          <label class="text-sm" for="status">وضعیت :</label>
          <input v-model="params.is_active" id="status" type="checkbox" class="w-4 h-4  outline-0 border border-gray-200 ">
          <div>
            <span v-if="params.is_active">فعال</span>
            <span v-else>غیر فعال</span>
          </div>
        </div>
      </div>
      <div class="flex justify-center mt-3">
        <button class="text-sm text-white px-3 py-2 bg-blue-500 rounded ">بروز رسانی</button>
      </div>
    </form>
  </div>
</section>
  <dashboard-card-select-media @close="showMedia = false" @select-file="selectFile" v-if="showMedia" status="NEWS_EVENTS" />

</template>

<style scoped lang="scss">
input, textarea {
  border: 1px solid rgba(0, 0, 0, 0.15);
  resize: none;
  outline: 0;

  &:focus {
    border: 2px solid rgba(0, 0, 255, 0.2);
    box-shadow: 0 0 3px rgba(0, 0, 255, 0.2);
  }
}
</style>
