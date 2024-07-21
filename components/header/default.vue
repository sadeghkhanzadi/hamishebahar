<script setup>
import cartShopping from '~/assets/image/shape/cart-shopping.png'
import NavbarMobile from "~/components/header/navbarMobile.vue";
import Search from "~/components/search.vue";
const search = ref(false)
const showNavbarMobile = ref(false)
const showAuth = ref(false)
const cookie = useCookie('jwt')
const route = useRoute()
const data = ref({
  menu: [
    {
      name: "خانه",
      link: "/",
    },
    {
      name: "مربیان",
      link: "/teachers",
      sub: [
        {
          name: "حسین حسینی",
          link: "/teachers/1",
        },
        {
          name: "میلاد میرزایی",
          link: "/teachers/2",
        },
      ]
    },
    {
      name: "دوره های تحصیلی ",
      link: "/courses",
      sub: [
        {
          name: "نوزاد",
          link: "/courses/1"
        },
        {
          name: "مهدکودک",
          link: "/courses/2"
        },
        {
          name: "پیش دبستان",
          link: "/courses/3"
        },
        {
          name: "دبستان",
          link: "/courses/4"
        },
      ]
    },
    {
      name: "آموزش",
      link: "",
      sub: [
        {
          name: "رفتارشناسی",
        },
        {
          name: "روانشناسی",
        },
        {
          name: "بازی و آموزش ",
        },
        {
          name: " نقاشی",
        },
      ]
    },
    {
      name: "اخبار و رویداد ها",
      link: "news",
    },
    {
      name: "تماس با ما",
      link: "/contact-us",
    },
    {
      name: "درباره ما",
      link: "/about-us",
    }

  ],
  brand: "همیشه بهار",
  icon: {
    shop: cartShopping,
    search: ""
  },
  start: "استارتش رو بزن ."
})
onMounted(() => {
  watch(showNavbarMobile, () => {
    showNavbarMobile.value ? document.body.classList.add("overflow-hidden") : document.body.classList.remove("overflow-hidden")
  })
  watch(showAuth, () => {
    showAuth.value ? document.body.classList.add("overflow-hidden") : document.body.classList.remove("overflow-hidden")
  })
})
watch(() => route.name, () => {
  if (showNavbarMobile.value) {
    showNavbarMobile.value = false
  }

})

function login() {
  if (cookie.value) {
    navigateTo('/dashboard')
  } else {
    showAuth.value = true
  }
}


</script>

<template>
  <div class="header" :class="{addCloud : route.path === '/'}">
    <div
        class=" w-[95%] md:w-[90%] max-w-screen-2xl  mx-auto flex justify-between lg:mx-5 xl:mx-auto items-center xl:items-center px-5 sm:px-7 lg:px-0 py-5 gap-10   header-container relative ">
      <div class="navbar-brand flex items-center">
        <nuxt-link to="/" class="flex flex-row-reverse items-center gap-2">
          <h1>
            {{ data.brand }}
          </h1>
          <figure class="flex items-center">
            <img src="/logo/logo.png" alt="همیشه بهار لوگو">
          </figure>
        </nuxt-link>
      </div>
      <nav class="flex justify-end xl:justify-between ms-auto gap-8 lg:w-full items-center ">
        <ul class="nav menu hidden justify-between gap-5 lg:gap-5 xl:gap-3 2xl:gap-7 lg:flex">
          <li class="nav-item relative" v-for="(item , index) in data.menu" :key="index">
            <nuxt-link class="nav-link flex items-baseline gap-1.5 hover:cursor-pointer " :to="item.link">
              <span>{{ item.name }}</span>
              <span v-if="item.sub"><i class="fa-solid fa-angle-down fa-md text-sm font-bold flex items-center"/></span>
            </nuxt-link>
            <ul v-if="item.sub" class="grid  gap-4 sub-menu mt-6" :class="{colsActive:item.sub.length>15}">
              <li v-for="(item , index) in item.sub" :key="index">
                <nuxtLink :to="item.link" class=" flex justify-between">
                  <span>{{ item.name }}</span>
                  <span v-if="item.sub"><i class="fa-solid fa-angle-left"/></span>
                </nuxtLink>
                <ul v-if="item.sub" class="grid  gap-4 sub-menu mt-6" :class="{colsActive:item.sub.length>15}">
                  <li v-for="(item , index) in item.sub" :key="index">
                    <nuxtLink :to="item.link" class=" flex justify-between">
                      <span>{{ item.name }}</span>
                      <span v-if="item.sub"><i class="fa-solid fa-angle-left"/></span>
                    </nuxtLink>
                  </li>
                </ul>
              </li>
            </ul>
          </li>
        </ul>
        <ul class="nav gap-5 lg:gap-5 flex 2xl:gap-8  items-center">
          <li class="nav-item search">
            <div class="input-group">
              <button @click="search = true" class="flex items-center justify-center"><i class="fa-solid fa-lg fa-search"/></button>
              <search v-if="search" @close-search ="search = false" />
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
            <button class="nav-link hover:cursor-pointer " @click="login">
              <span v-if="cookie">ورود</span>
              <span v-else>استارتش رو بزن </span>
            </button>
          </li>
        </ul>
      </nav>
      <div class="hamburger-menu p-1 z-[11] overflow-hidden lg:hidden">
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
    <div class="login ">
      <transition name="auth">
        <auth v-if="showAuth">
          <button @click="showAuth=false"
                  class="absolute top-5 right-[50%] sm:transform translate-x-[50%] sm:top-10 sm:right-10 p-2 rounded-md border-gray-600 flex items-center justify-center"><i
              class="fa-solid fa-close fa-xl flex "/></button>
        </auth>
      </transition>
    </div>
    <transition name="transition">
      <navbar-mobile v-if="showNavbarMobile" :data="data.menu" @close-navbar-mobile="showNavbarMobile = false" @show-login="showAuth=true"/>
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
    top: -20%;
    right: 0;
    z-index: -1 !important;
  }

  &:after {
    content: url('@/assets/image/shape/left-cloud.png');
    position: absolute;
    top: -20%;
    left: 0;
    z-index: -1 !important;
  }
}

