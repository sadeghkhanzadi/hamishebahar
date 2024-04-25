<script setup lang="ts">
import img1  from "@/assets/image/shape/card-cloud-top.png"
import img2  from "@/assets/image/shape/card-cloud.png"
const props = defineProps(['data'])
</script>

<template>
<div>
  <div class="content-container">
    <div class="card flex gap-5">
      <div class="card-header flex">
        <div class="image flex justify-center items-center ">
          <span><i :class="`fa-solid fa-${props.data.icon} fa-xl text-white`"/></span>
        </div>
      </div>
      <div class="card-body flex flex-col">
       <nuxt-link to="">
         <div class="title text-end">
           <h3>{{props.data.name}}</h3>
         </div>
         <div class="text text-end">
           <p>{{props.data.text}}</p>
         </div>
       </nuxt-link>
      </div>
    </div>
  </div>
</div>
</template>

<style scoped lang="scss">
@import '@/assets/style/varible.scss';
h3{
  font-family: $fontFamily-title-activities;
  font-size: $fontSize-title-activities;
  font-weight: $fontWeight-title-activities;
  color: $color-title-activities;
}
p{
  font-size: $fontSize-text-activities;
  color: $color-text-activities;
}
.image{

  position:relative;
  width: 120px;
  height: 89px;
  mask-image: url("@/assets/image/shape/card-cloud.png") ;
  mask-repeat: no-repeat;
  mask-position: center;
  mask-size: cover;
  background-color: rgba(26, 182, 157, 0.5);
  &:after{
    content: "";
    width: 100%;
    height: 100%;
    position: absolute;
    mask-image: url("@/assets/image/shape/card-cloud.png") ;
    mask-repeat: no-repeat;
    mask-position: center;
    mask-size: cover;
    background-color: rgba(26, 182, 157, 0.9);
    inset: 0;
  }
}
</style>