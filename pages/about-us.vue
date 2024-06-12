<script setup lang="ts">
import img from '~/assets/image/breadcrumb/edublink-breadcrumb-bg.webp'
import shapeMove from "~/composables/shapeMove.client.js";
import imgOne from "assets/image/teachers/1.webp";

const breadcrumb = {
  title: "این عنوان میتواند از عنوان صفحه مثتثنی باشد !",
  breadcrumb: [
    {
      name: 'درباره ما'
    }
  ]
}
onMounted(() => {
  shapeMove()
})
const data = {
  text: 'لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است',
  titleInfo: [
    {
      text:
          "کلاس های انعطاف پذیر"
    },
    {
      text: "حالت کلاس آفلاین"
    },
    {
      text: "پشتیبانی مربی"
    },

  ]
}
const whyUs = [
  {
    img:"fa-solid fa-medal",
    color:'red',
    title:'دوره های با کیفیت بالا\n',
    text:'لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است',
  },
  {
    img:"fa-regular fa-address-card",
    color:'blue',
    title:'دسترسی دائمی\n',
    text:'لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است',
  },
  {
    img:"fa-solid fa-chalkboard-user",
    color:'crimson',
    title:'مدرسین نخبه\n',
    text:'لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است',
  },
]
const teachers = [
  {
    name: 'ممد نصاب',
    job: "نصاب ماهواره",
    img: imgOne,
    url: "1",
    socialApp:{
      twitter:"/",
      instagram:"/",
      linkedin:"/"
    }
  },
  {
    name: 'اصغر بقال',
    job: "آزاد",
    img: imgOne,
    url: "2",
    socialApp:{
      twitter:"/",
      instagram:"/",
      linkedin:"/"
    }
  },
  {
    name: 'سمیه',
    job: "آبجی رقیه",
    img: imgOne,
    url: "3",
    socialApp:{
      twitter:"/",
      instagram:"/",
      linkedin:"/"
    }
  },
  {
    name: 'نقی رمال',
    job: "فال گیر",
    img: imgOne,
    url: "4",
    socialApp:{
      twitter:"/",
      instagram:"/",
      linkedin:"/"
    }
  },
  {
    name: 'سمیه',
    job: "آبجی رقیه",
    img: imgOne,
    url: "5",
    socialApp:{
      twitter:"/",
      instagram:"/",
      linkedin:"/"
    }
  },
  {
    name: 'سی سی رمال',
    job: "فال گیر",
    img: imgOne,
    url: "6",
    socialApp:{
      twitter:"/",
      instagram:"/",
      linkedin:"/"
    }
  },
]
</script>

