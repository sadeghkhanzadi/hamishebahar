<script setup lang="ts">
const {event , get , edit ,create , pending} = defineProps(['event','get' , 'edit' , 'create' , 'pending'])
const apiBaseUrl = useState('apiBaseUrl').value
const cookie = useCookie('jwt')
const emit = defineEmits(['close', 'sendData'])
const showMedia = ref(false)
const status = ref(true)
const form = reactive({
  title: "",
  text: "",
  dateTime:"",
  is_active: true,
  medias:[]
})
const formError = reactive({
  title:false,
  text:false,
  dateTime:false,
  medias:false
})
function selectFile(files){
  files.forEach(file=>{
    if ( form.medias.find(item => item.id  == file.id )){
      return
    }
    else{
     form.medias.push(file)
    }
  })

}
function removeMedias(index:any){
  if  (form.medias.length){ form.medias= form.medias.filter(item => item.id != form.medias[index].id)
  }
}
const handleAddNews = ()=>{
  if (!form.title.length){
    formError.title = true
  }
  if (!form.text.length){
    formError.text = true
  }
  if (!form.dateTime.length){
    formError.dateTime = true
  }
  if (!form.medias.length){
    formError.medias=true
  }

  if ( form.title.length && form.text.length && form.dateTime.length && form.medias.length){
    if (create){ /*  با توجه به اینکه پراپس از نوع edit یا create درخواست فرث میکنه */
    emit('sendData' , form , 'create')
    }
    else{
      emit('sendData' , form , 'edit')
    }
  }
}



/*  برای تعییر هر خبر یک پراپس از والد که ای دی فرزند است را دریافت کرده و سپس درخواست میزنیم اما با استفاده از یک دیتا مشخص میکنیم که در خروجی دیتای ما از نوع ادیت است یا create*/
if (get){
  const { data, error, status} = await useFetch(() => `${apiBaseUrl}/api/v1/news/`, {
    query:{
      id:get
    },
    headers:{
      Authorization:`${cookie.value}`
    }
  })
  if (error.value){
    console.log(error.value)
  }
  if (status.value === 'success'){
    console.log(data.value.result)
    form.title = data.value.result.title
    form.text = data.value.result.text
    form.is_active= data.value.result.is_active
    form.title = data.value.result.title
    form.dateTime = [ data.value.result.startDate , data.value.result.endDate]
    data.value.result.medias.forEach(item =>{
     form.medias.push({id:item.id , name:item.name})
    })
  }

}
</script>

<template>
  <div class="modal-backdrop overflow-auto" @click.self="emit('close')">
    <div class="modal-content mx-auto p-5 bg-white rounded ">
      <div class="text-center  mt-2 mb-5">
        <h1>{{event}}</h1>
      </div>
      <form @submit.prevent="handleAddNews" class="flex flex-col gap-y-3">
      <div class="input-group flex flex-col gap-2">
        <label for="title">تیتر</label>
        <input type="text" v-model.trim="form.title" id="title">
        <p v-if="formError.title" class="error">لطفا تیتر خبر را وارد کنید .</p>
      </div>
      <div class="input-group flex flex-col gap-2">
        <label for="text">متن</label>
        <textarea v-model.trim="form.text" rows="4"></textarea>
        <p v-if="formError.text" class="error">لطفا متن خبر را وارد کنید</p>
      </div>
      <div class="input-group flex flex-col gap-2">
        <label for="startTime">بازه نمایش خبر:</label>
        <date-picker v-model="form.dateTime"  range />
        <p v-if="formError.dateTime" class="error">لطفا تاریخ شروع و پایان خبر را وارد کنید</p>
      </div>
      <div class="input-group flex flex-col gap-2">
        <label for="status">وضعیت :</label>
        <div class="flex items-center gap-2">
          <input type="checkbox" class="w-4 h-4" v-model="form.is_active" id="status">
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
          <p v-if="formError.medias" class="error">لطفا عکس یا فیلم مورد نظر را برای خبر وارد کنید</p>
      </div>
      <div class="flex flex-wrap gap-2">
        <div v-if="form.medias.length" v-for="(item , index) in form.medias" :key="index" class="flex gap-2 items-center bg-blue-500 text-white text-sm cursor-pointer py-0.5 px-3 rounded-full" @click="removeMedias(index)">
          <span >{{item.name}}</span>
          <span class="relative top-0.5 font-bold">&times;</span>
        </div>
      </div>
        <div class="input-group" >
          <button class="w-full text-center bg-red-500 py-2 rounded text-white" :disabled="pending">ارسال</button>
        </div>
    </form>
      <dashboard-card-select-media @close="showMedia = false" @select-file="selectFile" v-if="showMedia" status="NEWS_EVENTS" />
    </div>
  </div>
</template>

<style scoped lang="scss">
h1{
  font-size: var(--title-xl);
  font-weight: var(--font-bold);
}
label{
font-size: var(--title-md);
  font-weight: var(--font-bold);
}
.modal-backdrop {
  background: rgba(0, 0, 0, 0.3);
  backdrop-filter: blur(3px);
  position: fixed;
  inset: 0;
  z-index: 99;

  .modal-content {
    max-width: 850px;
    margin-top: 15vh;
    margin-bottom: 5vh;
  }
}
input:not([type="checkbox"]) ,textarea {
  width: 100%;
  padding: 6px 12px;
  outline: 0;
  border-radius: 3px;
  font-size: var(--text-md);
  border: 1px solid rgba(0,0,0,0.1);
  &:focus {
    border: 2px solid rgba(0, 0, 255, 0.2);
    box-shadow: 0 0 3px rgba(0, 0, 255, 0.2);
  }
}
div span {
font-size: var(--text-md);
}
button{
  font-size: var(--text-md);
}
.error {
  font-size: var(--text-sm);
  color: #ee4a62;
}
</style>
