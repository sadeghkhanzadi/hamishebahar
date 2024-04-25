<script setup lang="ts">
import enamdOne from "@/assets/image/face/enamad.png"
import enamdTwo from "@/assets/image/face/saman.png"

const data = ref({
  brand: {
    name: "همیشه بهار ",
    body: "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است."
  },
  samandehi: {
    imgOne: enamdOne,
    imgTwo: enamdTwo
  },
  bestLink: [
    {
      name: "تماس با ما ",
      link: " /"
    },
    {
      name: "گالری تصاویر",
      link: " /"
    },
    {
      name: "اخبار و مقالات",
      link: " /"
    },
    {
      name: "پرسش های متداول",
      link: " /"
    },
    {
      name: "صفخه بزودی",
      link: " /"
    },
    {
      name: "ورود / ثبت نام",
      link: " /"
    },
  ],
  pOnline: [
    {
      name: "درباره ما ",
      link: "/"
    },
    {
      name: "دوره ها",
      link: "/"
    },
    {
      name: "مدرس ها",
      link: "/"
    },
    {
      name: "رویداد ها",
      link: "/"
    },
    {
      name: "جزئیبات مربیان",
      link: "/"
    },
    {
      name: "راهنمای ثبت نام",
      link: "/"
    },
  ],
  contactUs: {
    text: "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است.",
    address: "تهران مهدکودک همیشه بهار",
    tell: "021987654321",
    email: "esakhanzadi@gmail.com",
    social: [
      {
        img: "teal",
        name: "facebook",
        color:"blue",
        link: "https://wwww.google.com"
      },
      {
        img: "",
        name: "twitter",
        color:"cyan",
        link: "https://wwww.google.com"
      },
      {
        img: "",
        name: "instagram",
        color:"Fuchsia",
        link: "https://wwww.google.com"
      },
      {
        img: "",
        name: "linkedin",
        color:"Indigo",
        link: "https://wwww.google.com"
      },
      {
        img: "",
        name: "youtube",
        color:"red",
        link: "https://wwww.google.com"
      },
    ]
  }
})
</script>

<template>
  <footer>
    <div class="footer-container grid  gap-y-5 md:grid-cols-2 lg:grid-cols-5  lg:justify-items-center   lg:mx-auto   ">
      <section class="brand-info px-7">
        <div class="brand-nav ">
          <div class="brand-title">
            <nuxt-link to=""><h3>همیشه بهار</h3></nuxt-link>
          </div>
        </div>
        <div class="brand-text-info">
          <p class="brand-text footer-text text-justify">
            {{ data.brand.body }}
          </p>
        </div>
        <div class="namad flex justify-center mt-4">
          <ul class="namad-info flex w-full justify-evenly lg:inline-flex mx-auto">
            <li>
              <nuxt-link to="">
                <figure>
                  <img :src="data.samandehi.imgOne" alt="">
                </figure>
              </nuxt-link>
            </li>
            <li class="divider"></li>
            <li>
              <nuxt-link to="">
                <figure>
                  <img :src="data.samandehi.imgTwo" alt="">
                </figure>
              </nuxt-link>
            </li>
          </ul>
        </div>
      </section>
      <section>
        <nav class="px-7">
          <div class="platform-online-title ">
            <h3>پلتفرم آنلاین</h3>
          </div>
          <ul class="nav platform-online flex flex-col gap-3">
            <li class="nav-item footer-text" v-for="(item , index ) in data.pOnline" :key="index">
              <nuxt-link :to="item.link" class="nav-link"> {{ item.name }}</nuxt-link>
            </li>
          </ul>
        </nav>
      </section>
      <section>
        <nav class="practical-link  px-7">
          <div class="practical-link-title">
            <h3>لینک های کاربردی</h3>
          </div>
          <ul class="nav flex flex-col gap-3">
            <li class="nav-item footer-text" v-for="(item , index) in data.bestLink" :key="index">
              <nuxt-link :class="`nav-link after:content-['${item.name}']`" :to="item.link">{{ item.name }}</nuxt-link>
            </li>
          </ul>
        </nav>
      </section>
      <section class="lg:col-span-2">
        <div class="contact-us px-7">
          <div class="contact-us-title">
            <h3>ارتباط با ما</h3>
          </div>
          <div class="flex flex-col gap-3">
            <div class="contact-us-info footer-text">
              <p>{{ data.contactUs.text }}</p>
            </div>
            <div class="contact-us-body flex flex-col gap-1 ">
              <div class="contact-us-address footer-text">
                <span>آدرس : </span>
                <span>{{ data.contactUs.address }}</span>
              </div>
              <div class="contact-us-tell footer-text">
                <span>تلفن :</span>
                <span>{{ data.contactUs.tell }}</span>
              </div>
              <div class="contact-us-email footer-text">
                <span>رایانامه : </span>
                <span>{{ data.contactUs.email }}</span>
              </div>
            </div>
            <div class="contact-us-footer mt-3">
              <ul class="nav social-app flex justify-between  w-1/3 md:w-1/2 md:gap-4 ">
                <li class="nav-item footer-text footer-text" v-for="(item , index) in data.contactUs.social"
                    :key="index">
                  <nuxtLink class="nav-link" :to="item.link"><i :class="`fa-brands text-${item.color}-500 fa-${item.name} fa-lg`"/></nuxtLink>

                </li>
              </ul>
            </div>
          </div>
        </div>
      </section>
    </div>
    <div class="cloud-background">
      <div class="cloud-background-image"></div>
    </div>
  </footer>
</template>

<style scoped lang="scss">
@import "~/assets/style/_varible.scss";

footer {
  background-color: $background-color-footer;
  position: relative;

  & .cloud-background-image {
    background: #1AB69D;
    height: 100px;
    background: url("@/assets/image/shape/cloud-bottom.svg");
    background-size: cover;
    background-repeat: no-repeat;
  }
}

.contact-us-title, .brand-title, .platform-online-title, .practical-link-title {
  margin-bottom: 20px;
  font-size: $fontSize-footer-title;
}

.contact-us-body {
  line-height: $lineHeight-footer;
  font-size: $fontWeight-footer-address;
}

h3 {
  color: $color-footer-title;
  font-size: $fontSize-footer-title;
}

.footer-text {
  color: $color-footer-text;
}

.footer-container {
  line-height: $lineHeight-footer;
  max-width: 1200px;
  padding: 100px 0;
  position: relative;
  z-index: 0;
}

.namad-info {
  background: #f9f9f9;
  border-radius: 1px;
  overflow: hidden;
  li:not(.divider){
    padding: 12px;
  figure {
    width: 90px;
    height: 90px;
  }}

  .divider {
    width: 1px;
    height: 100%;
    background: rgba(0, 0, 0, 0.1);
  }
}


</style>