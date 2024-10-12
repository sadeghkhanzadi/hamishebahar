<script setup lang="ts">
import {useToast} from "vue-toastification";
const toast = useToast()
const showContactusModal = ref(false)
const {public: {apiBaseUrl}} = useRuntimeConfig()
const cookie = useCookie('jwt')
const showBtnAddAddress = ref(false)
const params = reactive({
  id: "",
  text: "",
  address: "",
  phoneNumberCompany: "",
  mobileNumber: "",
  emailAddress: "",
  latAddress: "",
  longAddress: "",
  locationAddress: " ",
  medias:[]
})
const paramsError = reactive({
  textError: false,
  addressError: false,
  phoneNumberCompanyError: false,
  mobileNumberError: false,
  emailAddressError: false,
  latAddressError: false,
  longAddressError: false,
  locationAddressError: false,
})
const locationStatus = ref('location-full')
const edit = ref(true)

function sendData(params) {
  console.log(params)
  const {status, error} = useAsyncData('sendData', () => $fetch(`${apiBaseUrl}/api/v1/insert/contactus`, {
    method: 'POST',
    headers: {
      Authorization: `${cookie.value}`
    },
    body: JSON.stringify({
      text: params.text,
      address: params.address,
      phoneNumberCompany: params.phone,
      mobileNumber: params.mobile,
      emailAddress: params.email,
      latAddress: params.lat,
      longAddress: params.long,
      locationAddress: params.location,
    })
  }))
  if (status.value === 'success') {
    showContactusModal.value = false
  } else {
    console.log(error.value)
  }
}

async function getData() {
  const {data, status} = await useAsyncData('getData', () => $fetch(`${apiBaseUrl}/api/v1/view/contactus`))
  if (status.value === 'success') {
    const result = data.value.result
    console.log(result)
    params.id = result.id
    params.text = result.text
    params.address = result.address
    params.phoneNumberCompany = result.phoneNumberCompany
    params.mobileNumber = result.mobileNumber
    params.emailAddress = result.emailAddress
    params.latAddress = result.latAddress
    params.longAddress = result.longAddress
    params.locationAddress = result.locationAddress
    params.medias = result.medias
  }
}

await getData()

async function cancel() {
  edit.value = true
  for (const i in paramsError) {
    paramsError[i] = false
  }
  await getData()
}

