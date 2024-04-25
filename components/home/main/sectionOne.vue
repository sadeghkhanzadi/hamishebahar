<script setup >
import book from "~/assets/image/shape/book.png"
import pen from "~/assets/image/shape/pen.png"
import scissor from "~/assets/image/shape/scissor.png"
import circleRed from "~/assets/image/shape/red-dot.png"
import circleBlue from "~/assets/image/shape/blue-dot.png"
import laboratory from "~/assets/image/shape/labratoar.png"
import calender from "~/assets/image/shape/calender.png"
import boy from "~/assets/image/face/circle--boy-kid.webp"
import girl from "~/assets/image/face/circle-kid.webp"
let axis=ref([])
onMounted(() => {
  const shapes = document.querySelectorAll('.shape-move')
  shapes.forEach(
      (item,index)=>{
        if(Math.random()>0.5){
          axis.value.push(1)
        }else{
          axis.value.push(-1)
        }
      }
  )
  const base = document.querySelector('.content')
  base.addEventListener('mousemove', (e) => {
    shapes.forEach((item, index) => {
      let finalX=(e.clientX * 30) / window.innerWidth
     let finalY = (e.clientY* 30) / window.innerHeight
      item.style.transform=`translate(${axis.value[index]*finalX}px , ${axis.value[index]*finalY}px)`
    })
  })
})


const data = {
  card: {
    title: "آینده ای روشن تر برای بچه ها\n",
    text: {
      textOne: "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است.",
      textTwo: "از امروز برای آینده کودکتان برنامه ریزی کنید",
    },

    more: {
      name: "مشاهده کلاس ها ",
      link: "/"
    },
    links: [
      {
        name: "450 دوره آنلاین",
        link: "",
        icon: "fa-film",
        color: "#8E56FF"
      },
      {
        name: "مدرسین برتر",
        link: "",
        icon: "fa-person-chalkboard",
        color: "#EE4A62"
      },
      {
        name: "گواهینامه انلاین",
        link: "",
        icon: "fa-id-card",
        color: "#1AB69D"
      },
    ]
  },
  shape: [
    {
      name: "calender",
      img: calender
    },
    {
      name: "pen",
      img: pen
    },
    {
      name: "red-circle",
      img: circleRed
    },
    {
      name: "blue-circle",
      img: circleBlue
    },
    {
      name: "book",
      img: book
    },
    {
      name: "scissor",
      img: scissor
    },
    {
      name: "laboratory",
      img: laboratory
    },
  ],
  face: {
    boy: {
      title: "boy kid",
      img: boy
    },
    girl: {
      title: "boy kid",
      img: girl
    },
  }
}
</script>

<template>
  <div>
    <div class="content">
      <div class="container flex justify-center mx-auto relative h-[95vh]">
        <div class="shapes">
          <div :class="`shape-${item.name} absolute shape-move opacity-50`" v-for="(item , index) in data.shape"
               :key="index">
            <figure><img :src="item.img" :alt="item.name" :data-speed="`${Math.floor(Math.random()*10)}`"></figure>
          </div>
        </div>
        <div class="flex items-center justify-center main-group">
          <div class="child">
            <figure>
              <img :src="data.face.girl.img" :alt="data.face.girl.title">
            </figure>
          </div>
          <div class="card text-content flex flex-col gap-10">
            <div class="card-body flex flex-col gap-5">
              <div class="header-text-content text-center">
                <h1>{{ data.card.title }}</h1>
              </div>
              <div class="body-text-content text-center">
                <p>{{ data.card.text.textOne }}</p>
                <p>{{ data.card.text.textTwo }}</p>
              </div>
              <div class="see-more-content text-center">
                <nuxt-link :to="data.card.more.link" class="inline-flex items-center justify-center">
                  <span class="flex items-center"><i class="fa-solid fa-arrow-right"></i></span>
                  <span>{{ data.card.more.name }}</span>
                </nuxt-link>
              </div>
            </div>
            <div class="card-footer">
              <nav>
                <ul class="nav flex justify-center gap-10">
                  <li class="nav-item flex items-center justify-center " v-for="(item , index) in data.card.links"
                      :key="index">
                    <nuxtLink class="nav-link flex flex-col justify-center items-center gap-y-3" :to="item.link">
                      <div class="icon flex justify-center items-center p-3 rounded-full"
                           :style="{background:`${item.color}`}">
                        <i :class="`fa-solid ${item.icon} fa-xl text-white`"></i>
                      </div>
                      <div class="name"><span>{{ item.name }}</span></div>
                    </nuxtLink>
                  </li>
                </ul>

              </nav>
            </div>
          </div>
          <div class="child">
            <figure>
              <img :src="data.face.boy.img" :alt="data.face.boy.title">
            </figure>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
@import "@/assets/style/varible";

.content {
  background: $background-color-section-one;
  position: relative;
  overflow: hidden;

  &:after {
    position: absolute;
    bottom: -10px;
    left: 50%;
    transform: translate(-50%);
    content: url("@/assets/image/shape/cloud.png");
  }
}

.card-body {
  .header-text-content {
    margin-bottom: 20px;

    h1 {
      line-height: $lineHeight-title-section-one;
      font-size: $fontSize-title-section-one;
      color: $color-section-one;
      font-weight: $fontWeight-title-section-one;
    }
  }

  .body-text-content {
    margin-bottom: 20px;

    p {
      margin: 0 auto;
      line-height: $lineHeight-text-section-one;
      font-size: $fontSize-text-section-one;
      color: $color-section-one;
      font-weight: $fontWeight-text-section-one;
    }
  }
}

.card-footer .nav-item {

  & .icon {
    width: 80px;
    height: 80px;
  }
}

.shapes {
  transition: 0.5s;

  .shape.move {
    transition: 0.5s;
  }

  .shape-scissor {
    bottom: 0;
    left: 50%;
    opacity: 0.5;
  }

  .shape-pen {
    top: 10%;
    left: 0;
    opacity: 0.5;
  }

  .shape-calender {
    top: 10%;
    right: 0;
    opacity: 0.5;
  }

  .shape-laboratory {
    top: 50%;
    right: 25%;
    opacity: 0.5;
  }

  .shape-calender {
    top: 10%;
    right: 0;
    opacity: 0.5;
  }

  .shape-book {
    top: 20%;
    left: 15%;
    opacity: 0.5;
  }

  .shape-red-circle {
    left: 50%;
    top: -50px;
    animation: rotateRed linear 15s infinite;
    transform-origin: center;
  }

  .shape-blue-circle {
    right: 70%;
    bottom: -50px;
    animation: rotateBlue linear 15s infinite;
    transform-origin: center;
  }
}

.see-more-content {
  a {
    display: inline-flex;
    gap: 10px;
    align-items: center;

    background: $button-color;
    padding: 0.8rem 1.5rem;
    border-radius: $border-radius-btn;
    transition: all 0.5s;
    color: $text-color-white;

    &:hover {
      border-radius: $border-radius-btn-hover;
      background: $linearGradient-button;
      transition: 0.5s;
    }
  }

}

@keyframes rotateRed {
  from {
    transform: rotate(0);
  }
  to {
    transform: rotate(-360deg);
  }
}

@keyframes rotateBlue {
  from {
    transform: rotate(0);
  }
  to {
    transform: rotate(360deg);
  }
}
</style>