<script setup lang="ts">
import {useDashboardIconsStore} from "~/store/dashboard/icons";
import {useDashboardActivitiesStore} from "~/store/dashboard/Activities";


const iconsStore = useDashboardIconsStore()
const activitiesStore = useDashboardActivitiesStore()

activitiesStore.fetchingActivities()
iconsStore.fetchingIcons()

const icons = computed(() => iconsStore.getIcons)
const activities = computed(() => activitiesStore.getActivities)
const showModal = computed(() => activitiesStore.getShowModal)
const breadcrumb = [
  {
    name: "فعالیت های ما"
  },
]


async function postData(obj) {
  await activitiesStore.insertActivities(obj)
}

function removeActivity(id) {
  activitiesStore.showAlert = true
  activitiesStore.removeId = id
}

async function handleRemoveAlert(result) {
  await activitiesStore.removeActivities(result)
}
async function editeActivities(result){
await activitiesStore.editeActivities(result)
}
</script>

<template>
  <div class="container mx-auto">
    <dashboard-breadcrumb :data="breadcrumb"/>
    <div class="flex items-center justify-end ">
      <button
          @click="activitiesStore.showNewActivityModal=true"
          class="text-sm px-3 py-2 bg-blue-500 rounded text-white ">ثبت فعالیت جدید</button>
    </div>
    <div class="mt-5 grid grid-cols-3 gap-3">
      <dashboard-settings-activityForm
          @remove="removeActivity"
          @sendDate="postData"
          @edite="editeActivities"
          :icons="icons"
          v-for="(item,index) in activities"
          :data="item"
          :title="item.title"
          :text="item.text"
          :icon="item.icon"
          :id="item.id"
          :key="index"
          :forShowingData="true"
          :disable="activitiesStore.disabled"
      />
    </div>
  </div>
  <transition-group name="show-modal">
    <div v-if="showModal" @click.self="activitiesStore.showNewActivityModal=false"
         class="fixed inset-0 bg-[rgba(0,0,0,0.15)] flex items-start justify-center p-3 z-[5]">
      <dashboard-settings-activity-form @send-data="postData" @cancel="activitiesStore.showNewActivityModal=false"
                                        class="shadow-lg w-full sm:w-1/2 md:mt-[10vh] lg:w-1/3"
                                        :icons="icons" :disable="false"/>
    </div>
  </transition-group>
  <dashboard-modal-alert v-if="activitiesStore.showAlert" @result="handleRemoveAlert"/>
</template>

<style scoped lang="scss">
.show-modal-enter-active {
  animation: show-modal 0.35 ease forwards
}

.show-modal-leave-active {
  animation: show-modal 0.35 ease forwards reverse
}

@keyframes show-modal {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
</style>
