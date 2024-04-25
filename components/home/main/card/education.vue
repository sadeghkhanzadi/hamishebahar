<script setup lang="ts">
  const props=defineProps(['data'])
  const collapse =ref(null)
  function show(){
    collapse.value.classList.toggle('show')
  }
</script>

<template>
<div class="collapse-container " ref="collapse">
    <div class="button text-red-600 flex justify-between px-5 py-4" :style="`background:${props.data.color}`" @click="show">
      <h3>{{props.data.title}}</h3>
      <span><i class="fa-solid fa-angle-down transition duration-300 ease-linear"></i></span>
    </div>
    <div class="text  " >
      <p class="p-3">
        {{props.data.text}}
        {{props.data.text}}
        {{props.data.text}}
        {{props.data.text}}
      </p>
    </div>
</div>
</template>

<style scoped lang="scss">
.button{
  color:#fff;
  border-radius: 5px;
}
.text {
  overflow: hidden;
    height: 0;
    transition: 0.5s;

  p{
    background-color: #FFFFFF;
    //transform: translateY(-100%);
  }
}
.show .text{
  height: 100px;
  overflow-y: scroll;
  //transform: translate(0);
}
.show i {
  transform: rotate(180deg);
}
.text::-webkit-scrollbar {
  display: none;
}


</style>