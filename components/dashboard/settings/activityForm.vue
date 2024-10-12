<script setup lang="ts">
import SelectOption from "~/components/dashboard/selectOption.vue";

const {icons ,title ,id , text , icon , disable ,forShowingData } = defineProps({
  icons: Array,
  disable:{
    type:Boolean,
    default:true
  },
  id:{
    type:Number||String,
    default:""
  },
  title:{
    type:String,
    default:""
  },
  text:{
    type:String,
    default:""
  },
  icon:{
    type:Object,
    default:""
  },
  forShowingData:Boolean
})
const emit = defineEmits(['cancel', 'edite', 'sendData', 'remove'])
const params = reactive({
  title: title,
  text: text ,
  icon: icon.id
})
const error = reactive({
  title: false,
  text: false,
  icon: false
})
const disabled = ref(disable)


function refreshData(){ //چون هم باید disabled بشه و هم دیتهامون به حالت قبل برگرده
  disabled.value=true
  refreshNuxtData()
  emit('cancel')
}
function handleSubmit(){
  if (!params.text.length ){
    error.text=true
  }
  if (!params.title.length ){
    error.title=true
  }
  if (!params.icon.toString().length ){
    error.icon=true
  }
  if(params.icon.toString().length && params.text.length && params.title.length){
    if(!forShowingData){
      emit('sendData' , {
        icon:{id:params.icon},
        text:params.text,
        title:params.title,
        header:params.title
      })
    }else{
      emit('edite' , {
        id:id,
        icon:{id:params.icon},
        text:params.text,
        title:params.title,
        header:params.title
      })
    }
  }
}
</script>

<template>
  <div class="bg-white p-3 rounded-lg activities " >
    <form action="" @submit.prevent="handleSubmit">
      <div class="input-group flex flex-col gap-1 mt-2">
        <label for="icon">آیکون :</label>
        <select-option @send-data="params.icon = $event" :currentItem="icon" :data="icons" curentIcon=""/>
        <span class="text-xs text-red-500 text-right" v-if="error.icon">لطفا کادر بالا را پر کنید</span>
      </div>
      <div class="input-group flex  flex-col gap-1 mt-2">
        <label for="title">عنوان : </label>
        <input :disabled="disabled"  v-model="params.title" id="title" class="rounded outline-0  px-3 py-2 text-sm"
               type="text">
        <span class="text-xs text-red-500 text-right" v-if="error.title">لطفا کادر بالا را پر کنید</span>

      </div>
      <div class="input-group flex flex-col gap-1 mt-2">
        <label for="text">متن :</label>
        <textarea :disabled="disabled" v-model="params.text" class="rounded outline-0  resize-x-0 px-3 py-2 text-sm"
                  name="" id="text" cols="30"
                  rows="3"></textarea>
        <span class="text-xs text-red-500 text-right" v-if="error.text">لطفا کادر بالا را پر کنید</span>
      </div>
      <div class="buttons mt-5">
        <div v-if="!disabled">
          <button
              class="btn px-3 py-2 rounded hover:text-white text-sm border border-solid mx-0.5 border-green-500 hover:bg-green-500">
            ثبت
          </button>
          <button @click="refreshData"
              class="btn px-3 py-2 rounded hover:text-white text-sm border border-solid mx-0.5 border-yellow-500 hover:bg-yellow-500">
            لغو
          </button>
        </div>
        <div v-else>
          <button
              class="btn px-3 py-2 rounded hover:text-white text-sm border border-solid mx-0.5 border-blue-500 hover:bg-blue-500"
              @click="disabled=false">ویرایش
          </button>
          <button @click="emit('remove' , id)"
              class="btn px-3 py-2 rounded hover:text-white text-sm border border-solid mx-0.5 border-red-500 hover:bg-red-500">
            حذف
          </button>
        </div>
      </div>
    </form>
  </div>
</template>

<style scoped lang="scss">
.activities {
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.15);
}

input, textarea, select {
  border: 1px solid rgba(0, 0, 0, 0.1);

  &:focus {
    border: 2px solid rgba(0, 0, 255, 0.2);
    box-shadow: 0 0 3px rgba(0, 0, 255, 0.2);
  }
}
</style>
