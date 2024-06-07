<script setup lang="ts">
const props = defineProps({
  data:Object
})
</script>

<template>
<div class="card relative flex flex-col items-center after:rounded-xl px-8">
  <div class="card-header relative rounded-xl overflow-hidden w-full flex justify-center items-center">
    <img :src="data.img" :alt="data.name"  class="w-full ">
    <div class="social-app bg-amber-500">
      <ul class="flex gap-2 justify-center">
        <li >
          <nuxtLink :to="data.socialApp.telegram" class=" flex justify-center items-center px-2 py-4 border border-white rounded-full ">
            <span class="flex  "><i class="fa-brands fa-linkedin-in fa-lg text-white"/></span>
          </nuxtLink>
        </li>
        <li >
          <nuxtLink :to="data.socialApp.instagram" class=" flex justify-center items-center px-2 py-4 border border-white rounded-full ">
            <span class="flex  "><i class="fa-brands fa-instagram fa-lg text-white"/></span>
          </nuxtLink>
        </li>
        <li >
          <nuxtLink :to="data.socialApp.twitter" class=" flex justify-center items-center px-2 py-4 border border-white rounded-full ">
            <span class="flex  "><i class="fa-brands fa-twitter fa-lg text-white"/></span>
          </nuxtLink>
        </li>
      </ul>
    </div>
  </div>
  <div class="card-body flex flex-col gap-1.5 text-center pt-4 pb-9">
    <nuxt-link :to="`/teachers/${data.url}`" class="card-title"><h3>{{data.name}}</h3></nuxt-link>
    <div class="card-text"><span>{{data.job}}</span></div>
  </div>
</div>
</template>

<style scoped lang="scss">
.card{
    .card-header{
      background: #6ACABD;
      position: relative;
      &:after{
        background: transparent;
        position: absolute;
        content: "";
        width: 100%;
        height: 100%;
        inset: 0;
        transition:background 0.3s;
    }
  }
  &:hover{
    .card-header{
      &:after{
        background: rgba(106, 202, 189, 0.50);
      }
    }
    li{
      &:first-child{
        animation: show 0.5s forwards ease 1;
      }
      &:nth-child(2){
        animation: show 0.5s forwards ease 1 0.1s;
      }
      &:last-child{

        animation: show 0.5s forwards ease 1 0.2s;
      }
    }
  }
  &:after{
    content: "";
    width: 100%;
    height: 70%;
    background: #fff;
    position: absolute;
    bottom: 0;
    z-index: -1;
    box-shadow:  0 0 15px rgba(0,0,0,0.2);
  }
  .card-title{
    font-size: 1.2rem;
    font-weight: 700;
    transition:0.2s;
    &:hover{
      color: rgba(106, 202, 189, 1);
    }
  }
  .card-text{
    font-size:1rem ;
    color: #808080;
  }
}
ul {
  position: absolute;
  top: 50%;
  right: 50%;
  transform: translate(50% , -50%);
    z-index: 10;
  li{
    opacity: 0;
    transform: translateX(100%);
    a:hover{

      background: #FFFFFF;
      i{
        color: rgba(106, 202, 189, 1);
      }
    }

  }
}
@keyframes show {
  from{
    opacity: 0;
    transform: translateX(100%);
  }
  to{
    opacity: 1;
    transform: translate(0);
  }
}
</style>