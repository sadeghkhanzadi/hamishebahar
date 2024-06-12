<script setup lang="ts">
const emit = defineEmits(["closeNavbarMobile"])
const props =defineProps(['data'])
function close() {
  emit('closeNavbarMobile')
}

const menus = [
  {
    name: "خانه",
    link: "",
    sub: [
      {
        name: "دموی اصلی ",
        link: "",
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
  {
    name: "سبد خرید",
    link: ""
  }
]
</script>

<template>
  <div class="backdrop z-10" @click.self="emit('closeNavbarMobile')">
    <div class="navbar-mobile flex w-full relative md:w-1/2 lg:w-1/3 px-5 z-10 ">
      <nav class="w-full z-10">
        <div class="navbar-brand py-7 flex items-center">
          <nuxt-link to="/">همیشه بهار</nuxt-link>
        </div>
        <ul class="nav w-full ">
            <navbar v-for="(item , index) in data" :key="index" :data="item" theme="parent"/>
        </ul>
      </nav>
    </div>
  </div>
</template>

<style scoped lang="scss">
.backdrop {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.3);
  backdrop-filter: blur(5px);

  .navbar-mobile {
    background-color: white;
    height: 100%;
    overflow: scroll;

    &::-webkit-scrollbar {
      display: none;
    }
  }
}

li:last-child {
  border: 0;
}

</style>