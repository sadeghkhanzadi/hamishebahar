<template>
    <NuxtLayout>
      <NuxtPage />
    </NuxtLayout>
</template>
<script setup>
useHead({
  titleTemplate:(title)=>{
    return title ? `${title} | همیشه بهار ` : 'همیشه بهار '
  }
})
</script>
