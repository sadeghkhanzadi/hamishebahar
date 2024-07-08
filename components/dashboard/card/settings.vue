<script setup lang="ts">
const props = defineProps(['data'])
</script>

<template>
<div class="card flex justify-center p-7 rounded-lg" v-if="data" :style="{background:data.color ? data.color :`#${Math.floor(Math.random()*176675)}` }">
    <div class="card-body flex flex-col items-center justify-center gap-5">
        <div class="icon"><i :class="`fa-solid fa-${data.icon} text-5xl text-white`" v-if="data.icon"/></div>
      <div class="card-title font-extrabold text-xl text-white mt-2" v-if="data.title"><h5>{{data.title}}</h5></div>
      <div class="card-text text-white text-sm text-center " v-if="data.title"><p>تنظیمات {{data.title}} در این قسمت میباشد </p></div>
      <div class="change bg-white px-5 py-2 rounded-full"><nuxt-link v-if="data.link" :to="data.link">ویرایش</nuxt-link></div>
    </div>
</div>
</template>

<style scoped lang="scss">

</style>