<script setup >
const props = defineProps(['data', 'theme','education', 'talentDiscovery','articles','active'])
console.log(props.articles)
</script>

<template>
  <div class="content-container  flex items-center py-20 px-4 "
       :class="{nocloud: props.theme === 'noCloud' ,education:props.education ===true,talentDiscovery: props.talentDiscovery ===true ,articles: props.articles ===true , active:props.active === true}"
       v-if="props.data">
    <div class="cloudy-top"></div>
    <div class="content   mx-auto relative p-2 py-22 my-10">
      <div>
        <div class="text-center flex flex-col gap-3 mb-7 title-content">
          <div class="sub-title">
            <span v-if="props.data.subTitle">{{ props.data.subTitle }}</span>
          </div>
          <div class="title">
            <h3 v-if="props.data.title">{{ props.data.title }}</h3>
          </div>
          <div class="icon"><span></span></div>
        </div>
        <div class=" gap-10 grid grid-cols-1 md:grid-cols-2 lg:flex " v-if='props.data && props.theme==true'>
          <home-main-card-best-class-card :data="item" v-for="(item , index) in props.data.cards" :key="index" mode="card"/>
        </div>
        <div class="grid md:grid-cols-2 xl:grid-cols-3 gap-10" v-if="props.data">
          <home-main-card-active :data="item" v-for="(item , index) in props.data.plans" :key="index"/>
        </div>
        <div class="education-card flex flex-col gap-5">
          <home-main-card-education :data="item" v-for="(item , index) in props.data.collapse" :key="index"/>
        </div>
        <div v-if="props.talentDiscovery" class="grid  sm:grid-cols-2 gap-7">
          <home-main-card-talent-discovery :data="item" v-for="(item , index) in props.data.items" :key="index"/>
        </div>
        <div   class="grid grid-cols-1 sm:grid-cols-2 lg:flex gap-10">
          <home-main-card-new-articles  v-for="(item , index) in props.data.categories" :key="index" :data="item" />
        </div>
        <div class="shapes hidden xl:block" v-if='props.data'>
          <div :class="`shape shape-${item.name} absolute`" v-for="(item,index) in props.data.shapes" :key="index">
          <span>
            <img :src="item.img" alt="">
          </span>
          </div>
        </div>
      </div>
      <div>
        <div class="image" v-if="props.education===true">
          <figure>
            <img :src="props.data.img" alt="">
          </figure>
        </div>
      </div>
    </div>
    <div class="cloudy-bottom"></div>
  </div>
</template>

<style scoped lang="scss">
@import '@/assets/style/varible.scss';

.content-container {
  background-color: #F5F1EB;
  overflow: hidden;
  position: relative;

  &:after {
    content: url("@/assets/image/shape/gray-cloud.png");
    background-size: cover;
    position: absolute;
    right: 50%;
    top: -10px;
    transform: rotate(180deg) translate(-50%);
  }

  &:before {
    content: url("@/assets/image/shape/gray-cloud.png");
    background-size: cover;
    position: absolute;
    right: 50%;
    bottom: -10px;
    transform: translate(50%);
  }
}

.content {
  max-width: 1200px;

}

.sub-title {
  font-size: $fontSize-normal-section-three;
  font-weight: $fontWeight-subTitle-section-three;
  color: $color-subTitle-section-three;
}

.title {
  font-size: $fontSize-Title-section-three;
  font-weight: $fontWeight-Title-section-three;
  font-family: $fontFamily-Title-section-three;
  line-height: $lineHeight-Title-section-three;
  color: $color-Title-section-three;
}

.shape {
  opacity: 0.5;
}

.shape-pen {
  top: 0;
  left: -15%;
}

.shape-book {
  bottom: -5%;
  left: -5%;
  z-index: 0;
}

.shape-calender {
  top: 5%;
  right: -7%;
}

figure {
  img {
    mask-image: url("@/assets/image/face/circle-face.png");
    mask-size: contain;
    mask-repeat: no-repeat;
    mask-position: center;
  }
}

// theme --> hide cloud
.content-container.nocloud {
  background: none;
  height: auto;
  min-height: 50vh;

  &::after {
    display: none;
  }

  &::before {
    display: none;
  }
}

.education {
  background-color: $bg-color-activities;
  min-height: 70vh !important;

  .content {
    display: grid;
    grid-template-columns:repeat(2, 1fr);
    align-content: center;
    align-items: center;
    gap: 30px;
  }

  .title-content {
    text-align: right;
    justify-content: center;
  }

  .image {
    //grid-column: 2/span 1;
    //order:-1;
  }

  .title-content {
    display: flex;
    flex-direction: column;
    gap: 1.5rem;

    .title {
      line-height: 50px;
    }
  }

  .shape-sun {
    position: absolute;
    left: 15%;
    z-index: 1;
    opacity: 1;
    top: 15%;
  }

  .shape-circle {
    position: absolute;
    left: 5%;
    opacity: 1;
    top: 40%;
  }

  .shape-dot {
    position: absolute;
    left: 40%;
    opacity: 1;
    bottom: 5%;
  }

}

.talentDiscovery.content-container {
  position: relative;
  flex-direction: column;
  justify-content: center;
  background-color: #E9EFF1 !important;
  min-height: 120vh !important;
  padding-bottom: 8rem;
  &:after {
    content: url("@/assets/image/shape/cloud-blue-sky.png");
    position: absolute !important;
    right: 50%;
    top: -10px;
    transform: rotate(180deg) translate(-50%);
  }


  .shape-book {
    top: 10%;
  }

  .shape-laboratory {
    bottom: 0;
    right: -5%;
  }
}
.articles{
  min-height: 80vh !important;
  padding:0 20px;
}

//responsive mode

@media screen and (max-width: 996px) {

  .education{
    padding: 20px !important;
    .content{
      display: grid;
      grid-template-columns: repeat(1,1fr);
      .img{
        display: flex;
        justify-content: center;
      }
    }
  }
  .active{
    padding: 0 20px;
    .content{
      padding: 0 ;
    }
  }
}


</style>