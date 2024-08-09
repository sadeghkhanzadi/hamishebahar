<script setup lang="ts">
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
<div>
  <div>
    <dashboard-navbar/>
  </div>
  <main>
    <aside >
      <div class="navbar-aside w-1/5 p-10 ">
        <ul class=" flex flex-col gap-2">
          <dashboard-navbar-items v-for="(item,index) in data" :key="index" :data="item"/>
        </ul>
      </div>
    </aside>
  </main>
</div>
</template>

<style scoped lang="scss">

</style>