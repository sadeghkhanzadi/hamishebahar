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
    link: "/dashboard/news",
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
    link: "/dashboard/teachers",
    icon:"fa-person-chalkboard"
  },
  {
    name: "آموزش ها",
    link: "/dashboard/educations",
    icon:"fa-school-flag"
  },
  {
    name: "مدیریت  دانش آموزان",
    link: "/dashboard/students",
    icon:"fa-user-graduate"
  },
  {
    name: "نمرات و آمتیازات",
    link: "/dashboard/points",
    icon:"fa-star"
  },
  {
    name: "پیام ها",
    link: "/dashboard/messages",
    icon:"fa-envelope",
    sub:[
      {
        name:"نظرات",
        link:"/dashboard/messages/comments"
      },
      {
        name:"ایمیل ",
        link:"/dashboard/messages/emails"
      }
    ]
  },
  {
    name: "تنظیمات",
    link: "/dashboard/settings",
    icon:"fa-gear",
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
    <main class="grid grid-cols-5" >
      <aside class=" col-span-2 md:col-span-2 lg:col-span-1  " >
        <div class="navbar-aside p-10  ">
          <ul class=" flex flex-col gap-1">
            <dashboard-navbar-items v-for="(item,index) in data" :key="index" :data="item"/>
          </ul>
        </div>
      </aside>
      <div class="bg-gray-50 p-5 w-full rounded-lg col-span-3 md:col-span-3 lg:col-span-4 ">
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
