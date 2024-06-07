<script setup lang="ts">
const props = defineProps(['data'])

</script>

<template>
<div>
  <div class="card "  v-if="props.data" data-aos="fade-up">
    <div class="card-header relative">
          <nuxt-link to="">
            <figure>
              <img :src="props.data.img" alt="">
            </figure>
          </nuxt-link>
      <div class="price absolute " :style="`color:${props.data.color}`"><span>{{props.data.price}}</span></div>
    </div>
    <div class="card-body md:bottom-7 relative flex flex-col gap-4" :style="`background:${props.data.color}`">
      <div class="type mt-4"><nuxt-link to="">{{props.data.type}}</nuxt-link></div>
      <div class="subject"><h2><nuxt-link to="">{{props.data.subject}}</nuxt-link></h2></div>
      <ul class="info">
        <li class="flex gap-3">
          <div class="flex gap-2">
            <span><i :class="`fa-solid fa-${props.data.info[0].icon}`"></i></span>
            <span>{{props.data.info[0].lesson}}</span>
            <span class="lesson">درس</span>
          </div>
          <div class="divider"></div>
          <div class="flex gap-2">
            <span><i :class="`fa-solid fa-${props.data.info[1].icon}`"></i></span>
            <span>{{props.data.info[1].count}}</span>
            <span class="lesson">دانش آموزان</span>
          </div>
        </li>
      </ul>
      <div class="text"><p>{{props.data.text}}</p></div>
    </div>
  </div>
</div>
</template>

<style scoped lang="scss">
@import '@/assets/style/varible.scss';
.card{
  border-radius:$border-radius-card-section-three;
  overflow: hidden;
  color: $color-card-section-three;
  font-size: $fontSize-normal-section-three;
  .subject{
    font-size: $fontSize-subject-section-three;
    font-weight: $fontWeight-subject-section-three;
  }
  .type{
    font-weight: $fontWeight-type-section-three;
    font-family: $font;
  }
  .card-body{
    padding: $body-padding-section-three;
    mask-image: url("@/assets/image/shape/card.png");
     overflow: hidden;
    mask-size: cover;
    mask-repeat: no-repeat;
    ul.info{
      font-size: $fontSize-card-text-info-section-three;
      .divider{
        width: 2px;
        background-color: white;
      }
    }
    .text{
      line-height: $lineHeight-normal-card-section-three;
    }
    //remove mask image in mobile mode
     @media screen and (max-height: 767.9px) {
          .card-body{
          mask-image: none !important;
          -webkit-mask-image: none !important;
          }
    }
  }
}
.price{
  left: 30px;
  bottom: -45px;
  width: 90px;
  height: 90px;
  z-index: 5;
  background-color: white;
  border-radius: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}
figure{
  overflow: hidden;
  img{
    transition: 0.5s;
  }
  &:hover img{
    transform: scale(1.05);
  }
}
//remove mask image in mobile mode 
@media screen and (max-height: 768px) {
.card{
  .card-body{
    mask-image: none !important;
  }
}
}

</style>