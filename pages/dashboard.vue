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
    link: "dashboard",
  },
  {
    name: "صفحه ها",
    link: "pages",
    sub: [
      {
        name: "صفحه اصلی",
        link: "",
      },
    ]
  },
  {
    name: "پست ها",
    link: "posts",
  },
  {
    name: "رسانه ها",
    link: "medias",
  },
  {
    name: "نظرات",
    link: "comments",
  },
  {
    name: "تنظیمات",
    link: "setting",
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
      <div class="bg-gray-50 p-5 ">
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