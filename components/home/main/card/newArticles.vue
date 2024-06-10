<script setup lang="ts">
const props = defineProps(['data'])
</script>

<template>
  <div>
    <div class="card " v-if="props.data" data-aos="fade-up">
      <div class="card-header  relative">
        <div class="link flex justify-center items-center arrow-link absolute ">
          <nuxt-link class="flex items-center justify-center" to=""><i class="fa-solid fa-arrow-right flex items-center fa-lg text-white"/></nuxt-link>
        </div>
        <nuxt-link to="">
          <figure>
            <img :src="props.data.img" alt="">
          </figure>
        </nuxt-link>
      </div>
      <div class="card-body relative flex flex-col gap-4 ">
        <div class="type xl:mt-4">
          <nuxt-link to="">{{ props.data.subject }}</nuxt-link>
        </div>
        <div class="subject">
          <h2>
            <nuxt-link to="">{{ props.data.title }}</nuxt-link>
          </h2>
        </div>
        <ul class="info">
          <li class="flex gap-3">
            <div class="flex gap-2">
              <span><i :class="`fa-solid fa-calendar`"></i></span>
              <span>{{ props.data.dateText }}</span>
            </div>
            <div class="divider"></div>
            <div class="flex gap-2">
              <span><i :class="`fa-solid fa-comment`"></i></span>
              <span class="lesson">نظرات</span>
              <span>{{ props.data.commentCount }}</span>
            </div>
          </li>
        </ul>
        <div class="text"><p>{{ props.data.text }}</p></div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
@import '@/assets/style/varible.scss';

.card {
  border-radius: $border-radius-card-section-three;
  overflow: hidden;
  box-shadow: 1px 1px 30px rgba(0,0,0,0.2);
  .subject {
    font-size: $fontSize-title-activities;
    font-weight: $fontWeight-title-activities;
    color: $color-title-activities;
  }
  .card-header {
    &:after{
      content: "";
      position: absolute;
      inset: 0;
      transition: 0.3s;
      width: 100%;
      height: 100%;
      background-color: rgba(0,0,0,0.5);
      opacity: 0;

    }
    .arrow-link {
      width: 60px;
      height: 60px;
      background: $background-arrow-articles;
      border-radius: 100%;
      right: 50%;
      top: 50%;
      transform: translate(50%, -50%);
      z-index: 1;
      opacity: 0;
      transition: 0.2s;
      &:hover{
        background-color: $background-arrow-hover-articles;
      }
    }
  }
  .type {
    font-family: $font;
    color: $color-text-articles;
  }

  .card-body {
    position: relative;
    padding: 20px;
    &:after {
      content: url("@/assets/image/shape/last-card-cloud.png");
      position: absolute;
      top: -35px;
      right: 0;
      width: 100%;
      transform: scale(1.15);
    }
    ul.info {
      font-size: $fontSize-card-text-info-section-three;

      .divider {
        width: 2px;
        background-color: white;
      }
    }

    .text {
      line-height: $lineHeight-normal-card-section-three;
      text-overflow: ellipsis;
      overflow: hidden;
      display: -webkit-box;
      -webkit-box-orient: vertical;
      line-clamp: 1;
      -webkit-line-clamp: 1;
      color: $color-text-articles;
      font-size: $fontSize-text-articles;

    }

    i {
      color: $color-icon-articles;
    }
  }
    &:hover img{
      transform: scale(1.05);
    }
   &:hover .arrow-link{
     transform: translate(50%, -50%);
     scale: 1;
     opacity: 1;
   }
  &:hover :after{
    opacity: 1;
  }
}

figure {
  overflow: hidden;

  img {
    transition: 0.5s;
  }
}

//responsive mode

@media screen and (max-width:1024px)  {
  figure{
    width: 100% !important;
    img{
      object-fit: fill;
    }
  }
  .card-body{
    &:after{
      display: none;
    }
  }
}

</style>


