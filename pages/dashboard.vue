<script setup lang="ts">
useHead({
  title:"داشبورد"
})
definePageMeta({
  layout:false,

})
const data = [
  {
    name: "داشبورد",
    link: "/dashboard",
    icon:"fa-chart-line"
  },
  {
    name: "اخبار و رویداد ها",
    icon:"fa-newspaper",
    link: "/pages",
    sub: [
      {
        name: "صفحه اصلی",
        link: "",
      },
    ]
  },

  {
    name: "رسانه ها",
    link: "/dashboard/media",
    icon:"fa-photo-film"
  },
  {
    name: "مربیان",
    link: "comments",
    icon:"fa-person-chalkboard"
  },
  {
    name: "آموزش ها",
    link: "posts",
    icon:"fa-school-flag"
  },
  {
    name: "مدیریت  دانش آموزان",
    link: "posts",
    icon:"fa-user-graduate"
  },
  {
    name: "نمرات و آمتیازات",
    link: "posts",
    icon:"fa-star"
  },
  {
    name: "پیام ها",
    link: "posts",
    icon:"fa-envelope",
    sub:[
      {
        name:"نظرات"
      },
      {
        name:"ایمیل "
      }
    ]
  },
  {
    name: "تنظیمات",
    link: "setting",
    icon:"fa-gear",
    sub:[
      {
        name:"تماس با ما"
      },
      {
        name:" درباره ما"
      },
      {
        name:"تغییر رمز "
      },
      {
        name:" مدیریت کاربران"
      },
      {
        name: " لینک ها"
      },
      {
        name:"آدرس"
      },
      {
        name:"سطوح دسترسی"
      },

    ]
  }
]
const icon = [


  {
    name:'media',
  },
  {
    name:'teachers',
  },
  {
    name:'education',
  },
  {
    name:'students',
  },
  {
    name:"scores",
  },
  {
    name:"message",
  },
  {
    name:"settings",
  },
  {
    name:"",
    icon:""
  },
  {
    name:"",
    icon:""
  }
]
</script>

<template>
  <div >
    <div>
      <dashboard-navbar/>
    </div>
    <main class="flex" >
      <aside class=" w-1/5 " >
        <div class="navbar-aside p-10  ">
          <ul class=" flex flex-col gap-2">
            <dashboard-navbar-items v-for="(item,index) in data" :key="index" :data="item"/>
          </ul>
        </div>
      </aside>
      <div class="bg-gray-50 p-5 w-full rounded-lg ">
        <nuxt-page/>
      </div>
    </main>
  </div>
</template>

<style scoped lang="scss">
  .navbar-aside{
    min-height:calc( 100vh - 64px);
  }
</style>