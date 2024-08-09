<script setup lang="ts">
import shapeMove from "~/composables/shapeMove.client"
import last_card_1 from "assets/image/face/last-card-1.jpg";
import last_card_2 from "assets/image/face/last-card-2.jpg";
import last_card_3 from "assets/image/face/last-card-3.jpg";
const breadcrumb ={
    title:"اخبار و رویداد ها",
  breadcrumb:[
    {
      name:"اخبار و رویداد ها "
    }
  ]
}
onMounted(()=>{
  shapeMove()
})
const categories = [
  {
    img:last_card_2,
    subject:"یادگیری",
    title:"تاریخ اولیه ایالات متحده",
    dateText:"مهر 26, 1402",
    commentCount:0,
    text:"لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است."
  },
  {
    img:last_card_1,
    subject:"علوم پایه",
    title:"آنچه جمع آوری کارت های بیسبال به من آموخت",
    dateText:"مهر 27, 1402",
    commentCount:0,
    text:"لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است."
  },
  {
    img:last_card_3,
    subject:"فن آوری",
    title:"آیا با بچه های دیگر خوب بازی می کنید؟",
    dateText:"مهر 25, 1402",

    commentCount:0,

    text:"لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است."
  },
  {
    img:last_card_1,
    subject:"علوم پایه",
    title:"آنچه جمع آوری کارت های بیسبال به من آموخت",
    dateText:"مهر 27, 1402",
    commentCount:0,
    text:"لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است."
  },
  {
    img:last_card_3,
    subject:"فن آوری",
    title:"آیا با بچه های دیگر خوب بازی می کنید؟",
    dateText:"مهر 25, 1402",

    commentCount:0,

    text:"لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است."
  },
  {
    img:last_card_2,
    subject:"یادگیری",
    title:"تاریخ اولیه ایالات متحده",
    dateText:"مهر 26, 1402",
    commentCount:0,
    text:"لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است."
  },
]
</script>

<template>
  <section>
  <breadcrumb-header-title :data="breadcrumb"/>
  </section>
<section>
<div class="flex flex-col gap-10 my-10">
  <div class="events flex flex-col justify-center items-center gap-2  ">
    <div class="event-sub-title"><span>رویدادها</span></div>
    <div class="event-title">
      <h3>رویداد های محبوب</h3>
    </div>
    <icons-brow/>
  </div>
  <div class="grid grid-cols-12 mx-auto w-[92%] md:w-[80%] lg:w-2/3 gap-5 lg:gap-10">
    <home-main-card-new-articles  v-for="(item , index) in categories" :key="index" :data="item" class="col-span-12 md:col-span-6 lg:col-span-4" />
  </div>
</div>
</section>
</template>

<style scoped lang="scss">
.event-sub-title {
  font-size: var(--title-xl);
  font-weight: var(--font-medium);
  @media screen and (max-width: 768px) {
    font-size: var(--title-lg);
  }
}

.event-title {
  font-size: var(--title-5xl);
  font-weight: var(--font-bolder);
  @media screen and (max-width: 768px) {
    font-size: var(--title-2xl);
  }
}
</style>
