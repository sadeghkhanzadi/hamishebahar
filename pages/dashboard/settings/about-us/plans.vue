<script setup lang="ts">
import {useDashboardAboutUsPlansStore} from "~/store/dashboard/aboutUs/plans";

const store = useDashboardAboutUsPlansStore()
await store.fetchingPlans()
const plans = computed(()=>store.getPlans)
const showModal = computed(()=>store.getShowModal)

const breadcrumb = [
  {
    name: "درباره ما",
    url:'/dashboard/settings/about-us'
  },
  {
    name: "برنامه های ما"
  },
]

async function sendData (obj:object){
 await store.insertPlans(obj)
}
async function refreshPage(){
 await store.fetchingPlans()
}
function removeActivity(id) {
  store.showAlert = true
  store.removeId = id
}
async function handleRemoveAlert(result:Boolean) {
  await store.removePlansItem(result)
}
</script>

<template>
  <section class="container mx-auto">
    <dashboard-breadcrumb :data="breadcrumb"/>
    <div class="flex items-center justify-end ">
      <button class="text-sm px-3 py-2 bg-blue-500 rounded text-white " @click="store.setShowModal(true)">ثبت فعالیت
        جدید
      </button>
    </div>
    <div class="mt-5 grid grid-cols-2 gap-3">
      <dashboard-about-us-plans
          v-for="(item , index) in plans" :key="index"
          :text="item.text"
          :plans="item.activities"
          :id="item.id"
          :disabled="true"
          @cancel="refreshPage"
          @remove="removeActivity"
      />
      <transition-group name="show-modal">
        <div v-if="showModal" @click.self="store.setShowModal(false)"
             class="fixed inset-0 bg-[rgba(0,0,0,0.15)] flex items-start justify-center p-3 z-[5]">
          <dashboard-about-us-plans @send-data="sendData" @cancel="store.setShowModal(false)"
                                            class="shadow-lg bg-white rounded p-3 w-full sm:w-1/2 md:mt-[10vh] lg:w-1/3"
                                             :disable="false"/>
        </div>
      </transition-group>
    </div>
    <transition-group name="show-modal">
    <dashboard-modal-alert v-if="store.showAlert" @result="handleRemoveAlert"/>
    </transition-group>

  </section>

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
