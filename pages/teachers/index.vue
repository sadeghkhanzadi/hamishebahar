<script setup lang="ts">
import imgOne from '~/assets/image/teachers/1.webp'
import imgTwo from '~/assets/image/teachers/2.jpeg'
import imgThree from '~/assets/image/teachers/3.jpeg'
import imgFour from '~/assets/image/teachers/4.jpeg'

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
  <div>
    <div class="header">
      <breadcrumb-header-title/>
    </div>
    <section>
      <div class="container mx-auto pb-5 mt-10">
        <div class="teachers-group lg:w-8/12 mx-auto text-center flex flex-col gap-20">
          <div class="teachers-header">
            <div class="teachers-sub-title"><span>مدرس های ما </span></div>
            <div class="teachers-title mt-5"><h5>مربیان به نام ما </h5></div>
          </div>
          <div class="grid grid-cols-12 gap-10 md:gap-7  ">
            <cards-teachers class=" col-span-12 md:col-span-6 lg:col-span-4 xl:col-span-4"
                            v-for="(item , index) in teachers" :key="index" :data="item"/>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<style scoped lang="scss">
.teachers-sub-title {
  font-size: 15px;
  font-weight: 500;
}

.teachers-title {
  font-size: 28px;
  font-weight: 700;

  &:after {
    content: "";
  }
}
</style>