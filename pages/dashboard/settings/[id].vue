<script setup lang="ts">
const breadcrumb = [
  {
    name:"page"
  }
]
</script>

<template>
<div class="container mx-auto">
  <header class="flex justify-between items-center ">
    <div>
      <dashboard-breadcrumb :data="breadcrumb"/>
    </div>
    <div class="flex gap-5">
      <button class="remove-key flex items-center justify-center bg-red-500 py-2 px-5 rounded-lg text-white gap-2" @click="">حذف کلید  <i class="fa-solid fa-trash-alt"/></button>
      <button class="write-key flex items-center justify-center bg-blue-500 py-2 px-5 rounded-lg text-white gap-2" @click="">ثبت کلید <i class="fa-solid fa-add"/> </button>
    </div>
  </header>
  <main>
<!--    <dashboard-modal-write-key/>-->
  </main>
</div>
</template>

<style scoped lang="scss">

</style>