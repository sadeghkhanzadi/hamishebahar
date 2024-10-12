<script setup lang="ts">
const {title , comment , status , id , modal } = defineProps({
  title:{
    type:String ,
    default:""
  },
  comment:{
    type:String ,
    default:""
  },
  status:{
    type:Boolean ,
    default:true
  },
  id:String||Number,
  modal:Boolean,
})
const emit = defineEmits(['close' , 'sendData', 'delete' , 'cancel' , 'edit']) // وقتی کنسل میشه برای اینکه احتمال داره دیتامون تغییر کردنه باشه یه دور دیتا هارو رفرش میکنیم تا مطمدن بشیم چیزی تغییر نکرده
const input = ref(null)
const params = reactive({
  id:id,
  title:title,
  comment:comment,
  status:status,
  titleError:false,
  commentError:false,
  disabled:true
})
function handleForm(){
  if(params.title.length <3){
    params.titleError = true
  }
  if(params.comment.length <3){
    params.commentError = true
  }
  if(params.title.length>3 && params.comment.length>3){
    if (modal){
      emit('sendData' , {name:params.title , comment:params.comment , is_active:params.status}) // برای ثبت اطلاعات
    }
  }
}
function edit(){
  params.disabled = false
  if(params.title.length <3){
    params.titleError = true
  }
  if(params.comment.length <3){
    params.commentError = true
  }
  if(params.title.length>3 && params.comment.length>3){
    emit('edit' , {id:params.id , name:params.title , comment:params.comment , is_active:params.status})
  }
  params.disabled = true
}
function cancel(){
  params.disabled = true
  emit('cancel')
  console.log(params.disabled)
}
onMounted(()=>{
  modal ? input.value.focus() : null
})
</script>

<template>
  <div class="card p-3 bg-white rounded">
    <div class="card-body">
      <form>
        <div class="form-group">
          <label class="text-sm" for="title"> عنوان : </label>
          <input ref="input" :disabled="params.disabled && !modal" class="text-start text-sm w-full rounded border border-solid border-gray-200 mt-2 p-2" v-model.trim="params.title" id="title" type="text">
        </div>
        <div class="form-group mt-3">
          <label class="text-sm" for="comment">کامنت :</label>
          <textarea rows="5" :disabled="params.disabled && !modal" class="text-start text-sm w-full rounded border border-solid border-gray-200 mt-2 p-2"  v-model.trim="params.comment" name="" id="comment"></textarea>
        </div>
        <div class="form-group flex gap-2 items-center mt-3 ">
          <label class="text-sm" for="status">وضعیت :</label>
          <div class="flex gap-1 items-center">
            <input :disabled="params.disabled && !modal" class="text-start text-sm w-4 h-4 rounded border border-solid border-gray-100 " id="status" v-model="params.status" type="checkbox">
            <span v-if="params.status" >فعال</span>
            <span v-else >غیر فعال</span>
          </div>
        </div>
        <div class="btn flex gap-1 items-center mt-3" >
          <button v-if="params.disabled && !modal"  class="w-full text-xs rounded border border-gray-100 text-white bg-blue-500 px-2 py-1.5"  @click.prevent="params.disabled = false">ویرایش</button>
          <button v-if="params.disabled && !modal"  class="w-full text-xs rounded border border-gray-100 text-white bg-red-500 px-2 py-1.5" type="button" @click="emit('delete' , params.id )">حذف</button>
          <button v-if="!params.disabled && !modal" class="w-full text-xs rounded border border-gray-100 text-white bg-green-500 px-2 py-1.5" @click="cancel">بستن</button>
          <button v-if="!params.disabled && !modal" class="w-full text-xs rounded border border-gray-100 text-white bg-amber-400 px-2 py-1.5" @click="edit">تایید</button>
          <button v-if="modal"  class="w-full text-xs rounded border border-gray-100 text-white bg-blue-500 px-2 py-1.5">تایید</button> <!-- اینجا چون هم تو مدال بود هم به صورت عادی یه دکمه صرفا تو حالت مدال براش قرار دادم -->
        </div>
      </form>
    </div>
  </div>
</template>

<style scoped lang="scss">
input , textarea {
  border: 1px solid rgba(0, 0, 0, 0.15);
  resize: none;
  outline: 0;
  &:focus {
    border: 2px solid rgba(0, 0, 255, 0.2);
    box-shadow: 0 0 3px rgba(0, 0, 255, 0.2);
  }
}
</style>