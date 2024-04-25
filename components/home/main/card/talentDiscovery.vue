<script setup lang="ts">
const props = defineProps(["data"])
const hoverCard = ref(null)

function hover() {
  hoverCard.value.classList.add('hover')
  hoverCard.value.style.background = `linear-gradient(${props.data.bg},${props.data.bg})`

}

function leaveHover() {
  hoverCard.value.classList.remove('hover')
  hoverCard.value.style.background = ""
}
</script>

<template>
  <div class="talent-discovery ">
    <div class="card flex flex-col  lg:flex-row p-7 " @mouseenter="hover" @mouseleave="leaveHover" ref="hoverCard">
      <div class="card-header">
        <nuxt-link class="image" to="">
          <figure class="">
            <img :src="props.data.img.link" :alt="props.data.img.name">
          </figure>
        </nuxt-link>
      </div>
      <div class="card-body mx-7  flex flex-col justify-center">
        <div class="flex flex-col gap-5">
          <div class="date flex gap-2.5">
            <span class="icon"><i :class="`fa-solid fa-lg fa-${props.data.icon.calender.icon}`"/></span>
            <span class="date-text">{{ props.data.icon.calender.time }}</span>
          </div>
          <div class="title">
            <h2>{{ props.data.title }}</h2>
          </div>
          <div class="location flex gap-2.5">
            <span class="icon"><i :class="`fa-solid fa-lg fa-${props.data.icon.location.icon}`"/></span>
            <span class="location-text">{{ props.data.icon.location.location }}</span>
          </div>
          <div class="card-text">
            <p>{{ props.data.text }}</p>
          </div>
        </div>
        <div class="mt-5 hidden md:block">
          <div class="show-post flex justify-end">
            <nuxt-link to="" class="px-5 py-2.5 inline-flex items-center gap-2.5 "
                       :style="`background-color:${props.data.color} ;`">
              <i class="fa-solid fa-arrow-left flex items-center"/>
              <span>مراجعه</span>
            </nuxt-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
@import "@/assets/style/varible.scss";
.talent-discovery{
  z-index: 2;
}
.card {
  background-color: #FFFFFF;
  border-radius: $border-radius-card-talent-discovery;
  .title {
    color: $color-title-card-talent-discovery;
    font-weight: $fontWeight-title-text-talent-discovery;
    font-size: $fontSize-title-talent-discovery;
    font-family: $fontFamily-title-talent-discovery;
    line-height: $lineHeight-title-talent-discovery;
    display: -webkit-box;
    overflow: hidden;
    text-overflow: ellipsis;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 1;
    line-clamp: 1;
  }

  .icon {
    i {
      color: $color-icon-talent-discovery;
    }
  }

  .show-post a {
    border-radius: $border-radius-btn;
    overflow: hidden;
    color: $color-button-card-talent-discovery;
    position: relative;
    transition: 0.5s;

    &:after {
      transition: 0.5s;
      content: "";
      position: absolute;
      left: 0;
      top: 0;
      background-color: #31B978;
      height: 100%;
      width: 100%;
      transform: translateX(100%);
    }

    &:hover:after {
      border-radius: $color-button-hover-card-talent-discovery;
      transform: translateX(0);

    }
    &:hover{
      color: $color-button-card-talent-discovery;
    }
    i, span {
      z-index: 2;
    }
  }


  .date-text {
    color: $color-text-talent-discovery;
  }

  .location-text {
    color: $color-text-talent-discovery;
  }

  .card-text {
    color: $color-text-talent-discovery;
    display: -webkit-box;
    overflow: hidden;
    text-overflow: ellipsis;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 1;
    line-clamp: 1;
  }
}

.image {

  figure {
    width: 190px;
    height: 270px;
    mask-image: url("@/assets/image/shape/gallery.png");
    mask-repeat: no-repeat;
    mask-position: center;
    mask-size: cover;
    object-fit: cover;

    img {
      width: 100%;
      height: 100%;
      object-fit: cover;
    }
  }
}

.hover {
  transition: 0.3s;

  .show-post a {
    color: $color-button-hover-card-talent-discovery;
    background-color: #FFFFFF !important;
  }

  .icon {
    i {
      color: $color-text-card-hover-talent-discovery;
    }
  }

  .title {
    color: $fontWeight-title-hover-talent-discovery;
  }

  .card-text {
    color: $color-text-card-hover-talent-discovery;
  }

  .date-text {
    color: $color-text-card-hover-talent-discovery;
  }

  .location-text {
    color: $color-text-card-hover-talent-discovery;
  }
}


//responsive mode
@media screen and (max-width: 1024px) {
  .card-body{
    margin-top: 20px;
}
  .image{
    figure{
      width: 100%;
      mask-image: none;
    }
  }
  }
</style>