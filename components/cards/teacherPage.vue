<script setup lang="ts">
const props = defineProps({
  data:Object
})
</script>

<template>
<div class="card grid grid-cols-12 gap-5 ">
<div class="card-header col-span-12 md:col-span-4 flex gap-5 flex-col">
  <figure class="rounded-full overflow-hidden">
    <img class="w-full h-full" :src="data.img" :alt="data.name">
  </figure>
  <div class="social-media">
    <ul class="flex gap-3 items-center justify-center ">
      <li class="telegram px-2 py-4 rounded-full">
        <nuxtLink  class="flex justify-center items-center" :to="data.telegram"><i class="fa-brands fa-telegram-plane fa-lg"/> </nuxtLink>
      </li>
      <li class="instagram px-2 py-4 rounded-full">
        <nuxtLink class="flex justify-center items-center"  :to="data.instagram"><i class="fa-brands fa-instagram fa-lg"/> </nuxtLink>
      </li>
      <li class="linkedin px-2 py-4 rounded-full">
        <nuxtLink class="flex justify-center items-center"  :to="data.linkedin"><i class="fa-brands fa-linkedin-in fa-lg"/> </nuxtLink>
      </li>
    </ul>
  </div>
</div>
  <div class="card-body col-span-12 md:col-span-8">
    <div class="flex flex-col items-center mt-5 md:mt-0 md:items-start" >
      <div class="job"><span>{{data.job}}</span></div>
      <div class="name mt-2 mb-1.5"><h2>{{data.name}}</h2></div>
      <div class="job-info"><span>{{data.jobInfo}}</span></div>
    </div>
    <div class="meta flex flex-col md:flex-row items-center gap-2 md:gap-4 mt-7">
      <div class="students flex gap-1.5 items-center">
        <span class="icon"> <i class="fa-regular fa-xs fa-user"/></span>
        <span class="text">{{data.students.count}}  دانشجو  </span>
      </div>
      <div class="divider hidden md:block">|</div>
      <div class="comments flex items-center gap-2">
        <ul class="flex gap-1">
          <li v-for="item in 5" :key="item"><i class="fa-solid fa-star fa-xs text-yellow-300"/></li>
        </ul>
        <div class="text">( {{data.comments.count}} دیدگاه )</div>
      </div>
    </div>
    <div class="card-content flex flex-col gap-4 mt-5">
      <div class="card-content-title"><h5>درباره من</h5></div>
      <div class="card-content-text text-justify" v-html="data.text"></div>
    </div>
    <div class="mt-20">
      <call-me :data="data.call"/>
    </div>
  </div>
</div>
</template>

<style scoped lang="scss">
figure{
  max-width: 400px;
  max-height: 400px;
}
.social-media{
  color: rgb(167,167,167);
  li{
    transition: 0.3s;
    border:1px solid rgba(0,0,0,0.1);
    &:hover{
      background: #1ab69d;
      color: #FFFFFF;
    }
  }
}
.name{
  font-size: 1.4rem;
  font-weight: 600;
}
.job{
  font-size: 0.8rem;
  color: #1ab69d;
}
.job-info{
  font-size: 0.8rem;
  color: #808080;
}
.meta{
  .divider{
    color:rgb(167,167,167);
  }
  .icon{
    color:rgb(167,167,167);
  }
  .text{
    color: rgb(24,24,24);
    font-size: 0.8rem;
  }
}
.card-content-title{
  font-size: 1rem;
  font-weight: 600;

}
</style>