<template>
  <div class="overflow-hidden">
    <header>
      <breadcrumb-Header-title :img="img" :data="breadcrumb" data-aos="fade-down"/>
    </header>
    <main>
      <section>
        <div class="about-us-container mx-auto  flex flex-col lg:flex-row gap-10   my-[10vh]">
          <div class="card-body" data-aos="fade-up">
            <div class="content" >
              <div class="sub-title"><span>درباره ما</span></div>
              <div class="title">
                ما دوره ها را با
                <span>بهترین کیفیت </span>
                به شما ارائه میدهیم.
              </div>
              <div class="icon mt-2">
                <icons-brow/>
              </div>
            </div>
            <div>
              <div class="mt-4">
                <p>{{ data.text.substring(0, 91) }}</p>
              </div>
              <ul class="flex flex-col gap-2 mt-4">
                <li v-for="(item , index) in data.titleInfo" class="flex gap-2" :key="index">
                  <span class="icon"><i class="fa-solid fa-check text-yellow-500 fa-lg"></i></span>
                  <span class="info">{{ item.text }}</span>
                </li>
              </ul>
            </div>
          </div>
          <div class="card-header mt-5 relative">
            <div class="img" >
              <div class="under-image max-w-[520px] overflow-hidden rounded-lg " >
                <img src="~/assets/image/about-us/ubder.webp "  alt="family-us"></div>
              <div
                  class="front-image max-w-[150px] overflow-hidden rounded-lg absolute z-[1] top-[-20%] left-0 transform translate-x-[-40%]">
                <img src="~/assets/image/about-us/top.webp" alt="me"></div>
            </div>
            <div class="shape" data-aos="fade-up">
              <div
                  class="blue-dot absolute shape-move z-[-1] left-[-10%] transform scale-110 opacity-50 bottom-[-15%] ">
                <img src="~/assets/image/shape/blue-dot.png" alt=" blue-dot shape"></div>
              <div class="red-dot absolute shape-move z-[-1] right-[-10%] transform scale-75 opacity-50 top-[-15%]"><img
                  src="~/assets/image/shape/red-dot.png" alt="red-dot shape"></div>
              <div class="absolute z-[1] right-[-10%] bottom-[-25%] transform scale-75 "><img
                  src="~/assets/image/shape/circle-width-shadow.png" alt="circle-shadow"></div>
              <div
                  class="Irregular-circle absolute shape-move z-[-1] right-[-20%] transform scale-75 opacity-50 top-[-20%] ">
                <img src="~/assets/image/shape/Irregular%20circle.png" alt="Irregular-circle"></div>
            </div>
          </div>
        </div>
      </section>
      <section>
        <div class="why-choose-us h-[45vh] flex flex-col items-center relative" data-aos="fade-up">
          <div>
            <div class="why-choose-us-body flex flex-col mt-[20%] items-center gap-2">
              <div class="why-choose-us-sub-title text-center" data-aos="fade-up">چرا همیشه بهار را انتخاب کنیم ؟</div>
              <div class="why-choose-us-title text-center" data-aos="fade-up">
                <h3>
                  بهترین
                  <span>جنبه سودمندی</span>
                  در کنار شما
                  <br>همیشه بهار
                </h3>
              </div>
              <div class="why-choose-us-icon " data-aos="fade-up">
                <icons-brow/>
              </div>
            </div>
          </div>
          <div class="why-choose-us-content flex justify-center">
            <div class="grid grid-cols-12 gap-7 absolute w-1/2  top-[30%] transform translate-y-[50%] ">
              <about-us-card class="col-span-4" v-for="(item , index) in whyUs" :key="index" :data="item" data-aos="fade-up"/>
            </div>
          </div>
          <div class="shapes " data-aos="fade-up">
            <div class="img shape-move absolute top-1/4 left-0" ><img src="~/assets/image/shape/red-square.png" alt="red-square"></div>
            <div class="img shape-move absolute top-1/4 right-0" ><img src="~/assets/image/shape/blue-dot.png" alt="blue-dot"></div>
            <div class="img shape-move absolute bottom-16 left-20 border rounded-full border-gray-300 h-[60%] w-[13%]" ></div>
          </div>
        </div>
      </section>
      <section>
        <div class="teachers pt-[20vh] pb-[5vh] overflow-hidden">
          <div class="teachers-body w-2/3 mx-auto flex flex-col justify-center items-center">
            <div class="teachers-content flex flex-col justify-center items-center gap-2">
              <div class="teachers-content-sub-title" data-aos="fade-up"><h4>مربیان</h4></div>
              <div class="teachers-content-title" data-aos="fade-up"><h4>محبوب ترین مدرسان ما </h4></div>
              <div class="teachers-content-icon" data-aos="fade-up"><icons-brow/></div>
            </div>
            <div class="teachers-item grid grid-cols-12 gap-8 py-10">
              <cards-teachers class=" col-span-12 md:col-span-6 lg:col-span-4 xl:col-span-4"
                              v-for="(item , index) in teachers.splice(0,3)" :key="index" :data="item" data-aos="fade-up"/>
            </div>
          </div>
        </div>
      </section>
    </main>
  </div>
</template>

<style scoped lang="scss">

.about-us-container {
  max-width: 900px;

  .card-body {
    .sub-title {
      color: #808080;
    }

    .title {
      font-size: 28px;
      font-weight: 700;

      span {
        color: #ee4a62;
      }
    }

    p {
      font-size: 0.8rem;
      color: #808080;
    }
  }

  .card-header {
    .Irregular-circle {
      scale: 0.7 !important;
    }

    .red-dot {
      scale: 0.9 !important;
    }

    .blue-dot {
      scale: 1.1 !important;
    }
  }
}

.why-choose-us {
  background: #f7f5f2;
  position: relative;
  //overflow: hidden;

  &:after {
    content: "";
    position: absolute;
    bottom: -40%;
    height: 50%;
    width: 100%;
    background: #FFFFFF;
    transform: rotate(-2deg);
  }

  .why-choose-us-sub-title {
    color: #808080;
  }

  .why-choose-us-title {
    font-size: 28px;
    font-weight: 700;

    span {
      color: #ee4a62;
    }
  }
  .why-choose-us-content{
   & > div{
     z-index: 1;
   }
  }
  .img{
    &:nth-child(1){
      scale: 0.7;
    }
    &:nth-child(2){
      scale: 0.7;
    }
  }
}

.teachers{
  .teachers-content-sub-title{
    font-size: 1rem;
    color: #808080;
  }
  .teachers-content-title{
    font-size: 1.7rem;
    font-weight: 700;
  }
}
</style>