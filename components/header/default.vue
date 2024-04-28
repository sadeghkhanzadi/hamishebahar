<script setup>
import cartShopping from '~/assets/image/shape/cart-shopping.png'
import NavbarMobile from "~/components/header/navbarMobile.vue";
const showNavbarMobile = ref(false)
const route = useRoute()
const data = ref({
  menu: [
    {
      name: "خانه",
      link: "",
      sub: [
        {
          name: "دموی اصلی ",
          link: "/#",
        },
        {
          name: "آموزش از راه دور ",
          link: ""
        },
        {
          name: "دانشگاه",
          link: ""
        },
        {
          name: "آکادمی آنلاین",
          link: ""
        },
        {
          name: "آموزشگاه مدرن",
          link: ""
        },
        {
          name: "آموزش آشپزی",
          link: ""
        },
        {
          name: "یوگا",
          link: ""
        },
        {
          name: "مهد کودک",
          link: ""
        },
        {
          name: "آموزشگاه زبان",
          link: ""
        },
        {
          name: "آموزش از راه دور ",
          link: ""
        },
        {
          name: "مدیریت کسب و کار",
          link: ""
        },
        {
          name: "انگیزشی",
          link: ""
        },
        {
          name: "برنامه نویسی ",
          link: ""
        },
        {
          name: "هنر آنلاین",
          link: ""
        },
        {
          name: "آموزش فروش",
          link: ""
        },
        {
          name: "آموزش قرآن",
          link: ""
        },
        {
          name: "آموزش بدنسازی",
          link: ""
        },
      ]
    },
    {
      name: "صفحه ها",
      link: "",
      sub: [
        {
          name: "درباره ما ",
          link: "",
          sub: [
            {
              name: "درباره ما - 1",
              link: ""
            },
            {
              name: "درباره ما - 2",
              link: ""
            },
            {
              name: "درباره ما - 3",
              link: ""
            },
            {
              name: "درباره ما - 4",
              link: ""
            },
          ]
        },
        {
          name: "مربیان",
          link: "",
          sub: [
            {
              name: "مربیان - 1",
              link: ""
            },
            {
              name: "مربیان - 2",
              link: ""
            },
            {
              name: "مربیان - 3",
              link: ""
            },
            {
              name: "مربیان - 4",
              link: ""
            },
          ]
        },
        {
          name: "صفحه های رویداد",
          link: "",
          sub: [
            {
              name: "صفحه های رویداد - 1",
              link: ""
            },
            {
              name: "صفحه های رویداد - 2",
              link: ""
            },
            {
              name: "صفحه های رویداد - 3",
              link: ""
            },
            {
              name: "صفحه های رویداد - 4",
              link: ""
            },
          ]
        },
        {
          name: "صفحه های فروشگاه",
          link: "",
          sub: [
            {
              name: "صفحه های فروشگاه - 1",
              link: ""
            },
            {
              name: "صفحه های فروشگاه - 2",
              link: ""
            },
            {
              name: "صفحه های فروشگاه - 3",
              link: ""
            },
            {
              name: "صفحه های فروشگاه - 4",
              link: ""
            },
          ]
        },
        {
          name: "پرسش های متداول",
          link: ""
        },
        {
          name: "جدول قیمت ",
          link: ""
        },
        {
          name: "حریم خصوصی",
          link: ""
        },
        {
          name: "به زودی",
          link: ""
        },
        {
          name: "صفحه 404",
          link: ""
        },
      ]
    },
    {
      name: "دوره ها ",
      link: "",
      sub: [
        {
          name: "استایل دوزه ها",
          link: "",
          sub: [
            {
              name: "استایل دوزه ها - 1",
              link: ""
            },
            {
              name: "استایل دوزه ها - 2",
              link: ""
            },
            {
              name: "استایل دوزه ها - 3",
              link: ""
            },
            {
              name: "استایل دوزه ها - 4",
              link: ""
            },
          ]
        },
        {
          name: "جزییات دوره ها",
          link: "",
          sub: [
            {
              name: "جزییات دوره ها - 1",
              link: ""
            },
            {
              name: "جزییات دوره ها - 2",
              link: ""
            },
            {
              name: "جزییات دوره ها - 3",
              link: ""
            },
            {
              name: "جزییات دوره ها - 4",
              link: ""
            },
          ]
        },
        {
          name: "فیلتر دوره ها",
          link: "",
          sub: [
            {
              name: "فیلتر دوره ها - 1",
              link: ""
            },
            {
              name: "فیلتر دوره ها - 2",
              link: ""
            },
            {
              name: "فیلتر دوره ها - 3",
              link: ""
            },
            {
              name: "فیلتر دوره ها - 4",
              link: ""
            },
          ]
        },
      ]
    },
    {
      name: "صفحه های بلاگ",
      link: "",
      sub: [
        {
          name: "بلاگ 1",
          link: " "
        },
        {
          name: "بلاگ 2",
          link: ""
        },
        {
          name: "بلاگ استاندارد",
          link: ""
        },
        {
          name: " جزئیات بلاگ ",
          link: ""
        },
      ]
    },
    {
      name: "صفحه های تماس",
      link: "",
      sub: [
        {
          name: "صفحه تماس با ما ",
          link: ""
        },
        {
          name: "صفحه تماس با من ",
          link: ""
        },
      ]
    },
  ],
  brand: "همیشه بهار",
  icon: {
    shop: cartShopping,
    search: ""
  },
  start: "استارتش رو بزن ."
})
watch(route , ()=>{
  console.log(5)
})
console.log(route)
onMounted(()=>{
  watch(showNavbarMobile , ()=>{
    showNavbarMobile.value ? document.body.classList.add("overflow-hidden") : document.body.classList.remove("overflow-hidden")
  })
})
</script>

