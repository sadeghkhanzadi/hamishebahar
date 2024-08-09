<script setup lang="ts">
const props = defineProps({
  data: Object
})
const {time, level, img, score, title, price, course, students, url, description} = props.data
</script>

<template>
  <div class="card overflow-hidden rounded relative">
    <div class="inner-card">
      <div class="card-header relative overflow-hidden">
        <img :src="img" :alt="title">
        <div class="time flex gap-2 items-center px-2 py-1 rounded">
          <span class="icon"><i class="fa-regular fa-clock"></i></span>
          <span class="text">{{ time }}</span>
        </div>
      </div>
      <div class="card-body flex flex-col gap-2 px-3 py-5">
        <div class="card-level w-fit py-1 px-2 rounded mb-3">{{ level }}</div>
        <div class="card-title">{{ title }}</div>
        <div>
          <ul class="flex gap-0.5">
            <li class="score " v-for="item in 5" :key="item">
              <i class="fa-solid fa-star fa-sm text-yellow-500 "/>
              <!--                      <i class="fa-regular fa-star fa-sm text-yellow-400 star-border text-red-500 z-30"/>-->
            </li>
            <li>( {{ score }} امتیاز)</li>
          </ul>
        </div>
        <div class="card-price"> {{ Number(price).toLocaleString() }} تومان</div>
        <div class="meta flex gap-2 items-center mt-1.5">
          <div class="flex gap-1">
            <span class="icon"><i class="fa-solid fa-book"/></span>
            <span class="text">{{ course.count }} درس</span>
          </div>
          <div class="divider">|</div>
          <div class="flex gap-1">
            <span class="icon"><i class="fa-solid fa-user-alt"/></span>
            <span class="text">{{ students.count }} دانش آموزان   </span>
          </div>
        </div>
      </div>
    </div>
    <div class="hover-card absolute inset-0 w-full h-full  ">
      <div class="card-body flex flex-col gap-2 px-3 py-5">
        <div class="card-level-hover w-fit py-1 px-2 rounded mb-3 ">{{ level }}</div>
        <div class="card-title">{{ title }}</div>
        <div>
          <ul class="flex gap-0.5">
            <li class="score " v-for="(item,index) in 5" :key="index">
              <i class="fa-solid fa-star fa-sm text-yellow-500 "/>
              <!--                      <i class="fa-regular fa-star fa-sm text-yellow-400 star-border text-red-500 z-30"/>-->
            </li>
            <li>( {{ score }} امتیاز)</li>
          </ul>
        </div>
        <div class="card-price-hover"> {{ Number(price).toLocaleString() }} تومان</div>
        <div class="description mt-2 mb-3"><p>{{ description }}</p></div>
        <div class="meta-hover flex gap-2 items-center mt-1.5">
          <div class="flex gap-1">
            <span class="icon"><i class="fa-solid fa-book"/></span>
            <span class="text">{{ course.count }} درس</span>
          </div>
          <div class="divider">|</div>
          <div class="flex gap-1">
            <span class="icon"><i class="fa-solid fa-user-alt"/></span>
            <span class="text">{{ students.count }} دانش آموزان   </span>
          </div>
        </div>
        <div class="register mt-3 w-fit gap-1 py-2 px-3 rounded">
          <nuxt-link :to="url" class="flex items-center gap-1 ">
            <i class="fa-solid fa-arrow-right-long "/>
            <span>ثبت نام کنید.</span>
          </nuxt-link>
        </div>
      </div>
    </div>

  </div>
</template>

<style scoped lang="scss">
.card {
  background: #FFFFFF;
  &:hover  img{
    transform: scale(1.1);
  }
}

.inner-card {
  img{
    transition: 0.3s;
  }
  .time {
    position: absolute;
    background: #F8B81F;
    top: 3%;
    right: 3%;
    color: #FFFFFF;
    font-size: 0.8rem;
  }

  .card-level {
    font-size: 0.8rem;
    background: rgba(26, 182, 157, 0.15);
    color: #1AB69D;
  }

  .card-title {
    font-size: 0.9rem;
    line-height: 25px;
    font-weight: 600;
  }

  .card-price {
    font-size: 0.8rem;
    color: #ee4a62;
  }

  .meta {
    font-size: 0.8rem;

    .icon {
      color: rgb(167, 167, 167);
    }

    .text {
      color: rgb(24, 24, 24);
    }
  }
}

ul {
  //width: 30px;
  //background: yellow;
  //background-repeat: repeat;
  //-webkit-background-clip: text;
  //z-index: 1;
  //-webkit-text-fill-color: transparent;

  .star-border {
    position: relative;
    top: 0;
    right: -50%;
    z-index: 0;
  }
}

/*hover-card*/
.card:hover .hover-card {
  //visibility: visible;
  opacity: 1;
  scale: 1;
  visibility: visible;
}

.hover-card {
 scale: .92;
  visibility: hidden;
  opacity: 0;
  background: #1AB69D;
  color: #FFFFFF;
  transition:  0.5s;

  .card-level-hover {
    font-size: 0.8rem;
    background: #FFFFFF;
    color: black;
  }

  .card-price-hover {
    color: #FFFFFF;
  }

  .meta-hover {
    color: #FFFFFF;
    font-size: 0.8rem;
  }

  .register {
    font-size: 0.9rem;
    background: #ee4a62;
    transition: 0.3s;

    &:hover {
      background: #FFFFFF;
      color: black;
    }
  }

  .description {
    display: -webkit-box;
    -webkit-line-clamp: 3;
    line-clamp: 3;
    -webkit-box-orient: vertical;
    text-overflow: ellipsis;
    overflow: hidden;
  }
}
</style>