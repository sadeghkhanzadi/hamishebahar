<script setup lang="ts">
const {public: {apiBaseUrl}} = useRuntimeConfig()
const showQuestionModal = ref(false)
const showAlertModal = ref(false)
const cookie = useCookie('jwt')
const dataQuestions = ref("")
const removeId = ref(null)
const getQuestions = async ():void => {
  const {data, status} = await useFetch(`${apiBaseUrl}/api/v1/view/question`)
  if (status.value === 'success') {
    dataQuestions.value = data.value.result
  }
}
getQuestions()
const sendData = async (objData: object) => {
  const {status} = await useFetch(`${apiBaseUrl}/api/v1/insert/question`, {
    method: 'POST',
    headers: {
      authorization: `${cookie.value}`,
    },
    body: JSON.stringify(objData),
  })
  if (status.value === 'success') {
    showQuestionModal.value = false
    getQuestions()
  }
}
const removeQuestion =  (id: number) => {
  showAlertModal.value = true
  removeId.value = id
}
const alertModalResult = async (result: boolean) => {
  if(result){
    const {status} = await useFetch(`${apiBaseUrl}/api/v1/question/${removeId.value}`, {
      method: 'DELETE',
      headers: {
        authorization: `${cookie.value}`,
      },
    })
    if (status.value === 'success') {
      getQuestions()
    }
    showAlertModal.value = false
  }
  else{
    showAlertModal.value = false
  }
}
const changeQuestion = async (obj:object) => {
  const {status} = await useFetch(`${apiBaseUrl}/api/v1/question/${obj.id}`, {
    method: 'PUT',
    headers: {
      authorization: `${cookie.value}`,
    },
    body: JSON.stringify(obj),
  })
  if (status.value === 'success') {
    getQuestions()
  }
}
</script>

<template>
  <section class="p-5 grid gap-3">
    <div class="search"></div>
    <div class="new-question-section flex justify-end ">
      <button @click="showQuestionModal=true"
              class="bg-blue-500 text-white text-sm p-2 rounded shadow-lg transform active:scale-95 ">ثبت سوال جدید
      </button>
    </div>
    <div class="content grid gap-2 md:grid-cols-2">
      <dashboard-settings-questions v-if="dataQuestions && dataQuestions.length" v-for="(item , index) in dataQuestions"
                                    :key="index" :index="index" :data="item" @sendData="changeQuestion"
                                    @remove="removeQuestion"/>
    </div>
  </section>
  <transition name="show-get-question">
    <dashboard-modal-new-question @sendData="sendData" @close="showQuestionModal=false" v-if="showQuestionModal"/>
  </transition>
  <transition name="show-alert-modal">
    <dashboard-modal-alert v-if="showAlertModal" @result="alertModalResult"/>
  </transition>
</template>

<style scoped lang="scss">
.show-get-question-enter-active {
  animation: show-modal 0.35s ease;
}

.show-get-question-leave-active {
  animation: show-modal 0.35s ease reverse;
}

.show-alert-modal-enter-active {
  animation: show-modal 0.35s ease;
}

.show-alert-modal-leave-active {
  animation: show-modal 0.35s ease reverse;
}

@keyframes show-modal {
  from {
    opacity: 0;
  }
  to {
    opacity: 1
  }
}
</style>