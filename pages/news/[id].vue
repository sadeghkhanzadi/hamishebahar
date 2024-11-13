<script setup lang="ts">
import shapeMove from "~/composables/shapeMove.client";
import imgOne from "assets/image/teachers/1.webp";

const breadcrumb = {
  title: "اخبار و رویداد ها",
  breadcrumb: [
    {
      name: "جزییات خبر "
    }
  ]
}
onMounted(() => {
  shapeMove()
})
const teachers = [
  {
    name: 'حسین خانزادی',
    job: "افسر نیروی دریایی",
    img: imgOne,
    url: "1",
    socialApp: {
      twitter: "/",
      instagram: "/",
      linkedin: "/"
    }
  },
  {
    name: 'مریم ادیبی',
    job: "معلم",
    img: imgOne,
    url: "2",
    socialApp: {
      twitter: "/",
      instagram: "/",
      linkedin: "/"
    }
  },
  {
    name: 'صادق خانزادی ',
    job: "برنامه نویس",
    img: imgOne,
    url: "3",
    socialApp: {
      twitter: "/",
      instagram: "/",
      linkedin: "/"
    }
  },
  {
    name: 'احمدی نژاد',
    job: "رئیس جمهور سابق",
    img: imgOne,
    url: "4",
    socialApp: {
      twitter: "/",
      instagram: "/",
      linkedin: "/"
    }
  },
  {
    name: 'سمیه',
    job: "آبجی رقیه",
    img: imgOne,
    url: "5",
    socialApp: {
      twitter: "/",
      instagram: "/",
      linkedin: "/"
    }
  },
  {
    name: 'سی سی رمال',
    job: "فال گیر",
    img: imgOne,
    url: "6",
    socialApp: {
      twitter: "/",
      instagram: "/",
      linkedin: "/"
    }
  },
]

</script>

