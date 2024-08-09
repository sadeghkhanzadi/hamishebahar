<script setup lang="ts">
const {theme} = defineProps(['theme'])
const emit = defineEmits(['search'])
const news = reactive({
  title: "",
  startTime: "",
  endTime: "",
  size: 10
})
const teachers = reactive({
  firstName: "",
  lastName: "",
  jobTitle: "",
  size: 10
})
const students = reactive({
  nationalCode: "",
  studentCode: "",
  phoneNumber: "",
  age: "",
  size: 10
})

function removeAllFilters() {
  if (theme === 'news') {
    news.title = ""
    news.startTime = ""
    news.endTime = ""
    news.size = 10
    emit('search', news)
  }
  if (theme === 'teachers') {
    teachers.firstName = ""
    teachers.lastName = ""
    teachers.jobTitle = ""
    teachers.size = 10
    emit('search', teachers)
  }
  if (theme === 'students') {
    students.phoneNumber = ""
    students.nationalCode = ""
    students.studentCode = ""
    students.age = ""
    students.size = 10
    emit('search', teachers)
  }
}

function search() {
  if (theme === 'news') {
    emit('search', news)
  }
  if (theme === 'teachers') {
    emit('search', teachers)
  }
  if (theme === 'students') {
    emit('search', students)
  }
}
</script>

<template>
  <section class="w-[92%] md:w-full max-w-screen-2xl mx-auto search-dashboard card bg-white p-5 rounded flex flex-col gap-5 ">
    <div class="card-header font-bold border-b pb-3 ">
      <h2>فیلتر ها </h2>
    </div>
    <section class="card-body">
      <form action="" class="flex flex-col gap-5">
        <section class="flex gap-2 items-center flex-col md:flex-row" v-if="theme==='news'">
          <div class="input-group flex flex-col gap-2 ">
            <label for="title">عنوان : </label>
            <input type="text" id="title" v-model.trim="news.title" class="w-full">
          </div>
          <div class="input-group flex flex-col gap-2">
            <label>تاریخ شروع :</label>
            <date-picker format="YYYY-MM-DD" v-model.trim="news.startTime" class="rounded"/>
          </div>
          <div class="input-group flex flex-col gap-2">
            <label>تاریخ پایان :</label>
            <date-picker format="YYYY-MM-DD" v-model.trim="news.endTime" class="rounded"/>
          </div>
          <div class="input-group flex flex-col gap-2">
            <label for="status">تعداد در صفحه :</label>
            <select id="status" v-model="news.size" class="w-full">
              <option value="10">10</option>
              <option value="20">20</option>
              <option value="40">40</option>
            </select>
          </div>
        </section>
        <section class="flex gap-2 items-center flex-col md:flex-row" v-if="theme==='teachers'">
          <div class="input-group flex flex-col gap-2">
            <label for="name">نام :</label>
            <input type="text" id="name" placeholder="نام خود را وارد کنید." v-model.trim="teachers.firstName" class="w-full">
          </div>
          <div class="input-group flex flex-col gap-2">
            <label for="lastName">نام خانوادگی :</label>
            <input type="text" placeholder="نام خانوادگی خودرا وارد کنید." id="lastName" v-model.trim="teachers.lastName" class="w-full">
          </div>
          <div class="input-group flex flex-col gap-2 ">
            <label for="title">عنوان شغلی : </label>
            <input type="text" placeholder="عنوان شغلی خود را وارد کنید." id="title" v-model.trim="teachers.jobTitle" class="w-full">
          </div>
          <div class="input-group flex flex-col gap-2">
            <label for="status">تعداد در صفحه :</label>
            <select id="status" v-model="teachers.size" class="w-full">
              <option value="10">10</option>
              <option value="20">20</option>
              <option value="40">40</option>
            </select>
          </div>
        </section>
        <section class="flex gap-2 items-center flex-col md:flex-row" v-if="theme==='students'">
          <div class="input-group flex flex-col gap-2">
            <label for="name">کد ملی :</label>
            <input type="text" id="name" placeholder="کد ملی دانش آموز را وارد کنید." v-model.trim="students.nationalCode" class="w-full">
          </div>
          <div class="input-group flex flex-col gap-2">
            <label for="lastName">کد دانش آموزی :</label>
            <input type="text" placeholder="کد دانش آموزی را وارد کنید." id="lastName" v-model.trim="students.studentCode" class="w-full">
          </div>
          <div class="input-group flex flex-col gap-2 ">
            <label for="title">شماره تماس : </label>
            <input type="text" placeholder="شماره تماس دانش آموز را وارد کنید." id="title" v-model.trim="students.phoneNumber" class="w-full">
          </div>
          <div class="input-group flex flex-col gap-2 ">
            <label for="title">سن : </label>
            <input type="text" placeholder="سن دانش آموز را وارد کنید." id="title" v-model.trim="students.age" class="w-full">
          </div>
          <div class="input-group flex flex-col gap-2">
            <label for="status">تعداد در صفحه :</label>
            <select id="status" v-model="students.size" class="w-full">
              <option value="10">10</option>
              <option value="20">20</option>
              <option value="40">40</option>
            </select>
          </div>
        </section>


        <div class=" flex flex-col md:grid grid-cols-4 gap-2 ">
          <div class="col-span-3">
            <button class="w-full bg-blue-500 text-white py-1.5 rounded" @click.prevent="search">جستجو</button>
          </div>
          <div class="col-span-1">
            <button class="w-full bg-red-500 text-white py-1.5 rounded" @click.prevent="removeAllFilters">حذف فیلتر ها
            </button>
          </div>
        </div>
      </form>
    </section>
  </section>
</template>

<style scoped lang="scss">
h2 {
  font-size: var(--title-xl);
}

label {
  font-size: var(--text-md);
  font-weight: var(--font-bold);
}

button {
  font-size: var(--text-md);
}

input, select {
  padding: 6px 6px;
  border-radius: 3px;
  border: 1px solid rgba(0, 0, 0, 0.15);
  outline: 0;
  font-size: var(--text-md);
}
input::placeholder{
  font-size: var(--text-sm);
  color: rgba(0,0,0,0.3);
}
.input-group {
  width: 100%;
}
</style>
