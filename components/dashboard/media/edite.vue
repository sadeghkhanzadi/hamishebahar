<script setup lang="ts">
const emit = defineEmits(['close'])
const props = defineProps(['data' , "theme"])
const {data , theme}= props
function closeEditPanel(){
  emit("close")
}
</script>

<template>
  <div  @click.self="closeEditPanel" class="backdrop  fixed inset-0 flex justify-center items-center ">
    <div class="modal w-1/3 h-auto rounded p-5" >
      <div class="modal-header mb-6">
        <h3 class="edite-media font-bold text-lg border-b border-gray-200  pb-2.5">
          <span v-if="theme==='addMedia'">افزودن رسانه</span>
          <span v-else>ویرایش رسانه</span>
        </h3>
      </div>
      <div class="modal-body">
        <form @submit.prevent="">
          <div class="flex flex-col gap-y-5">
            <div :class="`grid ${!theme?'grid-cols-2' :'grid-cols-1' } gap-x-5`" >
              <div class="input-group flex flex-col gap-4" v-if="!theme && data.name">
                <label for="">نام قبلی </label>
                <input type="text" class="shadow text-gray-400  px-3 py-2 rounded" disabled :value="data.name">
              </div>
              <div class="input-group flex flex-col gap-4">
                <label for="">متن جایگزین</label>
                <input type="text" placeholder="نام جدید را در این قسمت وارد کنید." class="shadow outline-0 focus:shadow focus:shadow-blue-200 focus:border focus:border-blue-300  text-gray-500 px-3 py-2 rounded" value="">
              </div>
            </div>
            <div :class="`grid ${!theme?'grid-cols-2' :'grid-cols-1' } gap-x-5 `">
              <div class="input-group  flex flex-col gap-4" v-if="!theme">
                <span>تصویر</span>
                  <figure class="w-[100px] h-[8=100px] rounded overflow-hidden" v-if="data.img">
                    <img class="w-full h-full bg-gray-300 block" :src="data.img" :alt="data.name">
                  </figure>
              </div>
              <div class="input-group flex flex-col gap-4">
                <span>تصویر جایگزین</span>
                <label for="file" class="shadow hover:cursor-pointer border text-center py-5 rounded text-gray-400 border-dashed">
                  فایل را بکشید و رها کنید .
                  <input type="file" class="hidden" id="file" value="">
                </label>
              </div>
            </div>
          </div>
          <div>
            <div class="button flex gap-2 mt-7">
              <button class="update active:scale-[0.98] shadow">به روز رسانی</button>
              <button class="cancel active:scale-[0.98] shadow" @click.self="closeEditPanel">لغو</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
.backdrop {
  background: rgba(0, 0, 0, 0.2);
  backdrop-filter: opacity(0.5);
  opacity: 0;
  animation:showModal 0.5s ease forwards 1 ;
  .modal {
    background: #f5f6fa;
  }

  .update {
    color: #000;
    border: 1px solid #3498db;
    padding: 5px 20px;
    border-radius: 5px;
    text-align: center;

    &:hover {
      background: #3498db;
      color: #FFFFFF;

    }
  }

  .cancel {
    color: #000000;
    border: 1px solid orangered;
    padding: 5px 20px;
    border-radius: 5px;
    text-align: center;

    &:hover {
      background: orangered;
      color: #FFFFFF;

    }
  }

  .cancel {

  }
  input::placeholder{
    font-size: 12px;
  }
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