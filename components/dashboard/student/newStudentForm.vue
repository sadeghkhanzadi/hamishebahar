<script setup lang="ts">
const {event, get, edit, create, pending } = defineProps(['event', 'get', 'edit', 'create', 'pending'])
const apiBaseUrl = useState('apiBaseUrl').value
const cookie = useCookie('jwt')
const emit = defineEmits(['close', 'sendData'])
const showMedia = ref(false)
const status = ref(true)
const form = reactive({
  name: "",
  lastName: "",
  studentAge: "",
  is_active: true,
  medias: [],
  nationalCode:"",
  studentCode:"",
  emergencyPhoneNumber:"",
  importantDetails:"",
  specialDisease:"",
  phoneNumber:""
})
const formError = reactive({
  name: false,
  lastName: false,
  studentAge: false,
  medias: false,
  nationalCode:false,
  studentCode:false,
  emergencyPhoneNumber:false,
  importantDetails:false,
  specialDisease:false,
  phoneNumber:false
})

function selectFile(files) {
  files.forEach(file => {
    if (form.medias.find(item => item.id == file.id)) {
      return
    } else {
      form.medias.push(file)
    }
  })

}

function removeMedias(index: any) {
  if (form.medias.length) {
    form.medias = form.medias.filter(item => item.id != form.medias[index].id)
  }
}

const handleAddNews = () => {
  console.log(form)
  if (!form.name.length) {
    formError.name = true
  }
  if (!form.lastName.length) {
    formError.lastName = true
  }
  if (!form.studentAge.length) {
    formError.studentAge = true
  }
  if (!form.nationalCode.length) {
    formError.nationalCode = true
  }
  if (!form.studentCode.length) {
    formError.studentCode = true
  }
  if (!form.phoneNumber.length) {
    formError.phoneNumber = true
  }
  if (!form.emergencyPhoneNumber.length) {
    formError.emergencyPhoneNumber = true
  }

  if (
      form.name.length &&
      form.lastName.length &&
      form.studentAge.length &&
      form.nationalCode.length &&
      form.studentCode.length &&
      form.emergencyPhoneNumber.length &&
      form.phoneNumber
  ) {
    if (create) { /*  با توجه به اینکه پراپس از نوع edit یا create درخواست فرث میکنه */
      emit('sendData', form, 'create')
    }
    if (edit){
      emit('sendData', form, 'edit')
    }
  }
}


/*  برای تعییر هر خبر یک پراپس از والد که ای دی فرزند است را دریافت کرده و سپس درخواست میزنیم اما با استفاده از یک دیتا مشخص میکنیم که در خروجی دیتای ما از نوع ادیت است یا create*/
if (get) {
  const {data, error, status} = await useFetch(() => `${apiBaseUrl}/api/v1/student/`, {
    query: {
      id: get
    },
    headers: {
      Authorization: `${cookie.value}`
    }
  })
  if (error.value) {
    console.log(error.value)
  }
  if (status.value === 'success') {
    console.log(data.value.result)
    form.name = data.value.result.first_name
    form.lastName = data.value.result.last_name
    form.is_active = data.value.result.is_active
    form.studentCode = data.value.result.studentCode
    form.studentAge = data.value.result.studentAge
    form.phoneNumber = data.value.result.phoneNumber
    form.nationalCode = data.value.result.nationalCode
    form.emergencyPhoneNumber = data.value.result.emergencyPhoneNumber
    form.importantDetails = data.value.result.importantDetails
    form.specialDisease = data.value.result.specialDisease
    data.value.result.documentFiles.forEach(item => {
      form.medias.push({id: item.id, name: item.name})
    })

  }

}
</script>

