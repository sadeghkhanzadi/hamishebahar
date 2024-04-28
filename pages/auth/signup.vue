<script setup lang="ts">
import book from "~/assets/image/shape/book.png"
import laboratory from "~/assets/image/shape/labratoar.png"

definePageMeta({
  layout: "header"
})
const password = ref("password")
const eye = ref(false)
const form = reactive({
  userName:"",
  password:"",
  repeatPassword:""
})
const error = reactive({
  userName:"",
  password:"",
  repeatPassword:""
})
function showPassword() {
  eye.value = !eye.value
  console.log(password.value)
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
function handleSubmit(){
if (form.userName.trim().length > 5){
return true
}
if (form.password.length < 5){
  error.password = "کلمه عبور باید بیشتر از 5 کاراکتر باشد ."
}
if (form.userName.trim().length < 5){
  error.userName = "نام کاربری باید بیشتر از 5 کاراکتر باشد ."
}
if (form.password !== form.repeatPassword && form.repeatPassword !== form.password ){
  error.password = "کلمه عبور  و تکرار ان باید باهم برابر باشند ."
  error.repeatPassword = "کلمه عبور  و تکرار ان باید باهم برابر باشند ."
}
}
</script>

<template>
  <div class="container mx-auto px-5">
    <div class="login mt-[15vh] mb-10 relative">
      <form action="" class="w-full sm:w-2/3 lg:w-1/2 xl:w-2/5 mx-auto border rounded-lg p-5 py-7" @submit.prevent="handleSubmit">
        <div class="title text-center mb-10 text-xl "><h3>فرم ثبت نام </h3></div>
        <div class="flex flex-col gap-4 mb-4">
          <div class="form-group flex flex-col  gap-3 ">
            <label for="userName">
              <span class="me-1"><i class="fa-solid fa-user-alt"/></span>
              <span> نام کاربری :</span>
            </label>
            <input id="userName" autocomplete="off" @keydown="error.userName.length ? error.userName = '' : ''" v-model="form.userName" type="text" class="w-full border-0 outline-0 border-b px-5 text-sm">
            <span class="error" v-if="error.userName.length">{{error.userName}}</span>
          </div>
          <div class="form-group flex flex-col relative gap-3 ">
            <label for="password">
              <span class="me-1"><i class="fa-solid fa-lock"/></span>
              <span> کلمه عبور : </span>
            </label>
            <input :type="password" autocomplete="off" id="password" @keydown="error.password.length ? error.password = '' : ''" v-model="form.password" class="w-full border-0 outline-0 border-b px-5 text-sm">
            <span class="error" v-if="error.password.length">{{error.password}}</span>
            <span class="show-password absolute top-6 left-0" @click="showPassword">
               <i class="fa-solid fa-eye-slash" v-if="eye"/>
               <i class="fa-solid fa-eye" v-else/>
            </span>
          </div>
          <div class="form-group flex flex-col relative gap-3 ">
            <label for="repeat-password">
              <span class="me-1"><i class="fa-solid fa-lock"/></span>
              <span> تکرار کلمه عبور : </span>
            </label>
            <input :type="password" autocomplete="off" @keydown="error.repeatPassword.length ? error.repeatPassword = '' : ''" v-model="form.repeatPassword" id="repeat-password" class="w-full border-0 outline-0 border-b px-5 text-sm">
            <span class="error" v-if="error.repeatPassword.length">{{error.repeatPassword}}</span>
            <span class="show-password absolute top-6 left-0" @click="showPassword">
               <i class="fa-solid fa-eye-slash" v-if="eye"/>
               <i class="fa-solid fa-eye" v-else/>
            </span>
          </div>
          <div class="submit">
            <button type="submit" class="border rounded w-full py-2 mt-4" >ثبت نام</button>
          </div>
        </div>
        <div>
          <ul class="remember-password flex flex-col items-center gap-y-4 sm:flex-row  sm:justify-between md:px-2.5">
            <li class="text-sm border w-full text-center py-2 rounded sm:rounded-none sm:border-none sm:text-left sm:p-0 sm:w-auto">
              <nuxt-link to="">کلمه عبور را فراموش کرده ام.</nuxt-link>
            </li>
            <li class="text-sm border w-full text-center py-2 rounded sm:rounded-none sm:border-none sm:text-left sm:p-0 sm:w-auto">
              <nuxt-link to="login">ورود</nuxt-link>
            </li>
          </ul>
        </div>
      </form>
      <div class="shapes hidden lg:block">
        <div v-for="(item , index) in shapes" :key="index" :class="`shape-move absolute shape shape-${item.name} opacity-50`">
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
button:active{
  scale: 0.99;
}
.error{
  font-size: 12px;
  color: #ee4a62;
  display: block;
}
</style>