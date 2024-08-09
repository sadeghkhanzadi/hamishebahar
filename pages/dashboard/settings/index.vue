<script setup lang="ts">
const card = [
  {
    title:"عمومی",
    color:"#EA2027",
    icon:"gear",
    link:"/dashboard/settings/general"
  },
  {
    title:"تماس با ما",
    color:"#ff9ff3",
    icon:"phone",
    link:"/dashboard/settings/contact-us"
  },
  {
    title:"درباره ما",
    color:"#feca57",
    icon:"address-card",
    link:"/dashboard/settings/about-us"
  },
  {
    title:"تغییر رمز",
    color:"#ff6b6b",
    icon:"lock",
    link:"/dashboard/settings/change-password"
  },
  {
    title:"مدیریت کاربران",
    color:"#48dbfb",
    icon:"users",
    link:"/dashboard/settings/user-manager"
  },
  {
    title:"لینک ها",
    color:"#1dd1a1",
    icon:"link",
    link:"/dashboard/settings/links"
  },
  {
    title:"آدرس",
    color:"#82589F",
    icon:"map-location-dot",
    link:"/dashboard/settings/address"
  },
  {
    title:"سطوح دسترسی",
    color:"#009432",
    icon:"universal-access",
    link:"/dashboard/settings/access-level"
  },
]
</script>

<template>
<div>
<div class="backdrop bg-white grid grid-cols-4 gap-4 p-10 rounded-lg">
  <dashboard-card-settings class=" col-span-4 md:col-span-2 lg:col-span-1" :data="item" v-for="(item , index) in card" :key="index"/>
</div>
</div>
</template>

<style scoped lang="scss">

</style>