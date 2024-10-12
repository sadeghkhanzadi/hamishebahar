<script setup lang="ts">
import {useDashboardCoursesStore} from "~/store/dashboard/courses";
const route = useRoute()
const params = reactive({
  showSelectMedia: false
})
const store = useDashboardCoursesStore()
store.fetchingCourseItem(route.params.id)
const data = computed(()=>store.getCourseItem)
const courseParams = reactive({
  id: "",
  courseCode:"",
  title: "",
  text: "",
  startTime: "",
  endTime: "",
  coste: "",
  is_active: true,
  teacher: {id: ""},
  category: {id: ""},
  medias: [""],
})

watch(()=>data.value , ()=>{
  if (Object.keys(data.value).length){
    courseParams.id= data.value.id
    courseParams.courseCode=data.value.courseCode
    courseParams.title= data.value.title
    courseParams.text= data.value.text
    courseParams.startTime= data.value.startTime
    courseParams.endTime= data.value.endTime
    courseParams.coste= data.value.coste
    courseParams.is_active= data.value.is_active
    courseParams.teacher = data.value.teacher
    courseParams.category =data.value.teacher
    courseParams.medias = data.value.medias
  }
})
const courseParamsError = reactive({
  courseCode: false,
  title: false,
  text: false,
  startTime: false,
  endTime: false,
  coste: false,
  is_active: false,
  teacher: false,
  category: false,
  medias: false,
})

function addTeacher(teacher) {
  courseParams.teacher.id = teacher
  console.log(teacher)
}

function addCategory(category) {
  courseParams.category.id = category
}

function addMedia(media) { // اینجا داره آرایه برمیگردونه ما باید برسی کنیم که اگر چند تا عکس با هم امد باز بیایم توی عکس ها حلقه بزنیم تا اگر تکراری داشت تشخیص بدیم
  if (
      courseParams.medias.find(item => item.id == media.id)) {
    return false
  } else {
    courseParams.medias.push()
  }
  console.log(media)
}

function handleForm() {
  if (courseParams.courseCode.length < 1) {
    courseParamsError.courseCode = true
  }
  if (courseParams.title.length < 1) {
    courseParamsError.title = true
  }
  if (courseParams.courseCode.length < 1) {
    courseParamsError.courseCode = true
  }
  if (courseParams.text.length < 20) {
    courseParamsError.text = true
  }
  if (courseParams.coste.length < 1) {
    courseParamsError.coste = true
  }
  if (courseParams.teacher.id.length < 1) {
    courseParamsError.teacher = true
  }
  if (courseParams.category.id.length < 1) {
    courseParamsError.category = true
  }
  if (courseParams.startTime.length == 0) {
    courseParamsError.startTime = true
  }
  if (courseParams.endTime.length == 0) {
    courseParamsError.endTime = true
  }
  if (
      courseParams.startTime.length != 0 &&
      courseParams.category.id.toString() >= 1 &&
      courseParams.teacher.id.toString().length >= 1 &&
      courseParams.coste.length >=1 &&
      courseParams.text.length >= 20 &&
      courseParams.title.length >= 1
  ) {
    async function sendData(body:object){
      await store.sendData(body)
    }
  }
}
watch(()=>route , ()=>{
  if(route.params.id){
    store.fetchingCourseItem(route.params.id)
  }
})
</script>