<template>
  <div class="header" :class="{addCloud : route.path === '/'}" >
    <div
        class="  mx-auto flex justify-between  items-center xl:items-baseline px-5 sm:px-7 lg:px-0 py-5 gap-10 md:gap-x-16 lg:container header-container">
      <div class="navbar-brand">
        <nuxt-link to="/">{{ data.brand }}</nuxt-link>
      </div>
      <nav class="flex justify-end xl:justify-between ms-auto lg:w-full items-center ">
        <ul class="nav menu hidden justify-between gap-5 2xl:gap-7 xl:flex">
          <li class="nav-item relative" v-for="(item , index) in data.menu" :key="index">
            <nuxt-link class="nav-link flex items-center gap-3 hover:cursor-pointer " :to="item.link">
              <span>{{ item.name }}</span>
              <span><i class="fa-solid fa-angle-down fa-md text-sm font-bold"/></span>
            </nuxt-link>
            <ul v-if="item.sub" class="grid  gap-4 sub-menu mt-6" :class="{colsActive:item.sub.length>15}">
              <li v-for="(item , index) in item.sub" :key="index">
                <nuxtLink :to="item.link" class=" flex justify-between">
                  <span>{{item.name}}</span>
                  <span v-if="item.sub"><i class="fa-solid fa-angle-left"/></span>
                </nuxtLink>
                <ul v-if="item.sub" class="grid  gap-4 sub-menu mt-6" :class="{colsActive:item.sub.length>15}">
                  <li v-for="(item , index) in item.sub" :key="index">
                    <nuxtLink :to="item.link" class=" flex justify-between">
                      <span>{{item.name}}</span>
                      <span v-if="item.sub"><i class="fa-solid fa-angle-left"/></span>
                    </nuxtLink>
                  </li>
                </ul>
              </li>
            </ul>
          </li>
        </ul>
        <ul class="nav flex md:gap-6  items-center">
          <li class="nav-item search">
            <div class="input-group">
              <input type="text" id="search" placeholder="دنبال چی میگردی ؟">
              <label for="search" class="flex items-center justify-center"><i class="fa-solid fa-search"/></label>
            </div>
          </li>
          <li class="nav-item shop-box  ">
            <nuxt-link class="nav-link hidden md:block" to="">
              <figure>
                <img :src="data.icon.shop" alt="cart-shopping">
              </figure>
            </nuxt-link>
          </li>
          <li class="nav-item start hidden md:inline-block">
            <nuxt-link class="nav-link" to="">{{ data.start }}</nuxt-link>
          </li>
        </ul>
      </nav>
      <div class="hamburger-menu p-1 z-[11] overflow-hidden xl:hidden">
        <input type="checkbox" id="checkbox2" class="checkbox2 visuallyHidden">
        <label for="checkbox2" :class="{active:showNavbarMobile===true}" @click="showNavbarMobile = !showNavbarMobile">
          <div class="hamburger hamburger2">
            <span class="bar bar1"></span>
            <span class="bar bar2"></span>
            <span class="bar bar3"></span>
            <span class="bar bar4"></span>
          </div>
        </label>
      </div>
    </div>
    <transition name="transition">
      <div class="navbar-mobile" >
        <navbar-mobile v-if="showNavbarMobile" @close-navbar-mobile="showNavbarMobile = false" />
      </div>
    </transition>
  </div>
</template>

<style scoped lang="scss">
@import "@/assets/style/_varible.scss";

