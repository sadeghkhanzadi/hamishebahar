<script setup lang="js">
import {useDashboardCoursesStore} from "~/store/dashboard/courses/index.js";
const store = useDashboardCoursesStore()
store.fetchingCourses()
const data = computed(()=>store.getCourses)
 const showModal = ref(false)
 const removeId = ref("")
  function showRemoveItemModal(id){
   removeId.value = id
    store.showRemoveModal = true
  }
  async function getResultRemoveModalItem(result){
   if (result){
     await store.deleteItem(removeId.value)
   }else{
    store.showRemoveModal = false
   }
  }

</script>

<template>
<section class="classes flex flex-col gap-y-5">
  <div class="new-class">
    <button @click="showModal = true"  class="text-sm text-white rounded bg-blue-500 px-3 py-2 float-left">کلاس جدید</button>
  </div>
  <dashboard-courses-table :data="data" @delete="showRemoveItemModal"/>
  <transition name="show-modal">
      <dashboard-modal-alert  @result="getResultRemoveModalItem" v-if="store.showRemoveModal"/>
  </transition>
</section>
</template>

<style scoped lang="scss">
.show-modal-enter-active{
  animation: showModal ease 0.3s forwards;
}
.show-modal-leave-active {
  animation: showModal ease 0.3s forwards reverse;
}
@keyframes showModal {
  from{
    opacity: 0;
  }
  to{
    opacity: 1;
  }
}
</style>
