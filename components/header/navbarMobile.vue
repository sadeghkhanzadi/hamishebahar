<script setup lang="ts">
const showItems = ref(false)
const emit = defineEmits(["closeNavbarMobile"])
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
]

const li = ref(null)
onMounted(()=>{
  const li = document.querySelectorAll('.navbar-mobile-item')
  const link = document.querySelectorAll('.navbar-mobile-item > a')

  link.forEach((item , index)=>{
    item.addEventListener("click" , ()=>{
      li[index].classList.toggle('show')
    })
  })
})

</script>

<template>
  <div class="backdrop" >
    <div class="navbar-mobile flex w-full relative md:w-1/2 lg:w-1/3 px-5 z-10 ">
      <nav class="w-full z-10">
        <div class="navbar-brand py-7 flex items-center">
          <nuxt-link to="">همیشه بهار</nuxt-link>
        </div>
        <ul class="nav w-full">
          <li v-for="( item , index) in menus" ref="li" :key="index" class="w-full border-b border-gray-300 py-3 father navbar-mobile-item" >
            <nuxtLink :to="item.link" class="flex  justify-between w-full">
              <span>{{ item.name }}</span>
              <span v-if="item.sub" class="icon">
            </span>
            </nuxtLink>
            <ul class="nav w-full px-5 sub-menu mt-2" v-if="item.sub">
              <li v-for="item in item.sub" :key="index" class="w-full border-b border-gray-300 py-3 navbar-mobile-item sub-father">
                <nuxtLink :to="item.link" class="flex  justify-between w-full">
                  <span>{{ item.name }}</span>
                  <span v-if="item.sub" class="icon">
            </span>
                </nuxtLink>
                <ul class="nav w-full px-5 sub-menu mt-2  " v-if="item.sub">
                  <li v-for="item in item.sub" :key="index" class="w-full border-b border-gray-300 py-3 navbar-mobile-item sub-father-two">
                    <nuxtLink :to="item.link" class="flex  justify-between w-full">
                      <span>{{ item.name }}</span>
                      <span v-if="item.sub" class="icon">
            </span>
                    </nuxtLink>

                  </li>
                </ul>
              </li>
            </ul>
          </li>
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
    &::-webkit-scrollbar{
      display: none;
    }
  }

  li:last-child {
    border: 0;
  }

  .sub-menu {
    transition: 0.3s;
    display: none;
    color: #555555;
  }

li{
  position: relative;
}
}
.show{
  display: block;
}
.show > ul{
 display: block !important;
}
li > a > span.icon:after {
position: absolute;
  content: "+";
  left: 0;
  font-size: 20px;
}
li.show > a > span.icon:after{
  content: "-";
}



</style>