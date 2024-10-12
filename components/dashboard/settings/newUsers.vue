<script setup lang="ts">
const {event, get, edit, create, pending } = defineProps(['event', 'get', 'edit', 'create', 'pending'])
const apiBaseUrl = useState('apiBaseUrl').value
const cookie = useCookie('jwt')
const showPassword = ref(false)
const emit = defineEmits(['close', 'sendData'])
const status = ref(true)
const passwordInput = ref(null)
const form = reactive({
  name: "",
  age: "",
  is_active: true,
  nationalCode:"",
  phoneNumber:"",
  accessPoints:""
})
const formError = reactive({
  name: false,
  nationalCode:false,
  phoneNumber:false
})


const handleAddNews = () => {
  console.log(form)
  if (!form.name.length) {
    formError.name = true
  }
  if (!form.nationalCode.length) {
    formError.nationalCode = true
  }
  if (!form.phoneNumber.length) {
    formError.phoneNumber = true
  }

  if (
      form.name.length &&
      form.nationalCode.length &&
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
  const {data, error, status} = await useFetch(() => `${apiBaseUrl}/api/v1/teacher/`, {
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
    form.name = data.value.result.firstName
    form.is_active = data.value.result.is_active
    form.phoneNumber = data.value.result.phoneNumber
    form.nationalCode = data.value.result.nationalCode
    // data.value.result.documentFiles.forEach(item => {
    //   form.medias.push({id: item.id, name: item.name})
    // })
  }

}
function showPasswordKey(){
  showPassword.value = !showPassword.value
  showPassword.value ? passwordInput.value.setAttribute('type', 'text') : passwordInput.value.setAttribute('type', 'password')
}
</script>

<template>
  <div class="modal-backdrop overflow-auto px-3" @click.self="emit('close')">
    <div class="modal-content mx-auto p-5 bg-white rounded ">
      <div class="text-center  mt-2 mb-10">
        <h1>{{ event }}</h1>
      </div>
      <form @submit.prevent="handleAddNews" class="flex flex-col gap-y-3">
        <section class="flex justify-between gap-2">
          <section class="input-group w-full flex flex-col gap-2">
            <label for="title">نام :</label>
            <input type="text" v-model.trim="form.name" id="title">
            <p v-if="formError.name" class="error">لطفا نام را وارد کنید .</p>
          </section>
          <section class="input-group w-full flex flex-col gap-2">
            <label for="text">کد ملی :</label>
            <input v-model.trim="form.nationalCode"/>
            <p v-if="formError.nationalCode" class="error">لطفا کدملی را وارد کنید</p>
          </section>
        </section>
        <section class="flex justify-between gap-2">
          <section class="input-group w-full flex flex-col gap-2">
            <label for="text">شماره تماس:</label>
            <input v-model.trim="form.phoneNumber"/>
            <p v-if="formError.phoneNumber" class="error">لطفا شماره تماس را وارد کنید</p>
          </section>
          <section class="input-group w-full flex flex-col gap-2">
            <label for="text">کلمه عبور :</label>
            <div class="relative bg-amber-400">
              <input ref="passwordInput" type="password" v-model.trim="form.password"/>
              <span class="absolute  left-1 transform translate-y-1/2 translate-x-1/2 " @click="showPasswordKey">
                <i v-if="showPassword" class="fa-solid fa-eye-slash"/>
                <i v-else class="fa-solid fa-eye"/>
              </span>
            </div>
            <p v-if="formError.password" class="error">لطفا کلمه عبور را وارد کنید</p>
          </section>
        </section>
        <section class="flex justify-between gap-2">
          <section class="input-group w-full flex flex-col gap-2">
            <label for="text">ایمیل :</label>
            <input type="email" v-model.trim="form.email"/>
            <p v-if="formError.email" class="error">لطفا ایمیل را وارد کنید</p>
          </section>
        </section>
        <div class="flex flex-col gap-1.5">
          <label for="accessPoints" class="">سطح دسترسی :</label>
          <select id="accessPoints" v-model="form.accessPoints" class="flex w-full border-gray-300 py-1 px-2 text-sm rounded  border">
            <option value="user">کاربر</option>
            <option value="admin">ادمین</option>
          </select>
        </div>
        <section class="input-group w-full flex flex-col gap-2" v-if="false">
          <h5 >سطح دسترسی :</h5>
          <div class="flex items-center gap-2">
            <input value="OP_ACCESS_ADMIN" type="checkbox" class="w-4 h-4" v-model="form.accessLevel" id="status">
            <label> دسترسی به صفحه  و منو ها ادمین سایت</label>
          </div>
          <div class="flex items-center gap-2">
            <input value="OP_ADMIN_EDIT_USER" type="checkbox" class="w-4 h-4" v-model="form.accessLevel" id="status">
            <label> دسترسی اینکه بتوان همه کاربر ها را ویرایش کرد</label>
          </div>
          <div class="flex items-center gap-2">
            <input value="OP_ADMIN_DELETE_USER" type="checkbox" class="w-4 h-4" v-model="form.accessLevel" id="status">
            <label> دسترسی اینکه بتوان هر کاربری را حذف کرد</label>
          </div>
          <div class="flex items-center gap-2">
            <input value="OP_EDIT_USER" type="checkbox" class="w-4 h-4" v-model="form.accessLevel" id="status">
            <label> دسترسی اینکه هر کاربر بتواند فقط خودش را ویرایش کند</label>
          </div>
          <div class="flex items-center gap-2">
            <input value="OP_ACCESS_USER" type="checkbox" class="w-4 h-4" v-model="form.accessLevel" id="status">
            <label> دسترسی به صفحه  و منو های مربوط به کاربر های عادی سایت (مادر و پدر ها  میتونن کیف پولشون و هزینه ها و نمرات و ... رو ببینن)</label>
          </div>
          <span v-if="formError.accessLevel">حداقل یک مورد را برای تعیین سطح دسترسی باید انتخاب کنید .</span>
        </section>




        <section class="input-group w-full flex flex-col gap-2">
          <label for="status">وضعیت :</label>
          <div class="flex items-center gap-2">
            <input type="checkbox" class="w-4 h-4" v-model="form.is_active" id="status">
            <span v-if="status"> فعال </span>
            <span v-else> غیر فعال </span>
          </div>
        </section>

        <div class="input-group">
          <button class="w-full text-center bg-red-500 py-2 rounded text-white" :disabled="pending">ثبت</button>
        </div>
      </form>
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

.input-group{
  h5{
    font-size: var(--title-md);
    font-weight: var(--font-bold);
  }
  div  label{
    font-size: var(--title-md);
    font-weight: var(--font-regular);
  }
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
  font-size: var(--text-lg);
}

.error {
  font-size: var(--text-sm);
  color: #ee4a62;
}
</style>