<template>
  <section class="p-5">
    <div class="course-content p-5 bg-white rounded mx-auto overflow-y-auto">
      <form action="" @submit.prevent="handleForm">
        <div class="form-group-container md:flex gap-5 w-full justify-between items-baseline mt-3">
          <div class="form-group w-full mt-3">
            <label class="text-sm" :for="`courseCode`">شماره کلاس :</label>
            <input v-model="courseParams.courseCode"
                   placeholder="شماره کلاس"
                   @change="courseParamsError.courseCode = false"
                   class="text-sm placeholder:text-sm border border-[rgba(0,0,0,0.1)] px-2 py-1.5 outline-0 rounded w-full mt-2 "
                   :id="`courseCode`" type="text">
            <span v-if="courseParamsError.courseCode" class="text-xs text-red-500 mt-0.5 px-2"> لطفا کادر بالا را پر کنید</span>
          </div>
          <div class="form-group w-full mt-3">
            <label class="text-sm" :for="`title`">عنوان کلاس :</label>
            <input v-model="courseParams.title"
                   placeholder="عنوان کلاس"
                   @change="courseParamsError.title = false"
                   class="text-sm placeholder:text-sm border border-[rgba(0,0,0,0.1)] px-2 py-1.5 outline-0 rounded w-full mt-2 "
                   :id="`title`" type="text">
            <span v-if="courseParamsError.title"
                  class="text-xs text-red-500 mt-0.5 px-2"> لطفا کادر بالا را پر کنید</span>
          </div>
        </div>
        <div class="form-group mt-3">
          <label class="text-sm" :for="`description`"> توصیحات کلاس :</label>
          <textarea v-model="courseParams.text"
                    placeholder="توضیحات کلاس"
                    @change="courseParamsError.text = false"
                    class="text-sm placeholder:text-sm border border-[rgba(0,0,0,0.1)] px-2 py-1.5 outline-0 rounded w-full mt-2 "
                    :id="`description`" rows="3"></textarea>
          <span v-if="courseParamsError.text" class="text-xs text-red-500 px-2 relative -top-1.5"> توضیحات باید بیشتر از 20 کارکتر باشد </span>
        </div>
        <div class="form-group w-full mt-3">
          <label class="text-sm" :for="`cost`">هزینه کلاس :</label>
          <input v-model="courseParams.coste"
                 placeholder="هزینه کلاس"
                 @change="courseParamsError.coste = false"
                 class="text-sm placeholder:text-sm border border-[rgba(0,0,0,0.1)] px-2 py-1.5 outline-0 rounded w-full mt-2 "
                 :id="`cost`" type="text">
          <span v-if="courseParamsError.coste"
                class="text-xs text-red-500 mt-0.5 px-2"> لطفا کادر بالا را پر کنید</span>
        </div>
        <div class="grid md:grid-cols-2 gap-5 mt-3">
          <div class="add-teachers mt-4">
            <h5 class="text-sm rounded mb-2 w-full"> اضافه کردن معلم :</h5>
            <dashboard-select-option @send-data="addTeacher"/>
            <span v-if="courseParamsError.teacher"
                  class="text-xs text-red-500 mt-0.5 px-2"> لطفا یک مورد را انتخاب کنید</span>
          </div>
          <div class="add-teachers mt-4">
            <h5 class="text-sm rounded mb-2 w-full"> اضافه کردن دسته بندی : </h5>
            <dashboard-select-option @send-data="addCategory"/>
            <span v-if="courseParamsError.category" class="text-xs text-red-500 mt-0.5 px-2"> لطفا یک مورد را انتخاب کنید</span>
          </div>
        </div>
        <div class="form-group-container md:flex gap-5 w-full justify-between items-baseline mt-3 ">
          <div class="form-group mt-3 w-full md:w-1/2">
            <label class="text-sm" :for="`startTime`">زمان شروع کلاس :</label>
            <div class="mt-2">
              <date-picker
                  type="datetime"
                  format="YYYY-MM-DD"
                  display-format="jYYYY-jMM-jDD"
                  v-model="courseParams.startTime"
                  @change="courseParamsError.startTime = false"
              />
              <span v-if="courseParamsError.startTime" class="text-xs text-red-500 mt-0.5 px-2"> لطفا کادر بالا را پر کنید</span>
            </div>
          </div>
          <div class="form-group mt-3 w-full md:w-1/2" v-if="courseParams.startTime.length">
            <label class="text-sm" :for="`endTime`">زمان پایان کلاس :</label>
            <div class="mt-2">
              <date-picker
                  type="datetime"
                  format="YYYY-MM-DD"
                  :min="courseParams.startTime"
                  display-format="jYYYY-jMM-jDD"
                  v-model="courseParams.endTime"
                  @change="courseParamsError.endTime = false"
              />
              <span v-if="courseParamsError.endTime"
                    class="text-xs text-red-500 mt-0.5 px-2"> لطفا کادر بالا را پر کنید</span>
            </div>
          </div>
        </div>
        <div class="add-media mt-5">
          <button @click.prevent="params.showSelectMedia = true"
                  class="text-sm text-white py-2 bg-blue-500 mb-2 w-full rounded "> اضافه کردن تصویر و فایل
          </button>
        </div>
        <button class="text-sm text-white py-2 bg-green-500 mb-2 w-full rounded mt-5 "> ثبت اطلاعات</button>
      </form>
    </div>
    <transition name="show-modal">
      <dashboard-card-select-media v-if="params.showSelectMedia" @selectFile="addMedia"
                                   @close="params.showSelectMedia = false" class="z-10"/>
    </transition>
  </section>

</template>

<style scoped lang="scss">
input, textarea {
  border: 1px solid rgba(0, 0, 0, 0.15);
  resize: none;
  outline: 0;

  &:focus {
    border: 2px solid rgba(0, 0, 255, 0.2);
    box-shadow: 0 0 3px rgba(0, 0, 255, 0.2);
  }
}

.show-modal-enter-active {
  animation: showModal ease 0.3s forwards;
}

.show-modal-leave-active {
  animation: showModal ease 0.3s forwards reverse;
}

@keyframes showModal {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
</style>
