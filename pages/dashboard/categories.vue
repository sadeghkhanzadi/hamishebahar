<script setup lang="ts">

import {useToast} from "vue-toastification";
const toast = useToast();
const {baseUrl , public:{apiBaseUrl}} = useRuntimeConfig()
const cookie = useCookie('jwt')
const dataCategories = ref()
const showModal = ref(false)
const showAlertModal = ref(false)
const deleteItemId = ref<number>(0)
await getData()
async function getData(){
  let url  = null
  process.client ? url= apiBaseUrl : url=baseUrl
  const {data , status , refresh} =await useAsyncData('getCategories' , ()=>$fetch(`${url}/api/v1/view/category` , {
    headers:{
      Authorization: `${cookie.value}`
    },
  }))
  if (status.value === 'success'){
    console.log(data.value.result.content)
    dataCategories.value = data.value.result.content
  }
}

async function insertCategories(body:object){
  let url  = null
  process.client ? url= apiBaseUrl : url=baseUrl
  const {data , status} =await useAsyncData('insertCategories' , ()=>$fetch(`${url}/api/v1/insert/category` , {
    method:`POST` ,
    headers:{
      Authorization: `${cookie.value}`
    },
    body: JSON.stringify(body)
  }))
  if (status.value === 'success'){
    console.log(data.value.result.content)
    await getData()
  }
  showModal.value = false
}

async function removeItem(id:string){
  let url  = null
  process.client ? url= apiBaseUrl : url=baseUrl
  const {data , status} =await useAsyncData('deleteCategories' , ()=>$fetch(`${url}/api/v1/category/${id}` , {
    method:`DELETE` ,
    headers:{
      Authorization: `${cookie.value}`
    },
    body: JSON.stringify({id:id})
  }))
  if (status.value === 'success'){
    console.log(data.value.result.content)
    toast.success('آیتم با موفقیت پاک شد')
    await getData()
  }
  showAlertModal.value = false
}

async function editItem(body:object){
  let url  = null
  process.client ? url= apiBaseUrl : url=baseUrl
  const {data , status} =await useAsyncData('editCategories' , ()=>$fetch(`${url}/api/v1/category/${body.id}` , {
    method:`PUT` ,
    headers:{
      Authorization: `${cookie.value}`
    },
    body: JSON.stringify(body)
  }))
  if (status.value === 'success'){
    console.log(data.value.result.content)
    await getData()
  }
}

 function showAlert(id:string|number){
  deleteItemId.value = id
  showAlertModal.value = true
}

async function resultRemoveItem(result:boolean):void{
  if (result){
    await removeItem(deleteItemId.value)
  }
  else{
    showAlertModal.value = false
  }
}
</script>

<template>
  <div class="categories flex flex-col gap-5">
    <div class="new-categories">
      <button class="text-sm text-white rounded bg-blue-500 px-3 py-2 float-left" @click="showModal=true">دسته بندی جدید</button>
    </div>
    <div class="categories-content grid grid-cols-12 gap-2 md:gap-3">
      <dashboard-modal-categories
          v-if="dataCategories&&dataCategories.length"
          v-for="(item , index) in dataCategories"
          :key="index"
          @delete="showAlert"
          @sendData="insertCategories"
          @edit="editItem"
          @cancel="refreshNuxtData"
          class="col-span-12 md:col-span-6 lg:col-span-3"
          :id="item.id"
          :status="item.is_active"
          :title="item.name"
          :comment="item.comment"
      />
    </div>
    <template v-if="showModal">
      <transition-group>
        <div  class="backdrop flex justify-center fixed bg-[rgba(0,0,0,0.15)] inset-0 w-full h-full" @click.self="showModal = !showModal">
          <div class="modal-body mt-[12vh]">
            <dashboard-modal-categories
                @sendData="insertCategories"
                :modal="true" />
          </div>
        </div>
      </transition-group>
    </template>
  </div>
    <transition name="transition">
      <dashboard-modal-alert v-if="showAlertModal" @result="resultRemoveItem" />
    </transition>
</template>

<style scoped lang="scss">
 .transition-leave-active{
  animation: showing 0.3s ease-in-out forwards reverse;
 }
 .transition-enter-active{
  animation: showing 0.3s ease-in-out forwards;
 }
 @keyframes showing {
   from{
     opacity: 0;
   }
   to{
     opacity: 1;
   }
 }
</style>