<template>
  <div class="modal-backdrop overflow-auto" @click.self="emit('close')">
    <div class="modal-content mx-auto p-5 bg-white rounded ">
      <div class="text-center  mt-2 mb-10">
        <h1>{{ event }}</h1>
      </div>
      <form @submit.prevent="handleAddNews" class="flex flex-col gap-y-3">
        <section class="flex justify-between gap-2">
          <section class="input-group w-full flex flex-col gap-1.5">
            <label for="title">نام :</label>
            <input type="text" v-model.trim="form.name" id="title">
            <p v-if="formError.name" class="error">لطفا نام خود را وارد کنید .</p>
          </section>
          <section class="input-group w-full flex flex-col gap-1.5">
            <label for="text">نام خانوادگی :</label>
            <input v-model.trim="form.lastName"/>
            <p v-if="formError.lastName" class="error">لطفا نام خانوادگی خود را وارد کنید</p>
          </section>
        </section>
        <section class="flex justify-between gap-2">
          <section class="input-group w-full flex flex-col gap-1.5">
            <label for="text">کد دانش آموزی :</label>
            <input v-model.trim="form.studentCode"/>
            <p v-if="formError.studentCode" class="error">لطفا کد دانش آموزی خود را وارد کنید</p>
          </section>
          <section class="input-group w-full flex flex-col gap-1.5">
            <label for="text">کد ملی :</label>
            <input v-model.trim="form.nationalCode"/>
            <p v-if="formError.nationalCode" class="error">لطفا کدملی خود را وارد کنید</p>
          </section>
          <section class="input-group w-full flex flex-col gap-1.5">
            <label for="startTime">سن:</label>
            <input type="text" v-model.trim="form.studentAge">
            <p v-if="formError.studentAge" class="error">لطفا سن خود را وارد کنید</p>
          </section>
        </section>
        <section class="flex justify-between gap-2">
          <section class="input-group w-full flex flex-col gap-1.5">
            <label for="text">شماره تماس:</label>
            <input v-model.trim="form.phoneNumber"/>
            <p v-if="formError.phoneNumber" class="error">لطفا شماره تماس خود را وارد کنید</p>
          </section>
          <section class="input-group w-full flex flex-col gap-1.5">
            <label for="text">شماره تماس اضطراری :</label>
            <input v-model.trim="form.emergencyPhoneNumber"/>
            <p v-if="formError.emergencyPhoneNumber" class="error">لطفا شماره تماس اضطراری خود را وارد کنید</p>
          </section>
        </section>
        <section class="flex justify-between gap-2">
          <section class="input-group w-full flex flex-col gap-1.5">
            <label for="text">ملاحضات:</label>
            <textarea v-model.trim="form.importantDetails" rows="3"></textarea>
          </section>
          <section class="input-group w-full flex flex-col gap-1.5">
            <label for="text">بیماری های خاص:</label>
            <textarea v-model.trim="form.specialDisease" rows="3"></textarea>
          </section>
        </section>
        <section class="input-group w-full flex flex-col gap-1.5">
          <label for="status">وضعیت :</label>
          <div class="flex items-center gap-1.5">
            <input type="checkbox" class="w-4 h-4" v-model="form.is_active" id="status">
            <span v-if="status"> فعال </span>
            <span v-else> غیر فعال </span>
          </div>
        </section>
        <section class="input-group flex flex-col gap-1.5 mt-3">
          <div
              class="w-full flex flex-wrap gap-1.5 py-2 px-3  bg-blue-400 text-white rounded hover:bg-blue-500 transition duration-200 ease-linear cursor-pointer ">
            <button type="button" @click.prevent="showMedia=true" class="text-center w-full">
              انتخاب مدارک
            </button>
          </div>
          <p v-if="formError.medias" class="error">لطفا عکس یا فیلم مورد نظر راانتخاب کنید</p>
        </section>
        <section class="flex flex-wrap gap-1.5">
          <section v-if="form.medias.length" v-for="(item , index) in form.medias" :key="index"
                   class="flex gap-1.5 items-center bg-blue-500 text-white text-sm cursor-pointer py-0.5 px-3 rounded-full"
                   @click="removeMedias(index)">
            <span>{{ item.name }}</span>
            <span class="relative top-0.5 font-bold">&times;</span>
          </section>
        </section>
        <div class="input-group">
          <button class="w-full text-center bg-red-500 py-2 rounded text-white" :disabled="pending">ارسال</button>
        </div>
      </form>
      <dashboard-card-select-media @close="showMedia = false" @select-file="selectFile" v-if="showMedia" status="CERTIFICATE"/>
    </div>
  </div>
</template>

<style scoped lang="scss">
h1 {
  font-size: var(--title-2xl);
  font-weight: var(--font-bold);
}

label {
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
    margin-top: 10vh;
    margin-bottom: 3vh;
  }
}

input:not([type="checkbox"]), textarea {
  width: 100%;
  padding: 6px 12px;
  outline: 0;
  border-radius: 3px;
  font-size: var(--text-md);
  border: 1px solid rgba(0, 0, 0, 0.1);

  &:focus {
    border: 2px solid rgba(0, 0, 255, 0.2);
    box-shadow: 0 0 3px rgba(0, 0, 255, 0.2);
  }
}

div span {
  font-size: var(--text-md);
}

button {
  font-size: var(--text-md);
}

.error {
  font-size: var(--text-sm);
  color: #ee4a62;
}
</style>