async function handleContactUs() {
  if (params.text.length < 20) {
    paramsError.textError = true
  }
  if (params.address.length < 20) {
    paramsError.addressError = true
  }
  if (params.phoneNumberCompany.length < 4) {
    paramsError.phoneNumberCompanyError = true
  }
  if (params.mobileNumber.length != 11 || params.mobileNumber[0] != 0 || params.mobileNumber[1] != 9) {
    paramsError.mobileNumberError = true
  }
  if (params.emailAddress.length < 10 || !params.emailAddress.includes('@')) {
    paramsError.emailAddressError = true
  }
  if (params.longAddress.length || params.latAddress.length) {
    if (params.latAddress.length < 5) {
      paramsError.latAddressError = true
    }
    if (params.longAddress.length < 5) {
      paramsError.longAddressError = true
    }
  } else {
    paramsError.latAddressError = true
    paramsError.longAddressError = true
    paramsError.locationAddressError = true
  }
  if (
      params.text.length >= 20 &&
      params.address.length >= 20 &&
      params.phoneNumberCompany.length >= 4&&
      params.emailAddress.length > 10 && params.emailAddress.includes('@') &&
      params.latAddress.length > 5 &&
      params.longAddress.length > 5&&
      params.mobileNumber.length == 11 || params.mobileNumber[0] == 0 || params.mobileNumber[1] == 9
  )
  {
    for (const i in paramsError) {
      paramsError[i] = false
    }
    const {data, status} = await useAsyncData('editData', () => $fetch(`${apiBaseUrl}/api/v1/contactus/${params.id}` , {
      method:'PUT',
      headers:{
        Authorization: `${cookie.value}`
      },
      body:JSON.stringify(params)
    }))
    if (status.value === 'success') {
      toast.success('درخواست با موفقیت ثبت شد')
      await getData()
      edit.value = true
    }
  }
}
</script>
<template>
  <section>
    <div class="container mx-auto">
      <div class="insert-contact-us flex justify-end" v-if="showBtnAddAddress">
        <button class="text-sm bg-blue-500 text-white px-3 py-1 rounded " @click="showContactusModal = true">ثبت جدید
        </button>
      </div>
      <div class="contact-us">
        <form action="" class="grid grid-cols-12 gap-3" @submit.prevent="handleContactUs">
          <div class="input-group col-span-6 flex flex-col gap-1.5">
            <label for="text"> متن :</label>
            <textarea @keydown="paramsError.textError=false" :disabled="edit" v-model.trim="params.text" class="w-full py-1.5 px-2 rounded outline-0 text-sm"
                      name=""
                      id="text" rows="3"></textarea>
            <span v-if="paramsError.textError" class="text-xs text-red-500 px-2 mt-0.5 "> متن باید بزرگتر از 20 کاراکتر باشد </span>
          </div>
          <div class="input-group col-span-6 flex flex-col gap-1.5">
            <label for="address"> آدرس :</label>
            <textarea @keydown="paramsError.addressError=false" :disabled="edit" v-model.trim="params.address"
                      class="w-full py-1.5 px-2 rounded outline-0 text-sm"
                      name="" id="address" rows="3"></textarea>
            <span v-if="paramsError.addressError" class="text-xs text-red-500 px-2 mt-0.5 "> آدرس باید بزرگتر از 20 کاراکتر باشد </span>
          </div>
          <div class="input-group col-span-4 flex flex-col gap-1.5">
            <label for="phone"> خط ثابت :</label>
            <input @keydown="paramsError.phoneNumberCompanyError=false" :disabled="edit" v-model.trim="params.phoneNumberCompany"
                   class="w-full py-1.5 px-2 rounded outline-0 text-sm"
                   type="text" id="phone">
            <span v-if="paramsError.phoneNumberCompanyError" class="text-xs text-red-500 px-2 mt-0.5 "> لطفا کادر بالا را پر کنید </span>
          </div>
          <div class="input-group col-span-4 flex flex-col gap-1.5">
            <label for="mobile"> تلفن همراه : </label>
            <input @keydown="paramsError.mobileNumberError=false" :disabled="edit" v-model.trim="params.mobileNumber"
                   class="w-full py-1.5 px-2 rounded outline-0 text-sm"
                   type="text" id="mobile">
            <span v-if="paramsError.mobileNumberError" class="text-xs text-red-500 px-2 mt-0.5 "> شماره همراه باید 11 رقمی و ابتدای آن 0 و 9 باشد </span>
          </div>
          <div class="input-group col-span-4 flex flex-col gap-1.5">
            <label for="email">ایمیل : </label>
            <input @keydown="paramsError.emailAddressError=false" :disabled="edit" v-model.trim="params.emailAddress"
                   class="w-full py-1.5 px-2 rounded outline-0 text-sm"
                   type="text" id="email">
            <span v-if="paramsError.emailAddressError" class="text-xs text-red-500 px-2 mt-0.5 "> ایمیل باید بزرگتر از 10 کارکتر و شمال @ باشد </span>
          </div>
          <div class="input-group-location flex flex-col gap-3 col-span-12">
            <div class="select-locations-inputs flex gap-3">
              <div class="flex items-center gap-1.5">
                <label for="location-two">آدرس کامل :</label>
                <input :disabled="edit" v-model.trim="locationStatus" type="radio" name="locations" id="location-two"
                       value="location-full">
              </div>
              <div class="flex items-center gap-1.5">
                <label for="location-one">طول و عرض جغرافیایی :</label>
                <input :disabled="edit" v-model.trim="locationStatus" type="radio" name="locations" id="location-one"
                       value="location-let-lang">
              </div>
            </div>
            <div class="location-full-address" v-if="locationStatus === 'location-full'">
              <div class="input-group flex flex-col gap-1.5">
                <textarea @keydown="paramsError.locationAddressError=false" :disabled="edit" placeholder="آدرس کامل" v-model.trim="params.locationAddress"
                          id="full-location"
                          class="rounded py-1.5 px-2 text-sm placeholder:text-sm outline-0"
                          rows="5"></textarea>
                <span v-if="paramsError.locationAddressError" class="text-xs text-red-500 px-2 mt-0.5 "> آدرس باید بزرگتر از 20 کاراکتر باشد </span>
              </div>
            </div>
            <div class="location-lat-long grid grid-cols-2 gap-3" v-else>
              <div class="input-group flex flex-col gap-1.5">
                <input @keydown="paramsError.latAddressError=false" :disabled="edit" placeholder="عرض جغرافیایی" v-model="params.latAddress" type="text" id="lat"
                       class="text-sm placeholder:text-sm py-1.5 px-2 rounded outline-0">
                <span v-if="paramsError.latAddressError" class="text-xs text-red-500 px-2 mt-0.5 "> لطفا کادر بالا را پر کنید </span>
              </div>
              <div class="input-group flex flex-col gap-1.5">
                <input @keydown="paramsError.longAddressError=false" :disabled="edit" placeholder="طول جغرافیایی" v-model="params.longAddress" type="text" id="long"
                       class="text-sm placeholder:text-sm py-1.5 px-2 rounded outline-0">
                <span v-if="paramsError.longAddressError" class="text-xs text-red-500 px-2 mt-0.5 "> لطفا کادر بالا را پر کنید </span>
              </div>
            </div>
          </div>
          <div class="input-group flex  items-center gap-3 col-span-12 ">
            <button @click="edit = false" v-if="edit" id="edit"
                    class="py-1.5 px-3 text-sm rounded bg-blue-500 text-white">ویرایش
            </button>
            <div v-else class="flex gap-2 ">
              <button id="submit" type="submit" class="py-1.5 px-3 text-sm rounded bg-green-500 text-white">ثبت</button>
              <button @click="cancel" id="cancel" class="py-1.5 px-3 text-sm rounded bg-red-500 text-white">لفو
              </button>
            </div>
          </div>
        </form>
      </div>
    </div>
    <transition name="contact-us">
      <dashboard-modal-contact-us @sendData="sendData" @close="showContactusModal=false" v-if="showContactusModal"/>
    </transition>
  </section>

</template>
<style scoped>
.contact-us-enter-active {
  animation: show-modal 0.3s ease forwards;
}

.contact-us-leave-active {
  animation: show-modal 0.3s ease forwards reverse;
}

@keyframes show-modal {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
</style>