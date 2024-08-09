<script setup lang="ts">
const emit = defineEmits(['close' , 'sendMedia'])
const props = defineProps(['data', "theme"])
const file = ref(null)
const form = reactive({
  name: "",
  image: "",
  status:""
})
const status = [
  {
    value:'CERTIFICATE',
    title:"مدرک "
  },
  {
    value:'CV',
    title:"رزومه"
  },
  {
    value:'NEWS_EVENTS',
    title:"رویداد یا  اخبار"
  },
  {
    value:'COURSE',
    title:"آموزش"
  },
  {
    value:'NATIONAL_CARD',
    title:"کارت ملی"
  },
  {
    value:'BIRTH_CERTIFICATE',
    title:"شناسنامه"
  },
  {
    value:'PROFILE',
    title:"عکس پروفایل"
  },
  {
    value:'BANNER',
    title:"بنر "
  },
  {
    value:'SLIDER',
    title:"اسلایدر"
  }
]
const {data, theme} = props

function closeEditPanel() {
  emit("close")
}

function addFile() {
  form.image = file.value.files[0]
}
function handleForm(){
  emit("sendMedia" , form)
}

</script>

<template>
  <div @click.self="closeEditPanel" class="backdrop  fixed inset-0 flex justify-center items-center overflow-scroll ">
    <div class="modal w-11/12 md:8/12 lg:w-2/3 xl:w-1/3 h-auto rounded p-5">
      <div class="modal-header mb-6">
        <h3 class="edite-media font-bold text-lg border-b border-gray-200  pb-2.5">
          <span v-if="theme==='addMedia'">افزودن رسانه</span>
          <span v-else>ویرایش رسانه</span>
        </h3>
      </div>
      <div class="modal-body">
        <form @submit.prevent="handleForm">
          <div class="flex flex-col gap-y-5">
            <section :class="`grid ${!theme?'md:grid-cols-2' :'md:grid-cols-1' } gap-10  md:gap-5`">
              <div class="input-group flex flex-col gap-4" v-if="!theme && data.name">
                <label for="">نام قبلی </label>
                <input type="text" class="shadow text-gray-400  px-3 py-2 rounded"  disabled :value="data.name">
              </div>
              <div class="input-group flex flex-col gap-4">
                <label for="fileName" class="cursor-pointer">
                  <span v-if="theme === 'addMedia'">نام عکس </span>
                  <span v-else>متن جایگزین</span>
                </label>
                <input type="text" id="fileName" v-model="form.name" placeholder="نام فایل را در این قسمت وارد کنید." autocomplete="off"
                       class="shadow outline-0 focus:shadow focus:shadow-blue-200 focus:border focus:border-blue-300  text-gray-500 px-3 py-2 rounded"
                       >
              </div>
            </section>
            <section>
              <div class="select-status flex flex-col gap-4">
                <div class="status-title"><h5>نوع فایل :</h5></div>
                <select name="" id="" v-model="form.status" class="grid appearance-none focus:shadow focus:shadow-blue-200 focus:border focus:border-blue-300 w-full px-2 py-2 rounded-lg outline-0 fo gap-5">
                  <option  value="">یک مورد را انتخاب کنید</option>
                  <option  v-for="(item , index) in status" :key="index" :value="item.value">{{item.title}}</option>
                </select>
              </div>
            </section>
            <section :class="`grid ${!theme?'md:grid-cols-2' :'md:grid-cols-1' } gap-x-5 `">
              <div class="input-group  flex flex-col gap-4" v-if="!theme">
                <span>تصویر موجود</span>
                <figure class="w-[100px] h-[8=100px] rounded overflow-hidden" v-if="data.img">
                  <img class="w-full h-full bg-gray-300 block" :src="data.img" :alt="data.name">
                </figure>
              </div>
              <div class="input-group flex flex-col gap-4 mt-10 md:mt-0">
                <div>
                  <span v-if="theme === 'addMedia'">تصویر</span>
                  <span v-else>تصویر جایگزین</span>
                  <span class="block text-xs mt-2.5 text-red-500">حداکثر حجم فایل باید کمتر 500 کیلوبایت باشد</span>
                </div>
                <label for="file"
                       class="shadow bg-white hover:cursor-pointer border text-center py-5 px-1 rounded text-gray-400 border-dashed">
                  <span v-if="form.image">
                      {{ form.image.name }}
                  </span>
                  <div v-else>
                    <span class="text-sm block">                  فایل را بکشید و رها کنید . </span>
                    <span class="text-xs">(پسوندها jpg , png , webp , jpeg , txt , pdf , mp4 )</span>
                  </div>
                  <input type="file" ref="file" class="hidden" id="file" @change="addFile"
                         accept=".jpg , .png , .webp , .jpeg , .txt , .pdf , .mp4 ">
                </label>
              </div>
            </section>
          </div>
          <div>
            <div class="button flex gap-2 mt-7">
              <button class="update active:scale-[0.98] shadow">
                <span v-if="theme === 'addMedia'">ارسال</span>
                <span v-else>به روز رسانی</span>
              </button>
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
  animation: showModal 0.5s ease forwards 1;

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

  input::placeholder {
    font-size: 12px;
  }
}

@keyframes showModal {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
</style>