.navbar-brand {
  text-wrap: nowrap;
  figure {
    width: 40px;
    height: 40px;
    border-radius: 100%;
    overflow: hidden;
    img {
      mix-blend-mode: multiply;
      width: 100%;
      height: 100%;
    }
  }
  a{
    font-size:var(--text-xl);
    @media screen and (max-width: 1300px) {
      font-size:var(--text-lg);

    }
  }
}

nav {
  & .nav.menu {
    a {
      font-size: var(--text-xl);
      @media screen and (max-width: 1400px) {
          font-size: var(--text-lg);
      }
      @media screen and (max-width: 1300px) {
          font-size: var(--text-md);
      }
    }
  }
}

.input-group {
  display: flex;

  #search {
    padding: 4px 8px;

    &::placeholder {
      font-size: 12px;
    }
  }

  input {
    border: 1px solid rgba(0, 0, 0, 0.1);
    border-left: 0;
    border-top-right-radius: 3px;
    border-bottom-right-radius: 3px;
    outline: 0;

    &:not([id="search"]) {
      padding: 8px;
    }
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
    width: 25px;
    height: 25px;

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
  font-size: 12px;

  &:hover {
    background-color: $linearGradient-header-button !important;
    border-radius: 0 25px 0 25px;
    transition: 0.3s;
  }
}


nav {
  .menu {
    position: relative;
    z-index: 1;

    a {
      cursor: pointer;
    }

    .sub-menu {
      position: absolute;
      border-radius: 3px;
      top: 0;
      padding: 30px 30px 30px;
      right: 0;
      min-width: 270px;
      background-color: white;
      transition: .4s;
      opacity: 0;
      transform: translateY(-20%);
      visibility: hidden;
      z-index: -1;

      & .sub-menu {
        position: absolute;
        right: 101%;
        opacity: 0;
        transform: translatex(10%);
        visibility: hidden;
        z-index: -2;
      }
    }

    li:hover > .sub-menu {
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

.transition-enter-active {
  transition: all 0.7s ease;
}

.transition-leave-active {
  transition: all 0.5s ease;
}

.transition-enter-from, .transition-leave-to {
  transform: translatex(100%);
}

.auth-enter-active {
  transition: all 0.7s ease;
}

.auth-leave-active {
  transition: all 0.5s ease;
}

.auth-enter-from, .auth-leave-to {
  opacity: 0;
  transform: translateY(-100%);
}


.colsActive {
  grid-template-columns: repeat(2, 270px) !important;
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


@media screen and (min-width: 1800px) {
  .header.addCloud {
    &::after, &::before {
      display: block;
    }
  }
}

@media screen and (max-width: 1450px) {
  .header.addCloud {
    &::after, &::before {
      display: none;
    }
  }
}
</style>
