<script setup lang="ts">
const {event, get, edit, create, pending} = defineProps(['event', 'get', 'edit', 'create', 'pending'])
const apiBaseUrl = useState('apiBaseUrl').value
const cookie = useCookie('jwt')
const showPassword = ref(false)
const emit = defineEmits(['close', 'sendData'])
const status = ref(true)
const image = ref(null)
const showImage = ref(null)
const passwordInput = ref(null)
const form = reactive<object>({
  name: "",
  enabled: true,
  nationalCode: "",
  phoneNumber: "",
  password: "",
  email: "",
  roles: [{id: 2}],
  picture: null
})
const formError = reactive({
  name: false,
  nationalCode: false,
  phoneNumber: false,
  email: false,
  password: false,
  image: false,
})


const handleAddNews = () => {

  if (form.name.length < 2) {
    formError.name = true
  }
  if (form.nationalCode.length !== 10) {
    formError.nationalCode = true
  }
  if (form.phoneNumber.length !== 11 && form.phoneNumber[0] !=0 && form.phoneNumber[1] !=9) {
    formError.phoneNumber = true
  }
  if (form.password.length < 6) {
    formError.password = true
  }
  if (form.email.length < 12) {
    formError.email = true
  }

  if (
      form.name.length >= 2 &&
      form.nationalCode.length === 10 &&
      form.password.length >= 6 &&
      form.email.length > 12 &&
      form.phoneNumber.length === 11&&form.phoneNumber[0] ==0 && form.phoneNumber[1] == 9
  ) {
      emit('sendData', form)
  }
}


function showPasswordKey() {
  showPassword.value = !showPassword.value
  showPassword.value ? passwordInput.value.setAttribute('type', 'text') : passwordInput.value.setAttribute('type', 'password')
}
function addImage(){
  const obj = {
    lastModified:image.value.files[0].lastModified,
    name:image.value.files[0].name,
    size:image.value.files[0].size,
    type:image.value.files[0].type,
    lastModifiedDate:image.value.files[0].lastModifiedDate,
  }
  form.picture = obj
  showImage.value = URL.createObjectURL(image.value.files[0])
}
</script>

<template>
  <div class="modal-backdrop overflow-auto px-3" @click.self="emit('close')">
    <div class="modal-content mx-auto p-5 bg-white rounded ">
      <div class="text-center  mt-2 mb-10">
        <h1>{{ event }}</h1>
      </div>
      <form @submit.prevent="handleAddNews" class="flex flex-col gap-y-3">
        <div class="flex  justify-start gap-2 items-center mb-2">
          <form action="">
            <label for="image" class="px-3 py-1.5 border border-black  text-sm hover:bg-black hover:text-white rounded transition duration-200 ease-linear cursor-pointer ">
              انتخاب عکس
              <input @change="addImage" ref="image" type="file" id="image" class="hidden" pattern="jpg , png , webp">
            </label>
          </form>
          <div v-if="showImage">
            <img class="w-16 h-16 text-xs text-center rounded-full border border-gray-300 p-0.5 object-cover" :src="showImage" alt="image profile">
          </div>
          <span v-if="formError.image && form.picture != null" class="error">لطفا یک عکس برای پروفایل کاربر انتخاب کنید</span>
        </div>
        <div class="flex justify-between items-baseline gap-2">
          <div class="input-group w-full flex flex-col gap-2">
            <label for="title">نام :</label>
            <input type="text" v-model.trim="form.name" id="title" class="placeholder:text-xs" placeholder="نام" @keydown="formError.name = false">
            <span v-if="formError.name" class="error">لطفا نام را وارد کنید .</span>
          </div>
          <div class="input-group w-full flex flex-col gap-2">
            <label for="text">کد ملی :</label>
            <input v-model.trim="form.nationalCode" class="placeholder:text-xs" placeholder="کدملی" @keydown="formError.nationalCode =false"/>
            <span v-if="formError.nationalCode" class="error">کدملی باید 10 رقمی باشد .</span>
          </div>
        </div>
        <div class="flex justify-between items-baseline gap-2">
          <div class="input-group w-full flex flex-col gap-2">
            <label for="text">شماره تماس:</label>
            <input v-model.trim="form.phoneNumber" class="placeholder:text-xs" placeholder="نمونه: 09111234567" @keydown="formError.phoneNumber = false"/>
            <span v-if="formError.phoneNumber" class="error">لطفا شماره تماس را وارد کنید</span>
          </div>
          <div class="input-group w-full flex flex-col gap-2">
            <label for="text">رمز عبور :</label>
            <div class="relative bg-amber-400">
              <input ref="passwordInput" type="password" v-model.trim="form.password" class="placeholder:text-xs" placeholder="رمز عبور" @keydown="formError.password = false"/>
              <span class="absolute  left-1 transform translate-y-1/2 translate-x-1/2 " @click="showPasswordKey">
                <i v-if="showPassword" class="fa-solid fa-eye-slash"/>
                <i v-else class="fa-solid fa-eye"/>
              </span>
            </div>
            <span v-if="formError.password" class="error"> رمز عبور باید حداقل 6 کارکتر باشد. </span>
          </div>
        </div>
        <div class="input-group w-full flex flex-col gap-2">
          <label for="text">ایمیل :</label>
          <input type="email" v-model.trim="form.email" class="placeholder:text-xs" placeholder="ایمیل" @keydown="formError.email = false"/>
          <span v-if="formError.email" class="error">ایمیل باید حداقل 13 کارکتر و شامل @ باشد</span>
        </div>
        <div class="flex flex-col gap-1.5">
          <label for="accessPoints" class="">سطح دسترسی :</label>
          <select id="accessPoints" v-model="form.roles"
                  class="flex w-full border-gray-300 py-1 px-2 text-sm rounded  border">
            <option :value="[{id:2}]">کاربر</option>
            <option :value="[{id:1} , {id:2}]">مدیر</option>
          </select>
        </div>
        <div class="input-group w-full flex flex-col gap-2">
          <label for="status">وضعیت :</label>
          <div class="flex items-center gap-2">
            <input type="checkbox" class="w-4 h-4" v-model="form.enabled" id="status" >
            <span v-if="status"> فعال </span>
            <span v-else> غیر فعال </span>
          </div>
        </div>

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

.input-group {
  h5 {
    font-size: var(--title-md);
    font-weight: var(--font-bold);
  }

  div label {
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
  padding-right: 5px ;
}
</style>
