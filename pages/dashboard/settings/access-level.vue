<script setup lang="ts">
import {useDashboardAccessLevelStore} from "~/store/dashboard/accessLevel";

const showNewForm = ref(true)
const store = useDashboardAccessLevelStore()
const breadcrumb = [
  {
    name:"سطوح دسترسی"
  }
]
function register(obj){
store.register(obj)
}
</script>

<template>
  <div class="container mx-auto">
    <header class="flex justify-between items-center ">
      <div>
        <dashboard-breadcrumb :data="breadcrumb"/>
      </div>
      <div class="flex gap-5">
        <button class="write-key flex items-center justify-center bg-blue-500 py-2 px-5 rounded-md text-white gap-2 text-sm" @click="showNewForm = true">افزودن کاربر <i class="fa-solid fa-add"/> </button>
      </div>
    </header>
    <main>
      <transition name="showModal">
      <dashboard-settings-new-users v-if="showNewForm" @close="showNewForm = !showNewForm" @sendData="register"/>
      </transition>
    </main>
  </div>
</template>

<style scoped lang="scss">
.showModal-leave-active {
 animation: show-modal 0.35s ease  forwards reverse ;
}
.showModal-enter-active{
   animation: show-modal 0.35s ease  forwards ;
}

  @keyframes show-modal {
    from{
      opacity: 0;
    }
    to{
      opacity: 1;
    }
  }
</style>
