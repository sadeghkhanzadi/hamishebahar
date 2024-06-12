<script setup lang="ts">
const props = defineProps({
  data:Object
})
const {color , img  , text , title} =props.data
</script>

<template>
  <div class="card p-10 shadow bg-white flex flex-col gap-3 rounded">
    <div class="card-header mx-auto flex justify-center items-center h-20 w-20 rounded-full bg-amber-500"><span><i :class="`${data.img} fa-2xl`"/></span></div>
    <div class="card-body flex flex-col gap-3">
      <div class="card-title text-center"><h3>{{data.title}}</h3></div>
      <div class="card-text text-center"><p>{{data.text.substring(0,91)}}</p></div>
    </div>
  </div>
</template>
<style scoped lang="scss">
.card{
  box-shadow: 0 0 15px 1px rgba(0,0,0,0.3);
  .card-header{
    transition: 0.2s;
  }
  &:hover{
    .card-header{
      background:v-bind(color);
      span{
        color:#FFFFFF;
      }
    }
  }
}
.card-title{
  font-weight: 600;
  font-size:0.9rem;
}
.card-text{
  font-size: 0.8rem;
  color: #808080;
}
</style>
