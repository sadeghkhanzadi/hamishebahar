<script setup lang="ts">
const showNewTeachersForm = ref(false)
const showEditTeachersForm = ref(false)
const showTeacher = ref(false)
const showTeacherInfo = ref([])
const showAlert = ref(false)
const alertData = ref("")
const editTeacherId = ref()
const router = useRouter()
const route = useRoute()
const apiBaseUrl = useState('apiBaseUrl').value
const cookie = useCookie('jwt')
const teachers = ref([])
const pendingRequest = ref(false)
getNews()
const tableBody = computed(() => teachers.value.content)
const thead = [
  {
    name: 'ردیف'
  },
  {
    name: 'نام'
  },
  {
    name: 'نام خانوادگی'
  },
  {
    name: 'سن'
  },
  {
    name: 'سابقه'
  },
  {
    name: 'عنوان شغلی'
  },
  {
    name: 'شماره تماس'
  },
  {
    name: 'کد پرسنلی'
  },
  {
    name: 'وضعیت'
  },
  {
    name: 'عملیات'
  },
]

async function getNews(jobTitle: null, firstName: null, lastName: null,size:null) {
  const {data, error, status} = await useFetch(() => `${apiBaseUrl}/api/v1/teacher`, {
    headers: {
      Authorization: `${cookie.value}`
    },
    query: {
      firstName,
      lastName,
      jobTitle,
      size
    }
  })
  if (error.value) {
    console.log(error.value)
  }
  if (status.value === 'success') {
    console.log(data.value)
    teachers.value = data.value.result
  }
}

function searchInNews(data:object) {
  getNews(data.jobTitle, data.firstName, data.LastName, data.size)
  let query = {
    ...route.query,
    size: data.size || 10,
    firstName: data.firstName || null,
    LastName: data.LastName || null,
    jobTitle: data.jobTitle || null
  }
  for (let i in query) {
    query[i] === null ? delete query[i] : ""
  }
  router.push({path: route.fullPath, query: query})
}
async function postNewTeachers(information: object , theme:string) {
  console.log(information , theme)
  pendingRequest.value = true
  const imgId = []
  information.medias.forEach(item => {
    imgId.push({id: item.id})
  })
  const json = {
    firstName: information.name,
    lastName: information.lastName,
    age: information.age,
    jobTitle: information.jobTitle,
    is_active: information.is_active,
    documentFiles:imgId,
    teacherCode:information.personalCode,
    phoneNumber:information.phoneNumber,
    workExperience : information.workExperience,
    nationalCode: information.nationalCode
  }
  if (theme === 'create' && theme != 'edit'){
    const { error, status} = await useFetch(()=>`${apiBaseUrl}/api/v1/teacher`, {
      method: 'POST',
      body:JSON.stringify(json),
      headers:{
        Authorization:`${cookie.value}`
      }
    })
    if (error.value){
      console.log(error.value)
    }
    if (status.value === 'success'){
      pendingRequest.value =false
      showNewTeachersForm.value = false
      getNews()
    }
  }
  if (theme === 'edit' && theme != 'create') {
    json.id = editTeacherId.value
    const { error, status} = await useFetch(()=>`${apiBaseUrl}/api/v1/teacher/${editTeacherId.value}`, {
      method: 'PUT',
      body:JSON.stringify(json),
      headers:{
        Authorization:`${cookie.value}`
      }
    })
    if (error.value){
      console.log(error.value)
    }
    if (status.value === 'success'){
      pendingRequest.value = false
      showEditTeachersForm.value = false
      getNews()
    }
  }
}
onMounted(() => {
  watch(() => showNewTeachersForm.value, () => {
    if (showNewTeachersForm.value) {
      document.body.classList.add('overflow-hidden')
    } else {
      document.body.classList.remove('overflow-hidden')
    }
  })
  watch(() => showTeacherInfo.value, () => {
    if (showTeacherInfo.value) {
      document.body.classList.add('overflow-hidden')
    } else {
      document.body.classList.remove('overflow-hidden')
    }
  })
})

function showTeacherModalInfo(info:any){
  showTeacherInfo.value = info
  showTeacher.value = true
}
function removeTeacher(teacherInfo:object){
  const name:string = `${teacherInfo.name} ${teacherInfo.lastName}`
  alertData.value = {name:name , id : teacherInfo.id}
  console.log(alertData.value)
  showAlert.value = true
}
function resultRemoveTeacher(info:boolean){
  if (info){
    removeNews(alertData.value.id)
  }
  else{
    showAlert.value =false
  }
}
async function removeNews(id:number|string){
  console.log(id)
  const { error, status} = await useFetch(()=>`${apiBaseUrl}/api/v1/teacher/${id}`, {
    method: 'DELETE',
    headers:{
      Authorization:`${cookie.value}`
    }
  })
  if (error.value){
    console.log(error.value)
  }
  if (status.value === 'success'){
    alertData.value = ""
    showAlert.value = false
    getNews()
  }
}
function requestEditTeacher (id:number|string){ /*درخواست از سمت جدول میاد تا فرم رو باز کنه و دیتا رو براش ارسال کنه */
  editTeacherId.value = id
  showEditTeachersForm.value = true
}

</script>

<template>
  <section class="flex flex-col gap-y-5">
    <section>
      <lazy-dashboard-search theme="teachers" @search="searchInNews"/>
    </section>
    <section>
      <div class="flex">
        <button @click="showNewTeachersForm = true" class="bg-blue-500 text-white px-3 py-1.5 rounded mr-auto add-news">
          ثبت معلم
          جدید
        </button>
      </div>
    </section>
    <section>
      <lazy-dashboard-table-teachers title="لیست همه معلم ها" v-if="tableBody && tableBody.length" @delete="removeTeacher" @edit="requestEditTeacher" @show="showTeacherModalInfo" :thead="thead" :tbody="tableBody"/>
    </section>
    <section >
      <lazy-dashboard-teachers-newTeacherForm v-if="showNewTeachersForm" @sendData="postNewTeachers" :create="true"  @close="showNewTeachersForm = false" event="ثبت نام معلم"/><!--for Create Teachers-->
      <lazy-dashboard-teachers-newTeacherForm v-if="showEditTeachersForm" @sendData="postNewTeachers" :edit="true" :get="editTeacherId" @close="showEditTeachersForm = false" event="ویرایش معلم"/><!--for Edit Teachers-->
    </section>
    <section>
      <lazy-dashboard-show-item @close="showTeacher = false" title=" نمایش جزئیات معلم" :data="showTeacherInfo" v-if="showTeacher && showTeacherInfo " />
    </section>
    <section>
      <lazy-dashboard-modal-alert
          v-if="showAlert"
          @result="resultRemoveTeacher"
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
