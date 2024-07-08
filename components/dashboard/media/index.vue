<script setup lang="ts">
 const props = defineProps(['data'])
 const {public:{baseUrl}} = useRuntimeConfig()
 const emit = defineEmits(['edite-card','remove-card'])
 const {data} = props
</script>

<template>
  <div class="card w-full hover:cursor-pointer flex items-center overflow-hidden rounded relative" >
    <div class="card-header">
      <figure class="flex items-center justify-center">
        <img :src="`${baseUrl}/api/v1/view/media/download/${data.pathFile}`" class="w-full h-full " :alt="data.name">
      </figure>
    </div>
    <div class="card-body  absolute ">
      <div class="icon flex justify-center items-center gap-2">
        <i class="fa-regular fa-edit fa-2xl hover:text-blue-500 transition duration-300 ease-in " @click.self="emit('edite-card' , data)"/>
        <i class="fa-regular fa-trash-alt fa-2xl hover:text-red-500 transition duration-300 ease-in" @click="emit('remove-card' , data.id)" />
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
  .card{
    position: relative;
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
    img{
      transition: 0.5s ease-out;
    }
    &:hover{
      img{
        transform: scale(1.15);
      }
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