<script setup lang="ts">
const {data} = defineProps({
  data:{
    default:`داده ای`,
    type:String
  }
})
</script>

<template>
  <div class="alert bg-red-400 rounded px-3 py-3 md:py-5 text-white text-center shadow-lg">
    <h5 class="text-sm md:text-lg"> هیچ {{data}} یافت نشد !!! </h5>
  </div>
</template>

<style scoped lang="scss">

</style>