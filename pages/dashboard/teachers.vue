<script setup lang="ts">
import {useDashboardTeachersStore} from "~/store/dashboard/teachers";

const store = useDashboardTeachersStore()
store.fetchingTeachers()
const data  = computed(()=>store.getTeachers)
const showNewTeachersForm = ref(false)
const showTeacher = ref(false)
const showTeacherInfo = ref([])
const alertData = ref("")
const editTeacherId = ref()
const router = useRouter()
const route = useRoute()

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


function searchInNews(data:object) {
  store.fetchingTeachers(data.jobTitle, data.firstName, data.LastName, data.size)
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
async function postNewTeachers(information: object) {
  await store.postNewTeachers(information)
}
async function editeTeachers(information: object) {
  await store.editeTeachers(information , editTeacherId.value)
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
  store.showAlert = true
}
function resultRemoveTeacher(info:boolean){
  if (info){
    store.removeTeacherItem(alertData.value.id)
  }
  else{
    store.showAlert =false
  }
}

function requestEditTeacher (id:number|string){ /*درخواست از سمت جدول میاد تا فرم رو باز کنه و دیتا رو براش ارسال کنه */
  editTeacherId.value = id
  store.showEditTeachersForm= true
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
      <lazy-dashboard-table-teachers title="لیست همه معلم ها" v-if="data && data.length" @delete="removeTeacher" @edit="requestEditTeacher" @show="showTeacherModalInfo" :thead="thead" :tbody="data"/>
    </section>
    <section >
      <lazy-dashboard-teachers-newTeacherForm v-if="showNewTeachersForm" @sendData="postNewTeachers" :create="true"  @close="showNewTeachersForm = false" event="ثبت نام معلم"/><!--for Create Teachers-->
      <lazy-dashboard-teachers-newTeacherForm v-if="store.showEditTeachersForm"  :edit="true" @edit="editeTeachers" :get="editTeacherId" @close="store.showEditTeachersForm = false" event="ویرایش معلم"/><!--for Edit Teachers-->
    </section>
    <section>
      <lazy-dashboard-show-item @close="showTeacher = false" title=" نمایش جزئیات معلم" :data="showTeacherInfo" v-if="showTeacher && showTeacherInfo " />
    </section>
    <section>
      <lazy-dashboard-modal-alert
          v-if="store.showAlert"
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