<template>
  <section>
    <header>
      <breadcrumb-header-title :data="breadcrumb"/>
    </header>
    <main class=" w-[95%] md:w-[80%] max-w-screen-2xl  mx-auto mt-[5%]">
      <div>
        <div class="image">
          <figure class="w-full overflow-hidden rounded-lg">
            <img class="w-full object-cover max-h-[550px]" src="~/assets/image/news/course-13.jpg" alt="">
          </figure>
        </div>
        <div class="grid grid-cols-12 mt-16">
          <div class="content col-span-12 md:col-span-9 pl-5 md:pl-8 ">
            <h2>درباره رویداد</h2>
            <p class="text-justify text-[#515151]"> این راست نیست که هرچه عاشق‌ تر باشی بهتر درک می‌کنی. همه‌ی آنچه عشق
              و عاشقی از من می‌ خواهد فقط درکِ این حکمت است: دیگری نشناختنی است؛ ماتیِ او پرده‌ی ابهامی به روی یک راز
              نیست، بل گواهی است که در آن بازیِ بود و نمود هیچ‌ جایی ندارد. پس من در مسرتِ عشق ورزیدن به یک ناشناس غرق
              می‌شوم، کسی که تا ابد ناشناس خواهد ماند. سِیری عارفانه: من آن‌چه را نمی‌شناسم می‌شناسم…!

              مداد رنگی ها مشغول بودند به جز مداد سفید، هیچکس به او کار نمیداد، همه میگفتند : تو به هیچ دردی نمیخوری، یک
              شب که مداد رنگی ها تو سیاهی شب گم شده بودند، مداد سفید تا صبح ماه کشید مهتاب کشید و انقدر ستاره کشید که
              کوچک و کوچکتر شد صبح توی جعبه مداد رنگی جای خالی او با هیچ رنگی پر نشد، به یاد هم باشیم شاید فردا ما هم در
              کنار هم نباشیم</p>
            <p class="text-justify text-[#515151]"> این راست نیست که هرچه عاشق‌ تر باشی بهتر درک می‌کنی. همه‌ی آنچه عشق
              و عاشقی از من می‌ خواهد فقط درکِ این حکمت است: دیگری نشناختنی است؛ ماتیِ او پرده‌ی ابهامی به روی یک راز
              نیست، بل گواهی است که در آن بازیِ بود و نمود هیچ‌ جایی ندارد. پس من در مسرتِ عشق ورزیدن به یک ناشناس غرق
              می‌شوم، کسی که تا ابد ناشناس خواهد ماند. سِیری عارفانه: من آن‌چه را نمی‌شناسم می‌شناسم…!

              مداد رنگی ها مشغول بودند به جز مداد سفید، هیچکس به او کار نمیداد، همه میگفتند : تو به هیچ دردی نمیخوری، یک
              شب که مداد رنگی ها تو سیاهی شب گم شده بودند، مداد سفید تا صبح ماه کشید مهتاب کشید و انقدر ستاره کشید که
              کوچک و کوچکتر شد صبح توی جعبه مداد رنگی جای خالی او با هیچ رنگی پر نشد، به یاد هم باشیم شاید فردا ما هم در
              کنار هم نباشیم</p>
            <p class="text-justify text-[#515151]"> این راست نیست که هرچه عاشق‌ تر باشی بهتر درک می‌کنی. همه‌ی آنچه عشق
              و عاشقی از من می‌ خواهد فقط درکِ این حکمت است: دیگری نشناختنی است؛ ماتیِ او پرده‌ی ابهامی به روی یک راز
              نیست، بل گواهی است که در آن بازیِ بود و نمود هیچ‌ جایی ندارد. پس من در مسرتِ عشق ورزیدن به یک ناشناس غرق
              می‌شوم، کسی که تا ابد ناشناس خواهد ماند. سِیری عارفانه: من آن‌چه را نمی‌شناسم می‌شناسم…!

              مداد رنگی ها مشغول بودند به جز مداد سفید، هیچکس به او کار نمیداد، همه میگفتند : تو به هیچ دردی نمیخوری، یک
              شب که مداد رنگی ها تو سیاهی شب گم شده بودند، مداد سفید تا صبح ماه کشید مهتاب کشید و انقدر ستاره کشید که
              کوچک و کوچکتر شد صبح توی جعبه مداد رنگی جای خالی او با هیچ رنگی پر نشد، به یاد هم باشیم شاید فردا ما هم در
              کنار هم نباشیم</p>
          </div>
          <div class="event-details col-span-12 md:col-span-3 box-shadow p-5 h-fit rounded-lg">
            <h4>اطلاعات رویداد</h4>
            <div class="info">
              <ul>
                <li class="flex justify-between items-center py-5">
                  <div class="flex gap-1.5 items-center ">
                    <i class="fa-solid fa-sack-dollar"/>
                    <span>قیمت :</span>
                  </div>
                  <span> تومان 450 </span>
                </li>
                <li class="flex justify-between items-center py-5">
                  <div class="flex gap-1.5 items-center ">
                    <i class=" fa-solid fa-user-group"/>
                    <span>ظرفیت :</span>
                  </div>
                  <span>50</span>
                </li>
                <li class="flex justify-between items-center py-5">
                  <div class="flex gap-1.5 items-center ">
                    <i class="fa-solid fa-lock"/>
                    <span>رزرو شده :</span>
                  </div>
                  <span>15</span>
                </li>
                <li class="flex justify-between items-center py-5">
                  <div class="flex gap-1.5 items-center ">
                  <span>
                    <i class=" fa-solid fa-user"/>
                  </span>
                    <span>جلسه پرسش و پاسخ :</span>
                  </div>
                  <span>دارد</span>
                </li>
              </ul>
            </div>
            <div class="alert bg-[#fdb494] mt-2 py-4 rounded">
              <p class="text-gray-400 text-sm text-center md:text-base">این رویداد منقضی شده است .</p>
            </div>
          </div>
        </div>
      </div>
      <div class="teachers mt-[10%]">
        <h4 class="text-lg lg:text-2xl font-bold"> سخنران رویداد</h4>
        <div class="teachers-item grid grid-cols-12 gap-8 py-16">
          <cards-teachers class=" col-span-6 md:col-span-3 "
                          v-for="(item , index) in teachers.splice(0,4)" :key="index" :data="item" data-aos="fade-up"/>
        </div>
      </div>
    </main>

  </section>
</template>

<style scoped lang="scss">
.box-shadow {
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
}

li:not(:last-child) {
  border-bottom: 1px solid rgba(0, 0, 0, .1);
}

.alert {
  border-right: 3px solid #c35a2c;

  p {
    color: #515151;
  }
}

</style>