.header.addCloud {
  position: relative;
  z-index: 1;

  &::before {
    content: url('@/assets/image/shape/right-cloud.png');
    position: absolute;
    top: 0;
    right: 0;
    z-index: -1 !important;
  }

  &:after {
    content: url('@/assets/image/shape/left-cloud.png');
    position: absolute;
    top: 0;
    left: 0;
    z-index: -1 !important;
  }
}

.navbar-brand {
  text-wrap: nowrap;
}

nav {
  & .nav.menu {
    font-size: $fontSize-header-menu;
  }
}

.input-group {
  display: flex;

  input {
    border: 1px solid rgba(0, 0, 0, 0.1);
    border-left: 0;
    padding: 8px;
    border-top-right-radius: 3px;
    border-bottom-right-radius: 3px;
    outline: 0;
  }

  label {
    border: 1px solid rgba(0, 0, 0, 0.1);
    border-right: 0;
    padding: 8px;
    border-top-left-radius: 3px;
    border-bottom-left-radius: 3px;
  }
}

.shop-box {
  display: flex;
  justify-content: center;
  align-items: center;

  & figure {
    width: 30px;
    height: 30px;

    & img {
      width: 100%;
      height: 100%;
    }
  }
}

.start {
  background: $button-header;
  padding: 0.5rem 1rem;
  border-radius: 25px 0 25px 0;
  transition: 0.3s;
  color: $text-color-white-header;

  &:hover {
    background-color: $linearGradient-header-button !important;
    border-radius: 0 25px 0 25px;
    transition: 0.3s;
  }
}


nav {
.menu{
  position: relative;
  z-index: 1;
  a{
    cursor: pointer;
  }
  .sub-menu{
    position: absolute;
    border-radius: 3px;
    top: 0;
    padding: 50px 30px 30px;
    right: 0;
    min-width: 270px;
    background-color: white;
    transition: .4s;
    opacity: 0;
    transform: translateY(-20%);
    visibility: hidden;
    z-index: -1;
    & .sub-menu{
      position: absolute;
      right:101%;
      opacity: 0;
      transform: translatex(10%);
      visibility: hidden;
      z-index: -2;
    }
  }
  li:hover > .sub-menu{
    transform: translate(0%);
    opacity: 1;
    visibility: visible;
    & li:hover .sub-menu {
      transform: translate(0%);
      opacity: 1;
      visibility: visible;
    }
  }
}
}

//.transition-enter-active {
//  transition: all 0.7s ease;
//}
//
//.transition-leave-active {
//  transition: all 0.5s ease;
//}
//
//.transition-enter-from, .transition-leave-to {
//  transform: translatex(-100%);
//}
//
//.transition-enter-to, .transition-leave-from {
//  transform: translatex(100%);
//
//}
.transition-enter-active,
.transition-leave-active {
  transition: transform 0.5s ease;
}

.transition-enter-from,
.transition-leave-to {
  transition: translatex 0.5s ease;
  transform: translatex(-100%);
}

.colsActive{
  grid-template-columns: repeat(2 , 270px) !important;
}

@media screen and (max-width: 1450px) {
  .header {
    &::after, &::before {
      display: none !important;
    }
  }
}

;
@media screen and (max-width: 500px) {
  .input-group {
    display: flex;

    input {
      display: none !important;
    }

    label {
      border: 0;
      padding: 8px;
    }
  }
  .shop-box {
    display: none;
  }
}

;


// hamburger  button


.visuallyHidden {
  position: absolute;
  overflow: hidden;
  clip: rect(0 0 0 0);
  height: 1px;
  width: 1px;
  margin: -1px;
  padding: 0;
  border: 0;
}

h1 {
  text-align: center;
}

.container {
  width: 70%;
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
}

.hamburger {
  margin: 0 auto;
  width: 30px;
  height: 30px;
  position: relative;
}

.hamburger .bar {
  padding: 0;
  width: 30px;
  height: 4px;
  background-color: black;
  display: block;
  border-radius: 4px;
  transition: transform 0.4s ease;
  position: absolute;
}

.bar1 {
  top: 0;
}

.bar2,
.bar3 {
  top: 13.5px;
}

.bar3 {
  right: 0;
}

.bar4 {
  bottom: 0;
}

.active > .hamburger2 > .bar1 {
  transform: translateX(40px);
  background-color: white;
    transition: transform 0.4s ease;

}

.active > .hamburger2 > .bar2 {
  transform: rotate(45deg);
  border: 2px solid black;
  background-color: white;
}

.active > .hamburger2 > .bar3 {
  transform: rotate(-45deg);
  border: 2px solid black;
  background-color: white;
}

.active > .hamburger2 > .bar4 {
  transform: translateX(-40px);
  background-color: white;
    transition: transform 0.5s ease;

}
</style>