<script setup lang="ts">
const showNewStudentForm = ref(false)
const showEditTeachersForm = ref(false)
const showStudent = ref(false)
const showStudentsInfo = ref([])
const showAlert = ref(false)
const alertData = ref("")
const editStudentId = ref()
const router = useRouter()
const route = useRoute()
const apiBaseUrl = useState('apiBaseUrl').value
const cookie = useCookie('jwt')
const students = ref([])
const pendingRequest = ref(false)
getNews()
const tableBody = computed(() => students.value.content)
const thead = [
  {
    name: 'ردیف'
  },
  {
    name: 'کد دانش آموزی'
  },
  {
    name: 'نام'
  },
  {
    name: 'نام خانوادگی'
  },
  {
    name: 'کدملی'
  },
  {
    name: 'شماره تماس'
  },
  {
    name: 'شماره تماس اضطراری'
  },
  {
    name: 'سن'
  },
  {
    name: 'وضعیت'
  },
  {
    name: 'عملیات'
  },
]
async function getNews(phoneNumber: null, nationalCode: null, studentCode: null, age: null, size: null,) {
  const {data, error, status} = await useFetch(() => `${apiBaseUrl}/api/v1/student`, {
    headers: {
      Authorization: `${cookie.value}`
    },
    query: {
      phoneNumber,
      nationalCode,
      studentCode,
      age,
      size
    }
  })
  if (error.value) {
    console.log(error.value)
  }
  if (status.value === 'success') {
    console.log(data.value)
    students.value = data.value.result
  }
}

function searchInNews(data) {
  getNews(data.phoneNumber, data.nationalCode, data.studentCode, data.age, data.size)
  let query = {
    ...route.query,
    size: data.size || 10,
    nationalCode: data.nationalCode || null,
    phoneNumber: data.phoneNumber || null,
    studentCode: data.studentCode || null,
    age: data.age || null
  }
  for (let i in query) {
    query[i] === null ? delete query[i] : ""
  }
  router.push({path: route.fullPath, query: query})
}

async function postNewStudent(information: object, theme: string) {
  console.log(information, theme)
  pendingRequest.value = true
  const imgId = []
  information.medias.forEach(item => {
    imgId.push({id: item.id})
  })
  const json = {
    first_name: information.name,
    last_name: information.lastName,
    studentAge: information.studentAge,
    emergencyPhoneNumber: information.emergencyPhoneNumber,
    is_active: information.is_active,
    documentFiles: imgId,
    studentCode: information.studentCode,
    phoneNumber: information.phoneNumber,
    importantDetails: information.importantDetails,
    nationalCode: information.nationalCode,
    specialDisease: information.specialDisease
  }
  if (theme === 'create') {
    const {error, status} = await useFetch(() => `${apiBaseUrl}/api/v1/student`, {
      method: 'POST',
      body: JSON.stringify(json),
      headers: {
        Authorization: `${cookie.value}`
      }
    })
    if (error.value) {
      console.log(error.value)
    }
    if (status.value === 'success') {
      pendingRequest.value = false
      showNewStudentForm.value = false
      getNews()
    }
  }
  if (theme === 'edit') {
    json.id = editStudentId.value
    const {error, status} = await useFetch(() => `${apiBaseUrl}/api/v1/student/${editStudentId.value}`, {
      method: 'PUT',
      body: JSON.stringify(json),
      headers: {
        Authorization: `${cookie.value}`
      }
    })
    if (error.value) {
      console.log(error.value)
    }
    if (status.value === 'success') {
      pendingRequest.value = false
      showEditTeachersForm.value = false
      getNews()
    }
  }
}

onMounted(() => {
  watch(() => showNewStudentForm.value, () => {
    if (showNewStudentForm.value) {
      document.body.classList.add('overflow-hidden')
    } else {
      document.body.classList.remove('overflow-hidden')
    }
  })
  watch(() => showStudentsInfo.value, () => {
    if (showStudentsInfo.value) {
      document.body.classList.add('overflow-hidden')
    } else {
      document.body.classList.remove('overflow-hidden')
    }
  })
})

function showTeacherModalInfo(info: any) {
  showStudentsInfo.value = info
  showStudent.value = true
}

function removeStudent(teacherInfo: object) {
  const name: string = `${teacherInfo.name} ${teacherInfo.lastName}`
  alertData.value = {name: name, id: teacherInfo.id}
  showAlert.value = true
}

function resultRemoveStudent(info: boolean) {
  if (info) {
    requestRemoveStudent(alertData.value.id)
  } else {
    showAlert.value = false
  }
}

async function requestRemoveStudent(id: number | string) {
  console.log(id)
  const {error, status} = await useFetch(() => `${apiBaseUrl}/api/v1/student/${id}`, {
    method: 'DELETE',
    headers: {
      Authorization: `${cookie.value}`
    }
  })
  if (error.value) {
    console.log(error.value)
  }
  if (status.value === 'success') {
    alertData.value = ""
    showAlert.value = false
    getNews()
  }
}


function requestEditTeacher(id) { /*درخواست از سمت جدول میاد تا فرم رو باز کنه و دیتا رو براش ارسال کنه */
  editStudentId.value = id
  showEditTeachersForm.value = true
}



</script>

<template>
  <section class="flex flex-col gap-y-5">
    <section>
      <dashboard-search theme="students" @search="searchInNews"/>
    </section>
    <section>
      <div class="flex">
        <button @click="showNewStudentForm = true" class="bg-blue-500 text-white px-3 py-1.5 rounded mr-auto add-news">
          ثبت دانش آموز
          جدید
        </button>
      </div>
    </section>
    <section>
      <dashboard-table-students title="لیست همه دانش آموزان" v-if="tableBody && tableBody.length"
                                @delete="removeStudent" @edit="requestEditTeacher" @show="showTeacherModalInfo"
                                :thead="thead" :tbody="tableBody"/>
    </section>
    <section class="">
      <lazy-dashboard-student-newStudentForm v-if="showNewStudentForm" @sendData="postNewStudent" :create="true"
                                        @close="showNewStudentForm = false" event="ثبت نام معلم"/>
      <!--for Create Teachers-->
      <lazy-dashboard-student-newStudentForm v-if="showEditTeachersForm" @sendData="postNewStudent" :edit="true"
                                        :get="editStudentId" @close="showEditTeachersForm = false" event="ویرایش معلم"/>
<!--      for Edit Teachers-->
    </section>
    <section>
      <dashboard-show-item theme="student" @close="showStudent = false" title=" نمایش جزئیات دانش آموز"
                           :data="showStudentsInfo" v-if="showStudent && showStudentsInfo "/>
    </section>
    <section class="">
      <dashboard-modal-alert
          v-if="showAlert"
          @result="resultRemoveStudent"
          :data="alertData"
      />
    </section>
  </section>
</template>

<style scoped lang="scss">
button.add-news {
  font-size: var(--text-md);
}
</style>
