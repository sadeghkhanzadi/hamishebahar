<script setup lang="ts">
import imgOne from '~/assets/image/teachers/1.webp'
import shapeMove from '~/composables/shapeMove.client'
import Brow from "~/components/icons/brow.vue";
const breadcrumb = {
  title:"صفحه معرفی مربی و مدرسین",
  breadcrumb: [
    {
      name: "صفحه معرفی مربی و مدرسین",
      url: ''
    },
  ]
}
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
onMounted(()=>{
  shapeMove()
})
</script>

<template>
  <div>
    <div class="header">
      <breadcrumb-header-title :data="breadcrumb"/>
    </div>
    <section>
      <div class=" w-[90%] sm:w-[80%] lg:w-8/12 max-w-screen-2xl mx-auto pb-5 mt-10">
        <div class="teachers-group  text-center flex flex-col gap-10 md:gap-20">
          <div class="teachers-header flex flex-col items-center justify-center gap-3 ">
            <div class="teachers-sub-title"><span>مدرس های ما </span></div>
            <div class="teachers-title "><h5>مربیان به نام ما </h5></div>
            <div><brow/></div>
          </div>
          <div class="grid grid-cols-12 gap-6 sm:gap-7  ">
            <cards-teachers class=" col-span-12 sm:col-span-6 lg:col-span-4 xl:col-span-4"
                            v-for="(item , index) in teachers" :key="index" :data="item" data-aos="fade-up"/>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<style scoped lang="scss">

.teachers-sub-title {
  font-size: var(--title-2xl);
  font-weight: var(--font-medium);
  @media screen and (max-width: 768px) {
    font-size: var(--title-lg);
  }
}
.teachers-title {
  font-size: var(--title-5xl);
  font-weight: var(--font-bolder);
  @media screen and (max-width: 768px) {
    font-size: var(--title-2xl);
  }
}
</style>
