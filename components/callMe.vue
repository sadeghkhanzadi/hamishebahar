<script setup lang="ts">
const props = defineProps({
  data:Object
})
</script>

<template>
<div class="flex flex-col justify-center gap-4">
  <h5>{{data.title}}</h5>
  <ul class="flex flex-col gap-y-3">
    <li><span class="title me-1">آدرس :</span><span class="text">{{data.address}}</span></li>
    <li><span class="title me-1">ایمیل :</span><a :href="`tel:${data.email}`" class="text">{{ data.email }}</a></li>
    <li><span class="title me-1">تلفن :</span><a :href="`tel:${data.phone}`" class="text">{{data.phone}}</a></li>
  </ul>
</div>
</template>

<style scoped lang="scss">
h5{
  font-size: 18px;
  font-weight: 600;
}
.title{
  font-size: 0.9rem;
  color:rgb(24,24,24)
}
.text{
  font-size: 0.9rem;
  color:#808080;
}
</style>