<script setup lang="ts">
const emit = defineEmits(['result'])
const props = defineProps(['data'])

</script>

<template>
<div class="backdrop"  @click.self="emit('result' , false)" >
  <div class="card grid gap-10 mt-[15vh] mx-auto bg-white w-10/12 sm:w-8/12 md:w-1/2 p-5 rounded-lg shadow-lg shadow-neutral-600">
    <div class="card-body flex items-center gap-2">
      <h6 class="text-xl ">آیا میخواهید <span class="text-red-500 font-bold">{{data.name}}</span> را حذف کنید ؟</h6>
    </div>
    <div class="card-body flex flex-col justify-center items-center md:flex-row w-full md:justify-start md:items-center gap-2 ">
      <button class="bg-red-500 w-full text-white font-bold py-2 px-3 rounded shadow shadow-red-100"  @click="emit('result' , true)">بله اطمینان دارم</button>
      <button class="bg-blue-500 w-full text-white font-bold py-2 px-3 rounded shadow shadow-blue-100 " @click="emit('result' , false)">خیر</button>
    </div>
  </div>
</div>
</template>

<style scoped lang="scss">
.backdrop{
  position: absolute;
  z-index: 5;
  inset: 0;
  width: 100%;
  height: 100%;
  background: rgba(0,0,0,0.2);

}
</style>