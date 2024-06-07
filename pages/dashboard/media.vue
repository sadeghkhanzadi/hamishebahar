<script setup lang="ts">
const showFilterMedia = ref(false)
const editCard = ref(false)
const addMedia = ref(false)
const dataCard: object = ref()
const route = useRoute()
const router = useRouter()
import boyOne from '@/assets/image/media/boy-one.jpg'
import boyTwo from '@/assets/image/media/boy-two.jpg'
import boyThree from '@/assets/image/media/boy-three.jpeg'
import girlOne from '@/assets/image/media/girl-one.jpg'

const filters = [
  {
    name: "همه فایل های رسانه",
    sort: "all"
  },
  {
    name: "تصاویر",
    sort: "images"
  },
  {
    name: "صوت ها",
    sort: "sounds"
  },
  {
    name: "ویدیو ها",
    sort: "videos"
  },
  {
    name: "سندها",
    sort: "texts"
  },
  {
    name: "صفحه های گسترده",
    sort: "sheets"
  },
  {
    name: "بایگانی ها",
    sort: "storage"
  },
  {
    name: "مال خودم",
    sort: "me"
  },

]
const medias = [
  {
    name: "boy-one",
    img: boyOne,
  },
  {
    name: "boy-two",
    img: boyTwo,
  },
  {
    name: "boy-three",
    img: boyThree,
  },
  {
    name: "girl",
    img: girlOne,
  }
]

function showEditeCard(data) {
  dataCard.value = data
  editCard.value = !editCard.value
}
function  closeFilterMedia(){
  setTimeout(()=>(showFilterMedia.value = false),100)
}
</script>

<template>
  <div>
    <div class="header flex items-center  justify-between">
      <section>
        <h1 class="title text-2xl font-bold">رسانه ها</h1>
      </section>
      <section class="flex gap-10 items-center">
        <div class="filters relative w-48    ">
          <div @click="showFilterMedia = !showFilterMedia"
               class="filter-title hover:cursor-pointer flex justify-between items-center  px-3 text-center border py-2.5 rounded-lg bg-white text-sm">
            <i class="fa-solid fa-sort-amount-down"/>
            <h3>فیلتر بر اساس</h3>
            <i class="fa-solid fa-angle-down"/>
          </div>
          <div v-if="showFilterMedia" class="absolute filters-body rounded-sm overflow-hidden w-full mt-3 z-10">
            <ul class="bg-white w-full text-sm  ">
              <li class="py-2 px-3 hover:bg-gray-50" @click="closeFilterMedia"  v-for="(item , index) in filters"
                  :key="index">
                <nuxt-link :to="{query:{sort:item.sort}}">{{ item.name }}</nuxt-link>
              </li>
            </ul>
          </div>
        </div>
        <div>
          <div class="add-media px-10  py-2 rounded-lg ">
            <div class="flex gap-4 items-center hover:cursor-pointer " @click="addMedia=true">
              <i class="fa-solid fa-plus fa-xl"/>
              <h3 class="mx-auto">افزودن رسانه</h3>
            </div>
          </div>
        </div>
      </section>
    </div>
    <main>
      <article>
        <div class="mt-10">
          <div class="media-card grid grid-cols-4 gap-5">
            <dashboard-media @edite-card="showEditeCard" v-for="(item , index) in medias" :key="index" :data="item"/>
          </div>
          <div class="edite-media-modal">
            <transition name="modal">
              <dashboard-media-edite @close="editCard = !editCard" v-if="editCard" :data="dataCard"/>
            </transition>
          </div>
          <div class="add-media-modal">
            <transition name="modal">
              <dashboard-media-edite theme="addMedia" @close="addMedia = !addMedia" v-if="addMedia"/>
            </transition>
          </div>
        </div>
      </article>
    </main>
  </div>
</template>

<style scoped lang="scss">
.filters-body {
  box-shadow: 2px 2px 5px 1px rgba(0, 0, 0, 0.5);
}

.add-media {
  background: #3498db;
  color: white;
  position: relative;
  padding-right: 25px;
}



</style>