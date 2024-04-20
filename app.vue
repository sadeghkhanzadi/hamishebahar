<template>
  <div>
    <nuxt-layout>
      <nuxt-page/>
    </nuxt-layout>
  </div>
</template>
<script setup>
useHead({
  titleTemplate:(title)=>{
    return title ? `${title} | همیشه بهار ` : 'همیشه بهار '
  }
})
</script>
