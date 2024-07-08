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
<style>
 .father .router-link-exact-active{
  background: #f4f4f4;
   border-radius: 3px;
}
</style>
