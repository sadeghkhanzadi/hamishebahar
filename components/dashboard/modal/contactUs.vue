<script setup>
const emit = defineEmits(['close' , 'sendData'])
const params = reactive({
  text: "",
  address: "",
  phone: "",
  mobile: "",
  email: "",
  lat: "",
  long: "",
  location: "",
})
const paramsError = reactive({
  text: false,
  address: false,
  phone: false,
  mobile: false,
  email: false,
  lat: false,
  long: false,
  location: false,
})
const locationStatus = ref('location-full')

function handleContactUs(e) {
  if (!params.text.length) {
        paramsError.text=true
  }
  if (!params.address.length) {
      paramsError.address=true
  }
  if (params.phone.length !== 11) {
      paramsError.phone=true
  }
  if (params.mobile.length !== 11 && params.mobile[0] != 0 && params.mobile[1] != 9) {
      paramsError.mobile=true
  }
  if (!params.email.includes('@')) {
      paramsError.email=true
  }
  if (locationStatus.value ==='location-full') {
    if (!params.location.length) {
      paramsError.location=true
    }
  }
  else {
    if (!params.lat.length) {
      paramsError.lat=true
    }
    if (!params.long.length) {
      paramsError.long=true
    }
  }

  if (
      params.text.length&&
      params.address.length&&
      params.phone.length&&
      params.mobile.length&&
      locationStatus.value ==='location-full' ? params.location.length : params.long.length&&params.lat.length   &&
      params.email.length
  ){
    emit('sendData' , params)
  }
}
</script>

<template>
  <div
      class="backdrop flex justify-center items-center fixed w-full h-full inset-0 bg-[rgba(0,0,0,0.15)] z-10 p-3 overflow-y-auto"
      @click.self="emit('close')">
    <div class="modal-content  p-5 bg-white shadow rounded w-full md:w-1/2 lg:w-1/3 2xl:w-1/2  ">
      <form action="" class="grid grid-cols-12 gap-3" @submit.prevent="handleContactUs">
        <div class=" input-group col-span-12 md:col-span-6  2xl:col-span-6 flex flex-col gap-1.5">
          <label class="text-sm" for="text"> متن :</label>
          <textarea v-model="params.text"
                    class="w-full resize-none py-1 px-2 border border-gray-200 rounded outline-0 text-sm" name=""
                    id="text" cols="30" rows="3" placeholder="متن"></textarea>
          <span v-if="paramsError.text" class="pr-0.5 text-red-500 text-xs">لطفا کادر بالا را پر کنید</span>
        </div>
        <div class=" input-group col-span-12 md:col-span-6  2xl:col-span-6 flex flex-col gap-1.5">
          <label class="text-sm" for="address"> آدرس :</label>
          <textarea v-model="params.address"
                    class="w-full resize-none py-1 px-2 border border-gray-200 rounded outline-0 text-sm" name=""
                    id="address" cols="30" rows="3" placeholder="آدرس"></textarea>
          <span v-if="paramsError.address" class="pr-0.5 text-red-500 text-xs">لطفا کادر بالا را پر کنید</span>
        </div>
        <div class=" input-group col-span-12 md:col-span-6  2xl:col-span-4 flex flex-col gap-1.5">
          <label class="text-sm" for="phone"> خط ثابت :</label>
          <input v-model="params.phone" placeholder="خط ثابت" class="w-full py-1 px-2 border border-gray-200 rounded outline-0 text-sm"
                 type="text" id="phone">
          <span v-if="paramsError.phone" class="pr-0.5 text-red-500 text-xs">لطفا کادر بالا را پر کنید</span>
        </div>
        <div class=" input-group col-span-12 md:col-span-6  2xl:col-span-4 flex flex-col gap-1.5">
          <label class="text-sm" for="mobile"> تلفن همراه : </label>
          <input v-model="params.mobile" placeholder="تلفن همراه" class="w-full py-1 px-2 border border-gray-200 rounded outline-0 text-sm"
                 type="text" id="mobile">
          <span v-if="paramsError.mobile" class="pr-0.5 text-red-500 text-xs">لطفا کادر بالا را پر کنید</span>
        </div>
        <div class=" input-group col-span-12 md:col-span-6  2xl:col-span-4 flex flex-col gap-1.5">
          <label class="text-sm" for="email">ایمیل : </label>
          <input v-model="params.email" placeholder="ایمیل" class="w-full py-1 px-2 border border-gray-200 rounded outline-0 text-sm"
                 type="text" id="email">
          <span v-if="paramsError.email" class="pr-0.5 text-red-500 text-xs">لطفا کادر بالا را پر کنید</span>
        </div>
        <div class=" input-group-location flex flex-col gap-3 col-span-12">
          <div class="select-locations-inputs flex gap-3">
            <div class="flex items-center gap-1.5">
              <label class="text-sm" for="location-two">آدرس کامل :</label>
              <input v-model="locationStatus" type="radio" name="locations" id="location-two" value="location-full">
            </div>
            <div class="flex items-center gap-1.5">
              <label class="text-sm" for="location-one">طول و عرض جغرافیایی :</label>
              <input v-model="locationStatus" type="radio" name="locations" id="location-one" value="location-lat-lang">
            </div>
          </div>
          <div class="location-full-address" v-if="locationStatus === 'location-full'">
            <div class=" input-group  flex flex-col gap-1.5">
              <textarea placeholder="آدرس کامل" v-model="params.location" name="" id="full-location" cols="30"
                        class="rounded py-1.5 px-2 text-sm placeholder:text-sm outline-0 border border-gray-200" rows="5"></textarea>
              <span v-if="paramsError.location" class="pr-0.5 text-red-500 text-xs">لطفا کادر بالا را پر کنید</span>
            </div>
          </div>
          <div class="location-lat-long grid grid-cols-2 gap-3" v-else>
            <div class=" input-group  flex flex-col gap-1.5">
              <input placeholder="عرض جغرافیایی" v-model="params.lat" type="text" id="lat"
                     class="text-sm placeholder:text-sm py-1 px-2 rounded outline-0 border border-gray-200">
              <span v-if="paramsError.lat" class="pr-0.5 text-red-500 text-xs">لطفا کادر بالا را پر کنید</span>
            </div>
            <div class=" input-group flex flex-col gap-1.5">
              <input placeholder="طول جغرافیایی" v-model="params.long" type="text" id="long"
                     class="text-sm placeholder:text-sm py-1 px-2 rounded outline-0 border border-gray-200">
              <span v-if="paramsError.long" class="pr-0.5 text-red-500 text-xs">لطفا کادر بالا را پر کنید</span>
            </div>
          </div>
        </div>
        <div class=" input-group  flex  items-center gap-3 col-span-12 ">
          <button id="submit" class="py-1.5 px-3 text-sm rounded bg-green-500 text-white">ثبت</button>
        </div>
      </form>
    </div>
  </div>
</template>

<style scoped lang="scss">
:is(input , textarea):focus {
  border: 2px solid rgba(0, 0, 255, 0.2);
  box-shadow: 0 0 3px rgba(0, 0, 255, 0.2);
  margin: 0;
}
</style>