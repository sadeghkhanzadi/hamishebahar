<script setup lang="ts">
import book from "~/assets/image/shape/book.png"
import laboratory from "~/assets/image/shape/labratoar.png"
import {useToast} from "vue-toastification";
const Toast = useToast()
const time = new Date()
const cookie = useCookie('jwt',{
  expires:new Date(time.getTime()+(60*60*24*1000))
})
const {public:{baseUrl}} = useRuntimeConfig()
definePageMeta({
  layout: "header"
})
const emit = defineEmits(['closeLogin', 'showSignup'])
const form = reactive({
  username: "",
  password: "",
})
const error = reactive({
  userName: "",
  password: "",
})
const password = ref("password")
const eye = ref(false)

function showPassword() {
  eye.value = !eye.value
  password.value === "password" ? password.value = "text" : password.value = "password"
}

const shapes = [
  {
    name: "book",
    img: book
  },
  {
    name: "laboratory",
    img: laboratory
  }
]

async function handleSubmit() {

  if (form.password.length <= 5) {
    error.password = "کلمه عبور باید بیشتر از 5 کاراکتر باشد ."
  }
  if (form.username.length <= 5) {
    error.userName = "نام کاربری باید بیشتر از 5 کاراکتر باشد ."
  }
  if (!error.userName && !error.password) {
    const {data,status, error} = await useFetch( () => `${baseUrl}/jwt/login`, {
          method: 'POST',
          body: JSON.stringify({username:form.username , password:form.password}),
        },
    )
    if (status.value === "success") {
      console.log(data.value)
      cookie.value = data.value
      navigateTo({path:'/dashboard'})
      Toast.success('با موفقیت وارد شدید.')
    }
    if (error.value) {
      console.log(error)
      if (error.value.statusCode === 400 || '400'){
      Toast.error('نام کاربری یا رمز عبور اشتباه است .')
      }else {
        Toast.error('سیستم قادر به پاسخگویی نمیباشد لطفا مجددا تلاش کنید.')
      }
    }
  }
}
</script>

<template>
  <div class="container mx-auto px-5 w-full ">
    <div class="login mt-[15vh] mb-10 relative w-full ">
      <div class="w-full sm:w-2/3 lg:w-1/2 xl:w-2/5 mx-auto border rounded-lg py-7 px-5 bg-white ">
        <form action="" @submit.prevent="handleSubmit" class=" ">
          <div class="title text-center mb-10 text-xl "><h3>فرم ورود</h3></div>
          <div class="flex flex-col gap-4 mb-4">
            <div class="form-group flex flex-col  gap-3 ">
              <label for="userName">
                <span class="me-1"><i class="fa-solid fa-user-alt"/></span>
                <span> نام کاربری :</span>
              </label>

              <input id="userName" type="text" v-model.trim="form.username"
                     @keydown="error.userName.length ? error.userName = '' : ''"
                     class="w-full border-0 outline-0 border-b px-5 text-sm">
              <span class="error">{{ error.userName }}</span>
            </div>
            <div class="form-group flex flex-col relative gap-3 ">
              <label for="password">
                <span class="me-1"><i class="fa-solid fa-lock"/></span>
                <span> کلمه عبور : </span>
              </label>

              <input :type="password" v-model.trim="form.password" id="password"
                     @keydown="error.password.length ? error.password = '' : ''"
                     class="w-full border-0 outline-0 border-b px-5 text-sm">
              <span class="error">{{ error.password }}</span>
              <span class="show-password absolute top-6 left-0" @click="showPassword">
          <i class="fa-solid fa-eye-slash" v-if="eye"/>
          <i class="fa-solid fa-eye" v-else/>
        </span>
            </div>
            <div class="submit">
              <button type="submit" class="border rounded w-full py-2 mt-4">ورود</button>
            </div>
          </div>
        </form>
        <div>
          <ul class="remember-password flex flex-col items-center gap-y-4 sm:flex-row  sm:justify-between md:px-2.5">
            <li class="text-sm border w-full text-center py-2 rounded sm:rounded-none sm:border-none sm:text-right sm:w-auto">
              <nuxt-link to="">کلمه عبور را فراموش کرده ام.</nuxt-link>
            </li>
            <li class="text-sm border w-full text-center py-2 rounded sm:rounded-none sm:border-none sm:text-left sm:p-0 sm:w-auto">
              <button class="hover:cursor-pointer" @click="emit('showSignup')">ثبت نام</button>
            </li>
          </ul>
        </div>
      </div>
      <div class="shapes hidden ">
        <div v-for="(item , index) in shapes" :key="index"
             :class="`shape-move absolute shape shape-${item.name} opacity-50`">
          <span><img :src="item.img" :alt="item.name"></span>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
.container {
  min-height: calc(100vh - 710px);
}

.login {
  color: rgba(24, 24, 24, 1);

  form {
    background-color: #FFFFFF;

  }
}

label {
  font-size: 14px;
}

.shape {
  z-index: -1;
}

.shape-laboratory {
  top: -10%;
  right: 25%;
}

.shape-book {
  bottom: -15%;
  left: 20%;
}

button:active {
  scale: 0.99;
}

.error {
  font-size: 12px;
  color: #ee4a62;
  display: block;
}
</style>