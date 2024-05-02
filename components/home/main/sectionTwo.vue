<script setup lang="ts">
import sun from "@/assets/image/shape/sun.png"
import circle from "@/assets/image/shape/blue-dot.png"
import paint from "@/assets/image/shape/paint.png"
import face from "@/assets/image/face/1.png"
import subFace from "@/assets/image/face/2.png"

const data = {
  title: "به بهترین مرکز پیش دبستانی خوش آمدید",
  topTitle: "خوش آمدید",
  subTitleIcon: "",
  text: "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است",
  btn: "همین الان ثبت نام کنید",
  section: [
    {
      title: "اهداف ما",
      text: "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است."
    },
    {
      title: "ماموریت ما",
      text: "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است."
    },
  ],
  sectionTwo: {
    titleOne: "سرویس رایگان",
    titleTwo: "بازی های فضای باز",
    text: "از امروز برای آینده کدکتان برنامه ریزی کنید. از امروز برای آینده کدکتان برنامه ریزی کنید"
  },
  picture: {
    sun: sun,
    paint: paint,
    circle: circle,
    face: face,
    subFace: subFace
  }

};


</script>

<template>
  <div class="content max-w-screen-xl grid lg:grid-cols-2 mx-auto min-h-[80vh] items-center px-5 md:gap-32 lg:gap-0 pb-10 lg:pb-32">
    <section class="grid order-1">
      <div class="welcome-text ">
        <div class="card ">
          <div class="card-header flex flex-col gap-3" data-aos="fade-up">
            <div class="card-welcome"><span>{{ data.topTitle }}</span></div>
            <div class="card-welcome-title"><h1>{{ data.title }}</h1></div>
            <div class="icon"><span></span></div>
            <div class="card-welcome-text">{{ data.text }}</div>
          </div>
          <div class="card-body grid gap-y-3 md:grid-cols-2 md:gap-y-10  gap-x-5"  data-aos="fade-up">
            <div class="card-welcome-info mt-7 " v-for="(item , index ) in data.section" :key="index">
              <div class="card-info-title"><h5>{{ item.title }}</h5></div>
              <div class="card-info-text"><p>{{ item.text }}</p></div>
            </div>
            <div class="flex flex-col gap-3 card-support "  data-aos="fade-up">
              <div class="flex items-center gap-2" >
                <span><i class="fa-solid fa-check text-red-600 fa-lg"></i></span>
                <span class="title">{{ data.sectionTwo.titleOne }}</span>
              </div>
              <div class="flex items-center gap-2">
                <span><i class="fa-solid fa-check text-red-600 fa-lg"></i></span>
                <span class="title">{{ data.sectionTwo.titleTwo }}</span>
              </div>
            </div>
            <div>
              <div class="card-support"  data-aos="fade-up">
                <p>{{ data.sectionTwo.text }}</p>
              </div>
            </div>
          </div>
          <div class="card-footer">
            <div class="more-class mt-10"  data-aos="fade-up-left">
              <nuxt-link to="" class="flex  items-center gap-2">
                <span class="flex items-center"><i class="fa-solid fa-arrow-left"></i></span>
                <span>{{ data.btn }}</span>
              </nuxt-link>
            </div>
          </div>
        </div>
      </div>
    </section>
    <section>
      <div class="relative flex flex-col justify-center items-center">
        <div class="picture-kid relative">
          <span><img :src="data.picture.face" alt="کودک یک"></span>
          <span class="absolute top-1/2 lg:left-0 left-[-35%] hidden md:block"><img :src="data.picture.subFace" alt="کودک دو"></span>
        </div>

        <div class="shapes hidden lg:block">
          <div class="shape-sun absolute bottom-[50px] right-[-40px]">
            <span><img :src="data.picture.sun" alt="خورشید"></span>
          </div>
          <div class="shape-circle shape-move absolute bottom-[-60px] right-5 z-[-1]">
            <span><img :src="data.picture.circle" alt="دایره"></span>
          </div>
          <div class="circle-paint  absolute top-1/3 left-28 z-[-1]">
            <span><img :src="data.picture.paint" alt="بادبادک"></span>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<style scoped lang="scss">
@import "@/assets/style/_varible.scss";
.card-welcome{
  color: $welcome-color;
  font-size: $fontSize-text-section-two;
}
.card-welcome-title {
  color: $color-title-section-two;
  font-size: $font-size-title-section-two;
  line-height: $lineHeight-title-section-two;
}

.card-info-title {
  font-size: $font-size-title-card-section-two;
  font-weight: $font-weight-title-card;
  font-family: $yekan;
  line-height: $lineHeight-title-card-section-two;
  margin-bottom: 20px;
}

.card-welcome-text, .card-info-text {
  font-size: $fontSize-text-section-two;
  color: $color-text-section-two;
  line-height: $normal-line-height;
}

.card-info-text {
  text-align: justify;
}

.shape-sun {
  animation: rotate 15s linear;
}

.card-support {
  p{
    font-size: $fontSize-text-section-two;
    font-weight: 500;
    line-height: $normal-line-height;
    color: $color-title-section-two;
  }
  .title {
    font-size: $fontSize-text-section-two;
    font-weight: 500;
    line-height: $normal-line-height;
    color: $color-title-section-two;
  }
}
.more-class {
  a {
    display: inline-flex;
    gap:10px;
    align-items: center;

    background: $button-color;
    padding: 0.8rem 1.5rem;
    border-radius:$border-radius-btn;
    transition: all 0.5s;
    color: $text-color-white;
    &:hover{
      border-radius: $border-radius-btn-hover;
      background: $linearGradient-button;
      transition: 0.5s;
    }
  }

}
.picture-kid{
 & span:nth-child(2){
   animation: transform 4s  linear infinite ;
   animation-delay: 3s;
 }
}

@keyframes rotate {
  from {
    rotate: 0;
  }
  to {
    rotate: 360deg;
  }
}
@keyframes transform {
  10% , 30% , 70%,90%{
    transform: translateY(20px);
  }
  50%{
    transform: translateY(0);
  }
}

</style>