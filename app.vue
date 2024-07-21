<template>
    <NuxtLayout>
      <NuxtPage />
    </NuxtLayout>
</template>
<script setup>
const cookie = useCookie('jwt')
useState('cookie' ,()=> cookie.value)
useState('apiBaseUrl' ,()=> 'https://hamishebahar-service.liara.run')
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
