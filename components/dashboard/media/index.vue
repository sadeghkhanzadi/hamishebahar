<script setup lang="ts">
 const props = defineProps(['data'])
 const emit = defineEmits(['edite-card'])
 const {data} = props
</script>

<template>
  <div class="card w-full hover:cursor-pointer overflow-hidden rounded relative" @click="emit('edite-card' , data)">
    <div class="card-header">
      <figure class="h-80 w-full">
        <img :src="data.img" class="w-full h-full" :alt="data.name">
      </figure>
    </div>
    <div class="card-body  absolute ">
      <div class="icon flex justify-center items-center">
        <i class="fa-regular fa-edit fa-2xl " />
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
  .card{
    box-shadow: 0 1px 6px rgba(0,0,0,0.5);
    .card-body{
      bottom: 0;
      height: 0;
      width: 100%;
      color: #FFFFFF;
      background: rgba(0,0,0,0.2);
      transition: 0.3s;
      overflow: hidden;
      .icon{
        width: 100%;
        height: 100%;
        transform: scale(0);
        transition: 0.3s 0.2s;
        opacity: 0;
      }
    }

    &:hover{
      .card-body{
        height: 100%;
        .icon{
          opacity: 1;
          transform: scale(1);
        }
      }
    }
  }

</style>