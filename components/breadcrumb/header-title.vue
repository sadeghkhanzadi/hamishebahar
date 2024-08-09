<script setup lang="ts">
const props  = defineProps(['data', 'img'])
// import img from '~/assets/image/breadcrumb/edublink-breadcrumb-bg.webp'
  const color = ref()
 props.img ? color.value='#fff' : color.value = '#000'
</script>

<template>
  <div class="header-pages h-[25vh] flex justify-center items-center overflow-hidden relative after:z-0  after:w-full after:h-full after:bg-[rgba(0,0,0,0.3)] after:inset-0 after:absolute" :class="{'after:hidden':!img , backgroundImage:img }" >
    <div class="inner-container relative w-full h-full flex items-center justify-center overflow-hidden">
      <div class="content flex flex-col items-center justify-center gap-4">
        <div class="title " >
          <h3>{{data.title}}</h3>
        </div>
        <div class="breadcrumb z-[1]">
          <lazy-breadcrumb :color="color" class="" :data="data.breadcrumb"/>
        </div>
      </div>
      <div v-if="!img" class="shape-move shape-blue hidden lg:block"><img src="~/assets/image/shape/blue-dot.png" alt="shape-move"></div>
      <div v-if="!img" class="shape-move shape-wave hidden lg:block"><img src="~/assets/image/shape/wave.png" alt="shape-move"></div>
      <div v-if="!img" class="shape-move shape-square hidden lg:block "><img src="~/assets/image/shape/red-square.png" alt="shape-move"></div>
      <div v-if="!img" class="image-circle-left hidden lg:block "></div>
      <div v-if="!img" class="image-circle-right hidden lg:block "></div>
    </div>
  </div>
</template>

<style scoped lang="scss">
.header-pages {
  background: #F5F9FA;
  background-size: cover ;
  background-position:top center ;
  background-attachment: fixed;
  background-repeat: no-repeat ;
  @media screen and (max-width: 768px){
    background-attachment: unset;
    background-size: cover ;
  }
}

.title {
  font-size: var(--title-5xl);
  z-index: 1;
  font-weight: var(--font-bold);
  color: v-bind(color);
  @media screen and (max-width: 768px) {
    font-size: var(--title-2xl);
  }
}

.image-circle-left {
  position: absolute;
  width: 350px;
  height: 350px;
  border: 1px solid rgba(0, 0, 0, 0.1);
  top: -10%;
  transform: translate(-50%, -50%);
  left: 7%;
  border-radius: 100%;
}

.image-circle-right {
  position: absolute;
  width: 350px;
  height: 350px;
  border: 1px solid rgba(0, 0, 0, 0.1);
  bottom: 5%;
  transform: translate(50%, 50%);
  right: 5%;
  border-radius: 100%;
}
.shape-blue{
  position: absolute;
  bottom: 0;
  left: 5%;
  img{
    width: 80%;
  }
}
.shape-wave{
  position: absolute;
  bottom: 40%;
  right: 7%;
  transform: translate(-50%, 50%);
  img{
    width: 80%;
  }
}
.shape-square{
  position: absolute;
  top: -15%;
  right: -3%;
  img{
    width: 80%;
  }
}
.backgroundImage{
  background: v-bind(img);
}
</style>
