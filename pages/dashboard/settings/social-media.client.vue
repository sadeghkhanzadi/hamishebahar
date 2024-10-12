<script setup>
const {public: {apiBaseUrl}} = useRuntimeConfig()
const cookie = useCookie('jwt')
const showModal = ref(false)
const showEditModal = ref(false)
const showRemoveModal = ref(false)
const itemDeleteID = ref()
const editItemData = ref()
const socialApp = ref([])
const icons = ref(null)
getData()
getIcons()

async function getIcons() {
  const {data, status, error} = await useAsyncData('fetchIcon', () => $fetch(`${apiBaseUrl}/api/v1/icon`, {
    headers: {authorization: `${cookie.value}`},
  }))
  if (status.value === 'success') {
    icons.value = data.value.result.content
    console.log(icons.value)
  }
  if (error.value) {
    console.log(error.value)
  }
}

async function getData() {
  const {data, status, error} = await useAsyncData('fetchLinks', () => $fetch(`${apiBaseUrl}/api/v1/view/links`))
  if (status.value === 'success') {
    socialApp.value = data.value.result.content
    console.log(socialApp.value)
    showModal.value = false
  }
  if (error.value) {
    console.log(error.value)
  }
}

async function sendDate(obj) {
  const {status, error} = await useAsyncData('postingSocial', () => $fetch(`${apiBaseUrl}/api/v1/insert/links`, {
    method: 'POST',
    headers: {authorization: `${cookie.value}`},
    body: JSON.stringify(obj)
  }))
  if (status.value == 'success') {
    console.log(status.value)
    showModal.value = false
    getData()
  }
  if (error.value) {
    console.log(error.value)
  }
}

async function editData(obj) {
  console.log(obj)
  await useAsyncData('postingIcon', () => $fetch(`${apiBaseUrl}/api/v1/links/${obj.id}`, {
        method: 'PUT',
        headers: {authorization: `${cookie.value}`},
        body: JSON.stringify(obj),
        onResponse({response}) {
          if (response.status == 200) {
            showEditModal.value = false
            getData()
          } else {
            console.log(response)
          }
        }
      }
  ))
}

function edit(item) {
  editItemData.value = item
  showEditModal.value = true
}

function remove(id) {
  itemDeleteID.value = id
  showRemoveModal.value = true
}

async function removeRequest(result) {
  if (result) {
    const {
      status,
      error
    } = await useAsyncData('removeSocial', () => $fetch(`${apiBaseUrl}/api/v1/links/${itemDeleteID.value}`, {
      method: 'DELETE',
      headers: {authorization: `${cookie.value}`},
    }))
    if (status.value == 'success') {
      console.log(status.value)
      showRemoveModal.value = false
      getData()
    }
    if (error.value) {
      console.log(error.value)
    }
  } else {
    showRemoveModal.value = false
  }
}
</script>

<template>
  <section>
    <div class="container flex flex-col gap-y-5">
      <div class="add-icon flex justify-end">
        <button role="button" @click="showModal=true"
                class="bg-blue-500 text-sm text-white flex items-center gap-1.5 rounded px-3 py-1.5">
          ثبت لینک جدید
        </button>
      </div>
      <div v-if="socialApp.length" class="grid grid-cols-12 gap-3">
        <div v-for="(item , index) in socialApp" :key="index"
             class="flex flex-col items-center justify-center bg-blue-300 rounded p-3 gap-0.5  h-full w-full">
          <figure class="flex flex-col items-center justify-center ">
            <span>
              <i v-if="item.icon" :class="`${item.icon.icon}`"
                 :style="{color:item.icon.color , backgroundColor:item.icon.backgroundColor }"
                 class="text-3xl"></i>
            </span>
            <figcaption class="text-xs mt-1.5 " v-if="item.name && item.name.length">{{ item.name }}</figcaption>
          </figure>
          <div class="flex flex-col items-center  justify-center gap-1">
            <button class="text-xs bg-blue-500 text-white px-3 py-1 rounded w-full" @click="edit(item)">ویرایش</button>
            <button class="text-xs bg-red-500 text-white p-1 rounded  w-full" @click="remove(item.id)">حذف</button>
          </div>
        </div>
      </div>
      <div>
      </div>
    </div>
  </section>
  <transition name="show-icon">
    <dashboard-modal-social-media :icons="icons" @sendData="sendDate" v-if="showModal" @close="showModal = false"/>
  </transition>
  <transition name="show-edit-icon">
    <dashboard-modal-edit-social-media @sendData="editData" :edit="editItemData" :icons="icons" v-if="showEditModal"
                                       @close="showEditModal = false"/>
  </transition>
  <transition>
    <dashboard-modal-alert v-if="showRemoveModal" @result="removeRequest"/>
  </transition>
</template>

<style scoped lang="scss">
.show-icon-leave-active {
  animation: show-modal 0.35s ease reverse forwards;
}

.show-icon-enter-active {
  animation: show-modal 0.35s ease forwards;
}

.show-eidt-icon-leave-active {
  animation: show-modal 0.35s ease reverse forwards;
}

.show-eidt-icon-enter-active {
  animation: show-modal 0.35s ease forwards;
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