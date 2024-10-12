<script setup lang="ts">
import {useDashboardAboutUsPlansStore} from "~/store/dashboard/aboutUs/plans";

const store = useDashboardAboutUsPlansStore()
const emit = defineEmits(['sendData' , 'remove','cancel'])
const {plans , id , text  ,disabled} = defineProps({
  plans:{
    type:Array,
    default:[]
  },
  text:{
    type:String,
    default:""
  },
  id:{
    type:String||Number,
    default:null
  },
  disabled:{
    type:Boolean,
    default:false
  }
})
const params = reactive<any>({
  inputPlans: null,
  plans: plans,
  title:'TASKS',
  text: text,
  disabled:disabled
})

function insertPlan() {
  if (params.plans.includes(params.inputPlans)) {
    params.inputPlans = null
  } else {
    params.plans.push(params.inputPlans)
    params.inputPlans = null
  }
}

function filterPlans(index) {
  if (!params.disabled){
    params.plans = params.plans.filter(item => item !== params.plans[index])
  }
}

function handlePlansForm() {
  emit(
      'sendData',
      {
        text: params.text,
        activities: params.plans,
        planTitle:'TASKS'
      })
}
function refreshData(){
  emit('cancel')
  params.disabled=true
}
function changeDisabled(){
  params.disabled=false
}
</script>

<template>
  <div>
    <form action="" @submit.prevent="handlePlansForm" class="flex flex-col gap-3">
      <div class="form-control flex flex-col gap-2">
        <label for="plan-text">عنوان :</label>
        <input :disabled="true" placeholder="عنوان" v-model="params.title" type="text" id="plan-text"
               class="rounded w-full outline-0 border px-3 py-2 border-solid border-gray-200 text-sm placeholder:text-sm"/>
      </div>
      <div class="form-control">
        <label for="plan-list">فعالیت های ما </label>
        <div class="mt-2">
          <input
              id="plan-list"
              type="text"
              placeholder="برای ثبت enter را فشار دهید"
              v-model.trim="params.inputPlans"
              @keydown.enter.prevent="insertPlan"
              :disabled="params.disabled"
              class="rounded w-full outline-0 border px-3 py-2 border-solid border-gray-200  text-sm placeholder:text-sm">
          <ul class="plans flex flex-wrap gap-1 mt-1" v-if="params.plans.length">
            <li
                class="cursor-pointer py-1 px-2 bg-blue-500 text-white w-fit rounded-r-2xl rounded-l-2xl text-xs hover:bg-red-500"
                v-for="(item , index) in params.plans"
                :key="index"
                @click="filterPlans(index)">
              <div class="flex items-center justify-center gap-2">
                <span>{{ item }}</span>
                <span class="text-sm">&times;</span>
              </div>
            </li>
          </ul>
        </div>
      </div>
      <div class="form-control flex flex-col gap-2">
        <label for="plan-text">توضیحات :</label>
        <textarea placeholder="توضیحات" v-model="params.text" id="plan-text" rows="3"
                :disabled="params.disabled"  class="rounded w-full outline-0 border px-3 py-2 border-solid border-gray-200 text-sm placeholder:text-sm"></textarea>
      </div>
      <div>
        <div class="buttons mt-5">
          <div v-if="!params.disabled">
            <button @keydown.prevent
                class="btn px-3 py-2 rounded hover:text-white text-sm border border-solid mx-0.5 border-green-500 hover:bg-green-500">
              ثبت
            </button>
            <button @keydown.prevent @click="refreshData"
                    class="btn px-3 py-2 rounded hover:text-white text-sm border border-solid mx-0.5 border-yellow-500 hover:bg-yellow-500">
              لغو
            </button>
          </div>
          <div v-else>
            <button @keydown.prevent
                class="btn px-3 py-2 rounded hover:text-white text-sm border border-solid mx-0.5 border-blue-500 hover:bg-blue-500"
                @click="changeDisabled">ویرایش
            </button>
            <button @keydown.prevent @click="emit('remove' , id)"
                    class="btn px-3 py-2 rounded hover:text-white text-sm border border-solid mx-0.5 border-red-500 hover:bg-red-500">
              حذف
            </button>
          </div>
        </div>
      </div>
    </form>
  </div>
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
