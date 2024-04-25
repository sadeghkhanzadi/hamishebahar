<script setup lang="ts">
import img1 from "@/assets/image/face/1.jpg"
import img2 from "@/assets/image/face/2.jpg"
import img3 from "@/assets/image/face/3.jpg"
import face from "@/assets/image/face/circle-face.webp"
import babyGirl_1 from "@/assets/image/face/telent-face.jpg"
import babyGirl_2 from "@/assets/image/face/telent-face-2.jpg"
import babyGirl_3 from "@/assets/image/face/telent-face-3.jpg"
import babyGirl_4 from "@/assets/image/face/telent-face-4.jpg"
import last_card_1 from "@/assets/image/face/last-card-1.jpg"
import last_card_2 from "@/assets/image/face/last-card-2.jpg"
import last_card_3 from "@/assets/image/face/last-card-3.jpg"

import pen from "@/assets/image/shape/pen.png"
import sun from "@/assets/image/shape/sun.png"
import circle from "@/assets/image/shape/blue-dot.png"
import dot from "@/assets/image/shape/dot.png"
import book from "@/assets/image/shape/book.png"
import calender from "@/assets/image/shape/calender.png"
import lab from "@/assets/image/shape/labratoar.png"

const data = {
  title: "یک درس را برای شروع انتخاب کنید",
  subTitle: "کلاس های محبوب ",
  shapes: [
    {
      img: pen,
      name: "pen"
    },
    {
      img: book,
      name: "book"
    },
    {
      img: calender,
      name: "calender"
    },
  ],
  cards: [
    {
      price: "رایگان",
      color: "#FF5B5C",
      img: img1,
      type: "انگیزشی",
      subject: "دوره نهایی طراحی _ مبتدی تا پیشرفته ",
      info: [
        {
          lesson: 0,
          icon: "book"
        },
        {
          count: 500,
          icon: "user"
        }
      ],
      text: "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده."
    },
    {
      price: " 30 ریال",
      color: "#8E56FF",
      img: img2,
      type: "انگیزشی",
      subject: "تکنیک های آرام بخش قدرتمند برای کودکان و نوجوانان ",
      info: [
        {
          lesson: 0,
          icon: "book"
        },
        {
          count: 300,
          icon: "user"
        }
      ],
      text: "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده."
    },
    {
      price: "رایگان",
      color: "#1AB69D",
      img: img3,
      type: "انگیزشی",
      subject: "مهارت های فرزندپروری برای تربیت فرزندان مسئولیت پذیر ",
      info: [
        {
          lesson: 0,
          icon: "book"
        },
        {
          count: 160,
          icon: "user"
        }
      ],
      text: "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده."
    },
  ]
}
const education = {
  img: face,
  subTitle: "اموزش برای همه ",
  title: "دورانی که با بچه ها در جایی که هستند ملاقات می کنیم",
  collapse: [
    {
      title: "مدرسه اطفال ( 1 - 2 سال )",
      color: "#8E56FF",
      text: "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است.",
    },
    {
      title: "مهدکودک ( 2 - 3 سال )",
      color: "#F8941F",
      text: "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است.",
    },
    {
      title: "پیش دبستانی ( 3 - 5 سال )",
      color: "#1AB69D",
      text: "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است.",
    },
  ],
  shapes: [
    {
      img: sun,
      name: "sun"
    },
    {
      img: dot,
      name: "dot"
    },
    {
      img: circle,
      name: "circle"
    },
  ],
}
const activities = {
  title: 'از فعالیت های ما خوشحال خواهید شد',
  subTitle: 'فعالیت های ما ',
  plans: [
    {
      icon: "tent",
      color: "",
      bg: "",
      name: "کمپ تابستانی",
      text: "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است."
    },
    {
      icon: "hand-sparkles",
      color: "",
      bg: "",
      name: "تمیز و پاکیزه",
      text: "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است."
    },
    {
      icon: "futbol",
      color: "",
      bg: "",
      name: "مجموعه ورزشی",
      text: "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است."
    },
    {
      icon: "music",
      color: "",
      bg: "",
      name: "کلاب موزیک",
      text: "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است."
    },
    {
      icon: "landmark-dom",
      color: "",
      bg: "",
      name: "موزه گردی",
      text: "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است."
    },
    {
      icon: "square-root-variable",
      color: "",
      bg: "",
      name: "کلاب ریاضی",
      text: "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است."
    },
  ]
}
const talentDiscovery = {
  title: 'به رویداد آینده ما بپیوندید',
  subTitle: 'زویداد ها را کشف کنید  ',
  items: [
    {
      icon: {
        location: {
          icon: "location-dot",
          location: "Ohio City, USA"
        },
        calender: {
          icon: "calendar-days",
          time: "آذر 2, 1403"
        }
      },
      color: "#EE4A62",
      img: {
        name: "baby girl",
        link: babyGirl_1
      },
      bg: "#EE4A62",
      title:"کنفرانس سالانه موزیک در شیراز",
      text: "این راست نیست که هرچه عاشق‌ تر باشی بهتر درک می‌کنی...."
    },
    {
      icon: {
        location: {
          icon: "location-dot",
          location: "Ontario City, Canada"
        },
        calender: {
          icon: "calendar-days",
          time: "دی 8, 1403"
        }
      },
      color: "#8E56FF",
      img: {
        name: "baby girl",
        link: babyGirl_2
      },
      bg: "#8E56FF",
      title:"جشن سالانه حیوانات خانگی",
      text: "این راست نیست که هرچه عاشق‌ تر باشی بهتر درک می‌کنی...."
    },
    {
      icon: {
        location: {
          icon: "location-dot",
          location: "London City, UK"
        },
        calender: {
          icon: "calendar-days",
          time: "آبان 30, 1403"
        }
      },
      color: "#F8941F",
      img: {
        name: "baby girl",
        link: babyGirl_3
      },
      bg: "#F8941F",
      title:"سمینار راه های جدید درآمد زایی در ایران",
      text: "این راست نیست که هرچه عاشق‌ تر باشی بهتر درک می‌کنی...."
    },
    {
      icon: {
        location: {
          icon: "location-dot",
          location: "Munich City, Germany"
        },
        calender: {
          icon: "calendar-days",
          time: "مهر 15, 1403"
        }
      },
      color: "#1AB69D",
      img: {
        name: "baby girl",
        link: babyGirl_4
      },
      bg: "#1AB69D",
      title:"دوره مهارت های پول سازی در تلگرام",
      text: "این راست نیست که هرچه عاشق‌ تر باشی بهتر درک می‌کنی...."
    },
  ],
  shapes:[
    {
      name:"book",
      img:book
    },
    {
      name:"laboratory",
      img:lab
    },
  ],
  cloudy:{
    img:""
  }
}
const articles ={
  title:"از مطالب جدید ما دیدن کنید",
  subTitle:"مقالات",
  categories: [
    {
      img:last_card_1,
      subject:"علوم پایه",
      title:"آنچه جمع آوری کارت های بیسبال به من آموخت",
      dateText:"مهر 27, 1402",
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
    {
      img:last_card_3,
      subject:"فن آوری",
      title:"آیا با بچه های دیگر خوب بازی می کنید؟",
      dateText:"مهر 25, 1402",

      commentCount:0,

      text:"لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است."
    },
  ]

}
</script>

<template>
  <div>
    <main>
      <article>
        <section>
          <home-main-section-one/>
        </section>
        <section>
          <home-main-section-two/>
        </section>
        <section>
          <home-main-best-class :data="data" :theme="true"/>
        </section>
        <section>
          <home-main-best-class :data="activities" theme="noCloud"/>
        </section>
        <section>
          <home-main-best-class :data="education" :education="true" theme="noCloud"/>
        </section>
        <section>
          <home-main-best-class :data="talentDiscovery" :talentDiscovery="true"/>
        </section>
        <section>
          <home-main-best-class  :data="articles" :articles="true" theme="noCloud"/>
        </section>
        <section>
          <home-main-get-news/>
        </section>
      </article>
    </main>
  </div>
</template>

<style scoped>

</style>